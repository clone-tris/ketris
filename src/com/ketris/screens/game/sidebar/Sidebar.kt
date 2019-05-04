package com.ketris.screens.game.sidebar

import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.screens.game.Shape

class Sidebar(game: Game, width: Int, height: Int) : GameScreen() {
  override val painter = Painter(width, height)

  override fun paint() {
    painter.background()
  }
}
