package com.ketris.screens.game

import com.ketris.Config.DEBUG_GRAPHICS
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import java.awt.event.KeyEvent

class Screen(game: Game, width: Int, height: Int) : GameScreen(game, width, height) {
  private val commander = Commander()
  private var nextFall = 0L
  private var fallRate = 1000L
  private var wasAnimating = false
  override val painter = Painter(width, height)

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
}
