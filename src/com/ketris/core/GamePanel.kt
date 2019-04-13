package com.ketris.core

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Toolkit
import java.util.concurrent.TimeUnit
import javax.swing.JPanel


const val FRAMES_PER_SECOND = 60
val REFRESH_INTERVAL_MS: Long = TimeUnit.SECONDS.toMillis(1) / FRAMES_PER_SECOND

class GamePanel(width: Int, height: Int) : JPanel() {
  private var player = Player(100f, 100f, 30f, 30f)
  private var timeLastRunMs = System.currentTimeMillis()
  private var isRunning: Boolean = true
  private val redrawLock = java.lang.Object()
  private val screen = Screen()

  init {
    preferredSize = Dimension(width, height)
    isFocusable = true
    background = Color.decode("#333333")
    addKeyListener(KeyManager())
  }

  fun start() {
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

  override fun paintComponent(g: Graphics?) {
    super.paintComponent(g)
    clearRedrawLock()
    val g2D = g as Graphics2D

    // Smoothing up things so that we get good graphics instead of pixely things
    val rh = RenderingHints(
      RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
    )
    rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
    g2D.setRenderingHints(rh)

    screen.paint(g2D)

    // After painting :
    // - in debug mode Showing some info about current location and frame rates are essential
    // TODO: add debugging stuff.

    Toolkit.getDefaultToolkit().sync()
    g2D.dispose()
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
