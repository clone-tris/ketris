package com.ketris.screens.test

import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.framework.engine.GraphicsPainter

class Screen() : GameScreen {
  override val painter = GraphicsPainter(Game.width, Game.height)

  override fun paint() {

  }
}
