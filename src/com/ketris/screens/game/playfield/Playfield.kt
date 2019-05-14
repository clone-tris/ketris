package com.ketris.screens.game.playfield

import com.ketris.GameConfig.DEBUG_GRAPHICS
import com.ketris.framework.engine.GameScreen

class Playfield(width: Int, height: Int) : GameScreen {
  val commander = Commander()

  override fun paint() {
    painter.drawBackground()
    painter.drawShape(commander.player)
    painter.drawShape(commander.opponent)

    // keep the following last as it need to be on top of everything
    if (DEBUG_GRAPHICS) {
      painter.drawFPS()
      painter.drawPlayerInfo(commander.player)
    }
  }

  override val painter = Painter(width, height)
}
