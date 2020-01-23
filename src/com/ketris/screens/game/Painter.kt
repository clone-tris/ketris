package com.ketris.screens.game

import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GraphicsPainter
import java.awt.image.BufferedImage

open class Painter(width: Int, height: Int) : GraphicsPainter(width, height) {
  fun stitch(sidebarBuffer: BufferedImage, playfieldBuffer: BufferedImage): BufferedImage {
    g.drawImage(sidebarBuffer, 0, 0, Config.SIDEBAR_WIDTH, Game.height, null)
    g.drawImage(playfieldBuffer, Config.SIDEBAR_WIDTH, 0, Config.WAR_ZONE_WIDTH, Game.height, null)
    return buffer
  }
}
