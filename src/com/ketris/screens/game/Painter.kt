package com.ketris.screens.game

import com.ketris.Config
import com.ketris.Config.PUZZLE_HEIGHT
import com.ketris.Config.PUZZLE_WIDTH
import com.ketris.framework.engine.GameFPS
import com.ketris.Config.SQUARE_BORDER_WIDTH
import com.ketris.Config.SQUARE_WIDTH
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

class Painter(val g: Graphics2D, var dt: Int, var fps: GameFPS, var debug: Boolean) {
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

  fun drawShape(shape: Shape) {
    shape.grid.forEach { square ->
      drawSquareAt(
        shape.row + square.row, shape.column + square.column, square.color
      )
    }

    if (debug) {
      g.color = Color.BLUE
      g.drawRect(
        shape.column * SQUARE_WIDTH,
        shape.row * SQUARE_WIDTH,
        shape.width * SQUARE_WIDTH,
        shape.height * SQUARE_WIDTH
      )
    }
  }

  private fun drawSquareAt(row: Int, column: Int, color: Color) {
    val border = color.darker()

    drawSquare(
      column * SQUARE_WIDTH, row * SQUARE_WIDTH, color, border
    )

    if (debug) {
      val margin = 10 * SQUARE_WIDTH / 100
      drawText("$row", column * SQUARE_WIDTH + margin, row * SQUARE_WIDTH + margin)
      drawText("$column", column * SQUARE_WIDTH + margin, row * SQUARE_WIDTH + margin + 12 + margin)
    }
  }

  private fun drawSquare(
    x: Int, y: Int, backgroundColor: Color, borderColor: Color
  ) {
    g.color = backgroundColor
    g.fillRect(x, y, SQUARE_WIDTH, SQUARE_WIDTH)

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

    for (i in 0 until PUZZLE_HEIGHT + 1) {
      drawLine(0, i * SQUARE_WIDTH, canvasWidth, i * SQUARE_WIDTH,
               UIColors.GUIDE, 1)
    }

    for (i in 0 until PUZZLE_WIDTH + 1) {
      drawLine(i * SQUARE_WIDTH, 0, i * SQUARE_WIDTH, canvasHeight,
               UIColors.GUIDE, 1)
    }
  }
}
