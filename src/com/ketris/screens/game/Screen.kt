package com.ketris.screens.game

import com.ketris.framework.engine.GraphicsPainter
import com.ketris.framework.engine.IScreen
import java.awt.event.KeyEvent

class Screen : IScreen {
  private val commander = Commander()
  private var nextFall = 0L
  private var fallRate = 1000L
  private var wasAnimating = false
  override val painterClass = ::Painter

  override fun update(dt: Int) {
    if (commander.animating) {
      wasAnimating = true
    } else if (wasAnimating) {
      nextFall = System.currentTimeMillis() + fallRate
      wasAnimating = false
    }
    if (!commander.gameEnded && !wasAnimating) {
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

  override fun keyPressed(e: KeyEvent) {
    if (!commander.gameEnded) {
      when (e.keyCode) {
        KeyEvent.VK_W -> commander.rotatePlayer()
        KeyEvent.VK_S -> commander.fallDown()
        KeyEvent.VK_A -> commander.moveLeft()
        KeyEvent.VK_D -> commander.moveRight()
        KeyEvent.VK_R -> commander.restart()
        KeyEvent.VK_I -> commander.inspect = true
        KeyEvent.VK_P -> commander.animating = !commander.animating
      }
    } else {

    }
  }

  override fun paint(p: GraphicsPainter) {
    val painter = p as Painter
    painter.drawBackground()
    painter.drawShape(commander.player)
    painter.drawShape(commander.opponent)

    // keep the following last as it need to be on top of everything

    @Suppress("ConstantConditionIf")//
    if (painter.debug) {
      painter.drawFPS()
      painter.drawPlayerInfo(commander.player)
    }
  }
}
