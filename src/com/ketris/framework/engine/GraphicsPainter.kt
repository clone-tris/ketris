package com.ketris.framework.engine

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

open class GraphicsPainter(val g: Graphics2D, var fps: GameFPS, var debug: Boolean) {
  private fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color, strokeWidth: Int) {
    g.stroke = BasicStroke(strokeWidth.toFloat())
    g.color = color
    g.drawLine(x1, y1, x2, y2)
  }

  fun drawText(
    text: String, x: Int, y: Int, fontSize: Int = 12, color: Color = Color.decode("#ffffff")
  ) {
    g.color = color
    g.font = Font("Verdana", Font.BOLD, fontSize)
    g.drawString(text, x, y + fontSize)
  }
}
