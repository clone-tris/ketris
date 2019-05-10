package com.ketris.framework.engine

typealias InstantiateScreen = (width: Int, height: Int) -> GameScreen

object Game {
  var screen: GameScreen? = null
  var width: Int = 0
  var height: Int = 0
  var canvas: GamePanel? = null
  private var isRunning: Boolean = true
  private var dt: Int = 0
  private var timeLastRunMs = System.currentTimeMillis()
  private val redrawLock = Object()

  fun create(screenClass: InstantiateScreen, width: Int, height: Int): GamePanel {
    val canvas = GamePanel(
      width = width, height = height, screen = screenClass(width, height)
    )
    screen = canvas.screen
    Game.canvas = canvas
    Game.width = width
    Game.height = height
    return canvas
  }

  fun useScreen(newScreen: InstantiateScreen) {
    canvas?.screen?.unload()
    canvas?.screen = newScreen(width, height)
    screen = canvas?.screen
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
    screen?.update(deltaTime)
    timeLastRunMs = t

    // asynchronously signals the paint to happen in the swing thread
    canvas?.repaint()

    // use a lock here that is only released once the paintComponent
    // has happened so that canvas.repaint() calls don't queue up that
    // are delayed and we get jerky drawing
    waitForPaint()

    return System.currentTimeMillis() - t
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
