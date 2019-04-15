package com.ketris.core.screens.game

import com.ketris.core.framework.*
import com.ketris.core.screens.game.Constants.SQUARE_WIDTH
import java.awt.Color
import java.awt.Graphics2D

class Screen : IScreen {
  private val player = Player(100f, 100f, 30f, 30f)


  override fun update(dt: Int) {
    // move 400 pixels per second
    val translate = 400 * dt / 1000f
    // todo : try both thrad and if

    if (wIsDown) {
      player.y -= translate
    }
    if (sIsDown) {
      player.y += translate
    }

    if (aIsDown) {
      player.x -= translate
    }

    if (dIsDown) {
      player.x += translate
    }
  }

  override fun paint(g: Graphics2D, dt: Int) {
    val p = Painter(g, dt)

    paintBackground(p)
    paintPlayer(p)

    // keep the following last as it need to be on top of everything
    paintDebugSection(p)
  }

  private fun paintDebugSection(p: Painter) {
    p.drawText(text = p.dt.toString(), x = 10, y = 10)
  }

  private fun paintBackground(p: Painter) {
    p.drawGuide()
  }

  private fun paintPlayer(p: Painter) {
    p.drawSquareAt((player.y / SQUARE_WIDTH).toInt(), (player.x / SQUARE_WIDTH).toInt())
  }
}
