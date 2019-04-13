package com.ketris.core.screens.game

import com.ketris.core.framework.*
import java.awt.Color
import java.awt.Graphics2D

class Screen : IScreen {
  private val player = Player(100f, 100f, 30f, 30f)

  override fun update(dt: Int) {
    // move 400 pixels per second
    val translate = 400 * dt / 1000f

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

  override fun paint(g: Graphics2D) {
//     Before painting :
//     - in debug mode showing the guide is essential to know what is being done
    Painter(g).drawGuide()
//

    g.color = Color(54, 214, 250)
    g.fillRect(
      player.x.toInt(), player.y.toInt(), player.width.toInt(), player.height.toInt()
    )
  }
}
