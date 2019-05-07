package com.ketris.framework.engine

import com.ketris.framework.io.MouseManager
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Toolkit
import java.util.concurrent.TimeUnit
import javax.swing.JPanel

const val FRAMES_PER_SECOND = 60
val REFRESH_INTERVAL_MS: Long = TimeUnit.SECONDS.toMillis(1) / FRAMES_PER_SECOND

class GamePanel(width: Int, height: Int, var screen: GameScreen, val game: Game) : JPanel() {
  private var timeLastRunMs = System.currentTimeMillis()
  private var isRunning: Boolean = true
  private val redrawLock = Object()
  private var dt: Int = 0
  private var fps = GameFPS()

  init {
    preferredSize = Dimension(width, height)
    isFocusable = true
    addMouseListener(MouseManager)
  }

  fun startGameLoop() {
    val thread = Thread(Runnable { loop() })
    thread.start()
  }

  private fun loop() {
    while (isRunning) {
      val durationMs = redraw()
      try {
        Thread.sleep(Math.max(0, REFRESH_INTERVAL_MS - durationMs))
      } catch (e: InterruptedException) {
        e.printStackTrace()
      }
    }
  }

  private fun redraw(): Long {
    val t = System.currentTimeMillis()
    val deltaTime: Int = (t - timeLastRunMs).toInt()
    dt = deltaTime
    screen.update(deltaTime)
    timeLastRunMs = t

    // asynchronously signals the paint to happen in the swing thread
    repaint()

    // use a lock here that is only released once the paintComponent
    // has happened so that canvas.repaint() calls don't queue up that
    // are delayed and we get jerky drawing
    waitForPaint()

    return System.currentTimeMillis() - t
  }

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)
    clearRedrawLock()
    val layers = listOf(
      // layer 1 : current screen
      screen,
      // layer 2 overlay on top to show information about the game
      Overlay(game, width, height)
    )
    // render all game layers
    layers.forEach { layer -> g.drawImage(layer.paintCanvas(), 0, 0, width, height, null) }

    fps.increment()

    Toolkit.getDefaultToolkit().sync()
    g.dispose()
  }

  private fun waitForPaint() {
    try {
      synchronized(redrawLock) {
        redrawLock.wait()
      }
    } catch (e: InterruptedException) {
    }
  }

  private fun clearRedrawLock() {
    synchronized(redrawLock) {
      redrawLock.notify()
    }
  }
}
