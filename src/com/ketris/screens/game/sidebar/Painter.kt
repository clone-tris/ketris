package com.ketris.screens.game.sidebar

import com.ketris.framework.engine.GraphicsPainter
import java.awt.Color

open class Painter(width: Int, height: Int) : GraphicsPainter(width, height) {
  fun background() {
    g.color = Color.DARK_GRAY
    g.fillRect(0, 0, width, height)
  }
}
