package com.ketris.framework.engine

import com.ketris.framework.components.Button
import java.awt.*
import java.awt.image.BufferedImage
import java.awt.image.IndexColorModel
import com.ketris.screens.game.ShapeColors

open class GraphicsPainter(val width: Int, val height: Int) {
  val buffer = BufferedImage(width, height, IndexColorModel.TRANSLUCENT)
  val g = buffer.graphics as Graphics2D

  init {
    val rh = RenderingHints(
      RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
    )
    rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
    g.setRenderingHints(rh)
  }

  fun drawButton(
    button: Button, color: Color = ShapeColors.CYAN.color
  ) {
    g.color = color
    g.drawRoundRect(button.x, button.y, button.width, button.height, 3, 3)
    drawText(text = button.text, x = button.tx, y = button.ty, color = color)
  }

  fun fillButton(
    button: Button, background: Color = ShapeColors.CYAN.color, color: Color = Color(0x222222)
  ) {
    g.color = background
    g.fillRoundRect(button.x, button.y, button.width, button.height, 3, 3)
    g.color = color
    g.drawRoundRect(button.x, button.y, button.width, button.height, 3, 3)
    drawText(text = button.text, x = button.tx, y = button.ty, color = color)
  }

  private fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color, strokeWidth: Int) {
    g.stroke = BasicStroke(strokeWidth.toFloat())
    g.color = color
    g.drawLine(x1, y1, x2, y2)
  }

  fun drawText(
    text: String, x: Int, y: Int, fontSize: Int = 12, color: Color = Color(0xffffff)
  ) {
    g.color = color
    g.font = Font(Font.MONOSPACED, Font.BOLD, fontSize)
    g.drawString(text, x, y + fontSize)
  }
}
