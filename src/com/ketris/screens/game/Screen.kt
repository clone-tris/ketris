package com.ketris.screens.game

import com.ketris.framework.engine.GameScreen
import com.ketris.framework.io.IListenToKeyboard
import com.ketris.framework.io.KeyManager
import com.ketris.screens.game.Config.SIDEBAR_WIDTH
import com.ketris.screens.game.Config.WAR_ZONE_WIDTH
import com.ketris.screens.game.playfield.Playfield
import com.ketris.screens.game.sidebar.Sidebar
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage

class Screen(val width: Int, val height: Int) : GameScreen, IListenToKeyboard {
  private var nextFall = 0L
  private var fallRate = 1000L
  private var wasAnimating = false
  override val painter = Painter(SIDEBAR_WIDTH + WAR_ZONE_WIDTH, height)

  private val playfield = Playfield(WAR_ZONE_WIDTH, height)
  private val sidebar = Sidebar(SIDEBAR_WIDTH, height, playfield.commander.nextPlayer)

  init {
    KeyManager.addListener(this)
  }

  override fun update(dt: Int) {
    if (playfield.commander.animating) {
      wasAnimating = true
    } else if (wasAnimating) {
      nextFall = System.currentTimeMillis() + fallRate
      wasAnimating = false
    }
    if (!playfield.commander.gameEnded && !wasAnimating) {
      applyGravity()
    }
  }

  private fun applyGravity() {
    val time = System.currentTimeMillis()
    if (time >= nextFall) {
      nextFall = time + fallRate
      handlePlayerFalling()
    }
  }

  private fun handlePlayerFalling() {
    val weHaveANewPlayer = playfield.commander.fallDown()
    if (weHaveANewPlayer) {
      sidebar.nextPlayer = playfield.commander.nextPlayer
    }
  }

  override fun keyPressed(e: KeyEvent) {
    if (!playfield.commander.gameEnded) {
      when (e.keyCode) {
        KeyEvent.VK_W -> playfield.commander.rotatePlayer()
        KeyEvent.VK_S -> handlePlayerFalling()
        KeyEvent.VK_A -> playfield.commander.moveLeft()
        KeyEvent.VK_D -> playfield.commander.moveRight()
        KeyEvent.VK_R -> playfield.commander.restart()
        KeyEvent.VK_I -> playfield.commander.inspect = true
        KeyEvent.VK_P -> playfield.commander.animating = !playfield.commander.animating
      }
    } else {

    }
  }

  override fun paint() {
    playfield.paint()
    sidebar.paint()
  }

  override fun paintCanvas(): BufferedImage {
    val playfieldBuffer = playfield.paintCanvas()
    val sidebarBuffer = sidebar.paintCanvas()

    painter.g.drawImage(sidebarBuffer, 0, 0, SIDEBAR_WIDTH, height, null)
    painter.g.drawImage(playfieldBuffer, SIDEBAR_WIDTH, 0, WAR_ZONE_WIDTH, height, null)

    return painter.canvas()
  }
}
