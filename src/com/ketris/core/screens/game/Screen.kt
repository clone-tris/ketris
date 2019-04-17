package com.ketris.core.screens.game

import com.ketris.core.framework.events.*
import com.ketris.core.screens.game.Colors.DEFAULT_SQUARE_COLOR
import java.awt.event.KeyEvent

class Screen : IScreen {
  private var nextFall = 0L
  private var fallRate = 1000L

  private val player = Shape(
    arrayOf(
      intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 1)
    ), 0, 0, DEFAULT_SQUARE_COLOR
  )

  override fun update(dt: Int) {
    val time = System.currentTimeMillis()
    if (time >= nextFall) {
      nextFall = time + fallRate
      player.fallDown()
    }
  }

  fun keyPressed(e: KeyEvent?) {
    when (e?.keyCode) {
      KeyEvent.VK_W -> player.rotate()
      KeyEvent.VK_S -> player.fallDown()
      KeyEvent.VK_A -> player.moveLeft()
      KeyEvent.VK_D -> player.moveRight()
    }
  }

  override fun paint(p: Painter) {
    paintBackground(p)
    paintPlayer(p)

    // keep the following last as it need to be on top of everything
    paintDebugSection(p)
  }

  private fun paintDebugSection(p: Painter) {
    paintFPS(p)
  }

  private fun paintFPS(p: Painter) {
    p.drawText(text = p.fps.value().toString(), x = 10, y = 10)
  }

  private fun paintBackground(p: Painter) {
    p.drawGuide()
  }

  private fun paintPlayer(p: Painter) {
    p.drawShape(player)
  }
}
