package com.ketris.screens.game.sidebar

import com.ketris.framework.engine.GraphicsPainter
import java.awt.Color

open class Painter(width: Int, height: Int) : GraphicsPainter(width, height) {
  fun paintTest() {
    g.color = Color.BLACK
    g.drawString("test", 20, 20)
  }
}
