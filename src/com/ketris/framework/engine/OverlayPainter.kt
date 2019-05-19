package com.ketris.framework.engine

import com.ketris.UIColors
import java.awt.Color

class OverlayPainter(width: Int, height: Int) : GraphicsPainter(width, height) {
  fun drawFPS(fps: Int) {
    g.color = UIColors.POPUP_BACKGROUND
    g.fillRect(10, height - 40, 100, 30)
    g.color = Color.WHITE
    g.drawString("fps: $fps", 20, height - 20)
  }
}
