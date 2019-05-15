package com.ketris

import com.ketris.framework.engine.Game
import com.ketris.screens.test.Screen as TestScreen

fun main(args: Array<String>) {
  java.awt.EventQueue.invokeLater {
    Game.create(
      screenClass = ::TestScreen,
      width = GameConfig.CANVAS_WIDTH,
      height = GameConfig.CANVAS_HEIGHT
    )

    Window(title = "Ketris", canvas = Game.canvas)

    Game.start()
  }
}
