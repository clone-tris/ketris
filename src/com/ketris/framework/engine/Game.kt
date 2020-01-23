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
        val sleepDuration = FRAME_SIZE - redrawDuration
        if (sleepDuration > 0) {
          Thread.sleep(sleepDuration)
        }
      } catch (e: InterruptedException) {
        e.printStackTrace()
      }
    }
  }

  private fun redraw(): Long {
    val beforeUpdate = System.currentTimeMillis()
    val dt: Int = (beforeUpdate - timeLastRunMs).toInt()
    this.dt = dt
    canvas.screen.update(dt)
    canvas.repaint()
    timeLastRunMs = System.currentTimeMillis()
    return timeLastRunMs - beforeUpdate
  }
}
