package com.ketris.screens.game

import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.framework.io.IListenToKeyboard
import com.ketris.framework.io.KeyManager
import com.ketris.screens.game.Config.SIDEBAR_WIDTH
import com.ketris.screens.game.Config.WAR_ZONE_WIDTH
import com.ketris.screens.game.playfield.Playfield
import com.ketris.screens.game.sidebar.Sidebar
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage

class Screen : GameScreen, IListenToKeyboard {
  private var nextFall = 0L
  private var wasAnimating = false
  override val painter = Painter(SIDEBAR_WIDTH + WAR_ZONE_WIDTH, Game.height)
  private var playerIsFalling = false
  private val playfield = Playfield(WAR_ZONE_WIDTH, Game.height)
  private val sidebar = Sidebar(SIDEBAR_WIDTH, Game.height, playfield.nextPlayer)
  private var paused = false
  private var remainingAfterPaused = 0L

  init {
    KeyManager.addListener(this)
  }

  override fun update(dt: Int) {
    if (paused) {
      return
    }
    if (playfield.animating) {
      wasAnimating = true
    } else if (wasAnimating) {
      nextFall = System.currentTimeMillis() + playfield.fallRate
      wasAnimating = false
    }
    if (!wasAnimating) {
      applyGravity()
    }
  }

  private fun applyGravity() {
    val time = System.currentTimeMillis()
    if (time >= nextFall) {
      nextFall = time + playfield.fallRate
      handlePlayerFalling()
    }
  }

  private fun handlePlayerFalling() {
    if (playerIsFalling) {
      return
    }
    playerIsFalling = true
    val ableToMove = playfield.fallDown()
    if (!ableToMove && playfield.onFloor) {
      nextFall = playfield.endOfLock
      sidebar.nextPlayer = playfield.nextPlayer
    }
    playerIsFalling = false
  }

  override fun keyPressed(e: KeyEvent) {
    if (paused) {
      when (e.keyCode) {
        KeyEvent.VK_P -> togglePaused()
      }
    } else {
      when (e.keyCode) {
        KeyEvent.VK_W -> playfield.rotatePlayer()
        KeyEvent.VK_S -> handlePlayerFalling()
        KeyEvent.VK_A -> playfield.moveLeft()
        KeyEvent.VK_D -> playfield.moveRight()
        KeyEvent.VK_R -> playfield.restart()
        KeyEvent.VK_I -> playfield.inspect = true
        KeyEvent.VK_P -> togglePaused()
      }
    }
  }

  private fun togglePaused() {
    paused = !paused
    val time = System.currentTimeMillis()
    if (paused) {
      val remaining = nextFall - time
      remainingAfterPaused = if (remaining >= 0) remaining else 0
    } else {
      nextFall = time + remainingAfterPaused
    }
  }

  override fun paint() {
    playfield.paint()
    sidebar.paint()
  }

  override fun paintCanvas(): BufferedImage {
    val playfieldBuffer = playfield.paintCanvas()
    val sidebarBuffer = sidebar.paintCanvas()

    painter.g.drawImage(sidebarBuffer, 0, 0, SIDEBAR_WIDTH, Game.height, null)
    painter.g.drawImage(playfieldBuffer, SIDEBAR_WIDTH, 0, WAR_ZONE_WIDTH, Game.height, null)

    return painter.canvas()
  }
}
