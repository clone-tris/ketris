package com.ketris.screens.game.playfield

import com.ketris.framework.engine.GraphicsPainter
import com.ketris.GameConfig.DEBUG_GRAPHICS
import com.ketris.screens.game.Config.SQUARE_BORDER_WIDTH as BORDER
import com.ketris.screens.game.Config.SQUARE_WIDTH as WIDTH
import com.ketris.screens.game.Shape
import com.ketris.screens.game.UIColors
import com.ketris.screens.game.UIColors.BACKGROUND
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Rectangle

open class Painter(width: Int, height: Int) : GraphicsPainter(width, height) {
  fun drawBackground() {
    clear()
    drawGuide()
  }

  fun drawPlayerInfo(player: Shape) {
    drawText(text = "r/c ${player.row}, ${player.column}", x = 10, y = 25)
  }

  private fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color, strokeWidth: Int) {
    g.stroke = BasicStroke(strokeWidth.toFloat())
    g.color = color
    g.drawLine(x1, y1, x2, y2)
  }

  fun drawShape(shape: Shape, row: Int = shape.row, column: Int = shape.column) {
    shape.grid.forEach { square ->
      drawSquareAt(row + square.row, column + square.column, square.color)
    }

    if (DEBUG_GRAPHICS) {
      g.color = Color.BLUE
      g.drawRect(
        column * WIDTH, row * WIDTH, shape.width * WIDTH, shape.height * WIDTH
      )
    }
  }

  private fun drawSquareAt(row: Int, column: Int, color: Color) {
    val border = color.darker()

    drawTetrominoSquare(
      column * WIDTH, row * WIDTH, color, border
    )

    if (DEBUG_GRAPHICS) {
      val margin = 10 * WIDTH / 100
      drawText("$row", column * WIDTH + margin, row * WIDTH + margin)
      drawText("$column", column * WIDTH + margin, row * WIDTH + margin + 12 + margin)
    }
  }

  val top = Color(255, 255, 255, 255 * 7 / 10)
  val bottom = Color(0, 0, 0, 255 * 5 / 10)
  val sides = Color(0, 0, 0, 255 * 1 / 10)

  private fun drawTetrominoSquare(
    x: Int, y: Int, backgroundColor: Color, borderColor: Color
  ) {
    g.color = backgroundColor
    g.fillRect(x, y, WIDTH, WIDTH)

    g.color = sides
    g.fillPolygon(
      intArrayOf(x, x + BORDER, x + BORDER, x),
      intArrayOf(y, y + BORDER, y + WIDTH - BORDER, y + WIDTH),
      4
    )
    g.fillPolygon(
      intArrayOf(x + WIDTH, x + WIDTH - BORDER, x + WIDTH - BORDER, x + WIDTH),
      intArrayOf(y, y + BORDER, y + WIDTH - BORDER, y + WIDTH),
      4
    )

    g.color = top
    g.fillPolygon(
      intArrayOf(x, x + BORDER, x + WIDTH - BORDER, x + WIDTH),
      intArrayOf(y, y + BORDER, y + BORDER, y),
      4
    )

    g.color = bottom
    g.fillPolygon(
      intArrayOf(x, x + BORDER, x + WIDTH - BORDER, x + WIDTH),
      intArrayOf(y + WIDTH, y + WIDTH - BORDER, y + WIDTH - BORDER, y),
      4
    )
  }

  open fun clear() {
    g.color = BACKGROUND
    g.fillRect(0, 0, width, height)
  }

  fun drawGuide(bounds: Rectangle = Rectangle(0, 0, width, height)) {
    val puzzleHeight = bounds.height / WIDTH
    val puzzleWidth = bounds.width / WIDTH

    for (i in 0 until puzzleHeight + 1) {
      val y = bounds.x + i * WIDTH
      drawLine(
        bounds.x, y, bounds.x + bounds.width, y, UIColors.GUIDE, 1
      )
    }

    for (i in 0 until puzzleWidth + 1) {
      val x = bounds.y + i * WIDTH
      drawLine(
        x, bounds.y, x, bounds.y + bounds.height, UIColors.GUIDE, 1
      )
    }
  }
}
