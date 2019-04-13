package com.ketris.core.screens.game

import com.ketris.core.Config
import com.ketris.core.screens.game.Constants.SQUARE_WIDTH
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D

class Painter(val g: Graphics2D) {
  private fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color, strokeWidth: Int) {
    g.stroke = BasicStroke(strokeWidth.toFloat())
    g.color = color
    g.drawLine(x1, y1, x2, y2)
  }

  fun drawGuide() {
    val canvasHeight = Config.CANVAS_HEIGHT
    val canvasWidth = Config.CANVAS_WIDTH

    for (i in 0 until canvasHeight / SQUARE_WIDTH + 1) {
      drawLine(0, i * SQUARE_WIDTH, canvasWidth, i * SQUARE_WIDTH, Colors.GUIDE, 1)
    }

    for (i in 0 until canvasWidth / SQUARE_WIDTH + 1) {
      drawLine(i * SQUARE_WIDTH, 0, i * SQUARE_WIDTH, canvasHeight, Colors.GUIDE, 1)
    }
  }
}
