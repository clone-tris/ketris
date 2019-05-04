package com.ketris.screens.game.sidebar

import com.ketris.framework.engine.GameScreen

class Sidebar(width: Int, height: Int) : GameScreen {
  override val painter = Painter(width, height)

  override fun paint() {
    painter.background()
  }
}
