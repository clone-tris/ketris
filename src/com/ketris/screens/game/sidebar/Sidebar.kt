package com.ketris.screens.game.sidebar

import com.ketris.framework.engine.GameScreen
import com.ketris.screens.game.Shape

class Sidebar(val width: Int, val height: Int, var nextPlayer: Shape) : GameScreen {
  override val painter = Painter(width, height)

  override fun paint() {
    painter.background()
    painter.drawShape(shape = nextPlayer, row = 1, column = 1)
    painter.drawLevel()
    painter.drawLinesCleared()
    painter.drawScore()
  }
}
