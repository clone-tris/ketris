package com.ketris.core.screens.game

import com.ketris.core.framework.events.*
import java.awt.event.KeyEvent

class Screen : IScreen {
  private var nextFall = 0L
  private var fallRate = 1000L
  private val commander = Commander()

  override fun update(dt: Int) {
    if (!commander.ended) {
      applyGravity()
    }
  }

  private fun applyGravity() {
    val time = System.currentTimeMillis()
    if (time >= nextFall) {
      nextFall = time + fallRate
      commander.fallDown()
    }
  }

  fun keyPressed(e: KeyEvent?) {
    if (!commander.ended) {
      when (e?.keyCode) {
        KeyEvent.VK_W -> commander.rotate()
        KeyEvent.VK_S -> commander.fallDown()
        KeyEvent.VK_A -> commander.moveLeft()
        KeyEvent.VK_D -> commander.moveRight()
      }
    } else {

    }
  }

  override fun paint(p: Painter) {
    paintBackground(p)
    paintShapes(p)

    // keep the following last as it need to be on top of everything

    @Suppress("ConstantConditionIf")//
    if (p.debug) {
      paintDebugSection(p)
    }
  }

  private fun paintDebugSection(p: Painter) {
    paintFPS(p)
    paintPlayerInfo(p)
  }

  private fun paintFPS(p: Painter) {
    val fps = p.fps.value().toString()
    p.drawText(text = "FPS : $fps", x = 10, y = 10)
  }

  private fun paintPlayerInfo(p: Painter) {
    p.drawText(text = "r/c ${commander.player.row}, ${commander.player.column}", x = 10, y = 25)
  }

  private fun paintBackground(p: Painter) {
    p.drawGuide()
  }

  private fun paintShapes(p: Painter) {
    p.drawShape(commander.player)
    p.drawShape(commander.opponent)
  }
}
