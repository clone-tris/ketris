package com.ketris.core.screens.game

import com.ketris.core.Config
import com.ketris.core.framework.GameFPS
import com.ketris.core.screens.game.Colors.DEFAULT_SQUARE_BACKGROUND
import com.ketris.core.screens.game.Constants.SQUARE_BORDER_WIDTH
import com.ketris.core.screens.game.Constants.SQUARE_WIDTH
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

class Painter(val g: Graphics2D, var dt: Int, var fps: GameFPS) {
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

  fun drawSquareAt(row: Int, column: Int, background: Color = DEFAULT_SQUARE_BACKGROUND) {
    val border: Color = background.darker()

    drawSquare(
      column * SQUARE_WIDTH, row * SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH, background, border
    )
  }

  private fun drawSquare(
    x: Int, y: Int, width: Int, height: Int, backgroundColor: Color, borderColor: Color
  ) {
    g.color = backgroundColor
    g.fillRect(x, y, width, height)

    drawLine(x, y, x + SQUARE_WIDTH - 1, y, borderColor, SQUARE_BORDER_WIDTH)
    drawLine(x, y, x, y + SQUARE_WIDTH - 1, borderColor, SQUARE_BORDER_WIDTH)
    drawLine(
      x + SQUARE_WIDTH - 1,
      y,
      x + SQUARE_WIDTH - 1,
      y + SQUARE_WIDTH - 1,
      borderColor,
      SQUARE_BORDER_WIDTH
    )
    drawLine(
      x,
      y + SQUARE_WIDTH - 1,
      x + SQUARE_WIDTH - 1,
      y + SQUARE_WIDTH - 1,
      borderColor,
      SQUARE_BORDER_WIDTH
    )
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
