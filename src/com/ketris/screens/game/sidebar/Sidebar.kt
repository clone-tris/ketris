package com.ketris.screens.game.sidebar

import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen

class Sidebar(
  game: Game, width: Int, height: Int
) : GameScreen(game, width, height) {
  override val painter = Painter(width, height)

  override fun paint() {
    painter.paintTest()
  }

}
