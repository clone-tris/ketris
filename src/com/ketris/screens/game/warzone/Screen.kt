package com.ketris.screens.game.warzone

import com.ketris.GameConfig.DEBUG_GRAPHICS
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.framework.engine.GraphicsPainter
import com.ketris.screens.game.Config.SIDEBAR_WIDTH
import com.ketris.screens.game.Config.WAR_ZONE_WIDTH
import com.ketris.screens.game.sidebar.Sidebar
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage

class Screen(val game: Game, val width: Int, val height: Int) : GameScreen {
  private val commander = Commander()
  private var nextFall = 0L
  private var fallRate = 1000L
  private var wasAnimating = false
  override val painter = Painter(WAR_ZONE_WIDTH, height)
  val sideBar = Sidebar(SIDEBAR_WIDTH, height)
  val stitcher = GraphicsPainter(SIDEBAR_WIDTH + WAR_ZONE_WIDTH, height)

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
    // todo : add score
    // todo : level indicator
    // todo : removed lines count
    // todo : next shape preview

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
