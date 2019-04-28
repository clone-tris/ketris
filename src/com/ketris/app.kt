package com.ketris

import com.ketris.framework.engine.Game
import com.ketris.screens.game.Screen as MainScreen
import com.ketris.screens.loading.Screen as LoadingScreen

fun main(args: Array<String>) {
  java.awt.EventQueue.invokeLater {
    val game = Game(
      screen = ::LoadingScreen,
      width = Config.CANVAS_WIDTH,
      height = Config.CANVAS_HEIGHT
    )

    Window(title = "Ketris", canvas = game.canvas)
  }
}
