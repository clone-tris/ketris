package com.ketris.framework.engine

import java.awt.*
import java.awt.image.BufferedImage
import java.awt.image.IndexColorModel

abstract class GraphicsPainter(val width: Int, val height: Int) {
  private val buffer = BufferedImage(width, height, IndexColorModel.TRANSLUCENT)
  val g = buffer.graphics as Graphics2D

  init {
    val rh = RenderingHints(
      RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
    )
    rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
    g.setRenderingHints(rh)
  }

  fun canvas(): BufferedImage {
    return buffer
  }

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
