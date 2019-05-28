package com.ketris.framework.engine

import com.ketris.GameConfig.CANVAS_HEIGHT
import com.ketris.GameConfig.CANVAS_WIDTH
import java.awt.Dimension

typealias InstantiateScreen = () -> GameScreen

object Game {
  var width: Int = 1
  var height: Int = 1
  val canvas = GamePanel(
    width = CANVAS_WIDTH, height = CANVAS_HEIGHT, screen = EmptyScreen()
  )
  private var isRunning: Boolean = true
  private var dt: Int = 0
  private var timeLastRunMs = System.currentTimeMillis()
  private val redrawLock = Object()
  val screens = mutableMapOf<InstantiateScreen, GameScreen>()

  fun create(screenClass: InstantiateScreen, width: Int, height: Int) {
    Game.width = width
    Game.height = height
    canvas.screen = screenClass()
    canvas.preferredSize = Dimension(width, height)
    canvas.overlay = Overlay()

    screens[screenClass] = canvas.screen
  }

  fun useScreen(newScreen: InstantiateScreen) {
    canvas.screen.unload()
    canvas.screen = newScreen()
    screens[newScreen] = canvas.screen
  }

  fun start() {
    val thread = Thread(Runnable { loop() })
    thread.start()
  }

  private fun loop() {
    while (isRunning) {
      val redrawDuration = redraw()
      try {
        Thread.sleep(Math.max(0, REFRESH_INTERVAL_MS - redrawDuration))
      } catch (e: InterruptedException) {
        e.printStackTrace()
      }
    }
  }

  private fun redraw(): Long {
    val t = System.currentTimeMillis()
    val deltaTime: Int = (t - timeLastRunMs).toInt()
    dt = deltaTime
    canvas.screen.update(deltaTime)

    // asynchronously signals the paint to happen in the swing thread
    canvas.repaint()

    // use a lock here that is only released once the paintComponent
    // has happened so that canvas.repaint() calls don't queue up that
    // are delayed and we get jerky drawing
    timeLastRunMs =  System.currentTimeMillis()
    waitForPaint()

    return timeLastRunMs - t
  }

  private fun waitForPaint() {
    try {
      synchronized(redrawLock) {
        redrawLock.wait()
      }
    } catch (e: InterruptedException) {
    }
  }

  fun clearRedrawLock() {
    synchronized(redrawLock) {
      redrawLock.notify()
    }
  }
}
