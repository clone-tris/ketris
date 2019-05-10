package com.ketris.screens.game.playfield

import com.ketris.GameConfig.DEBUG_GRAPHICS
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.framework.engine.GraphicsPainter
import com.ketris.framework.io.IListenToKeyboard
import com.ketris.framework.io.KeyManager
import com.ketris.screens.game.Config.SIDEBAR_WIDTH
import com.ketris.screens.game.Config.WAR_ZONE_WIDTH
import com.ketris.screens.game.sidebar.Sidebar
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage

class Screen(val game: Game, val width: Int, val height: Int) : GameScreen, IListenToKeyboard {
  private val commander = Commander()
  private var nextFall = 0L
  private var fallRate = 1000L
  private var wasAnimating = false
  override val painter = Painter(WAR_ZONE_WIDTH, height)
  private val sideBar = Sidebar(SIDEBAR_WIDTH, height, commander.nextPlayer)
  private val stitcher = GraphicsPainter(SIDEBAR_WIDTH + WAR_ZONE_WIDTH, height)

  init {
    KeyManager.addListener(this)
  }

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
      handlePlayerFalling()
    }
  }

  private fun handlePlayerFalling() {
    val weHaveANewPlayer = commander.fallDown()
    if (weHaveANewPlayer) {
      sideBar.nextPlayer = commander.nextPlayer
    }
  }

  override fun keyPressed(e: KeyEvent) {
    if (!commander.gameEnded) {
      when (e.keyCode) {
        KeyEvent.VK_W -> commander.rotatePlayer()
        KeyEvent.VK_S -> handlePlayerFalling()
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

  override fun paintCanvas(): BufferedImage {
    val ketrisBuffer = super.paintCanvas()
    val sidebarBuffer = sideBar.paintCanvas()

    stitcher.g.drawImage(sidebarBuffer, 0, 0, SIDEBAR_WIDTH, height, null)
    stitcher.g.drawImage(ketrisBuffer, SIDEBAR_WIDTH, 0, WAR_ZONE_WIDTH, height, null)

    return stitcher.canvas()
  }
}
