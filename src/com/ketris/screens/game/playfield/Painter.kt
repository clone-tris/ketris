package com.ketris.screens.game.playfield

import com.ketris.framework.engine.GraphicsPainter
import com.ketris.GameConfig.DEBUG_GRAPHICS
import com.ketris.screens.game.Config.SQUARE_BORDER_WIDTH
import com.ketris.screens.game.Config.SQUARE_WIDTH
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

  fun drawFPS() {
//    val fps = fps.value().toString()
//    drawText(text = "FPS : $fps", x = 10, y = 10)
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
        column * SQUARE_WIDTH,
        row * SQUARE_WIDTH,
        shape.width * SQUARE_WIDTH,
        shape.height * SQUARE_WIDTH
      )
    }
  }

  private fun drawSquareAt(row: Int, column: Int, color: Color) {
    val border = color.darker()

    drawTetrominoSquare(
      column * SQUARE_WIDTH, row * SQUARE_WIDTH, color, border
    )

    if (DEBUG_GRAPHICS) {
      val margin = 10 * SQUARE_WIDTH / 100
      drawText("$row", column * SQUARE_WIDTH + margin, row * SQUARE_WIDTH + margin)
      drawText("$column", column * SQUARE_WIDTH + margin, row * SQUARE_WIDTH + margin + 12 + margin)
    }
  }

  private fun drawTetrominoSquare(
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

  open fun clear() {
    g.color = BACKGROUND
    g.fillRect(0, 0, width, height)
  }

  fun drawGuide(bounds: Rectangle = Rectangle(0, 0, width, height)) {
    val puzzleHeight = bounds.height / SQUARE_WIDTH
    val puzzleWidth = bounds.width / SQUARE_WIDTH

    for (i in 0 until puzzleHeight + 1) {
      val y = bounds.x + i * SQUARE_WIDTH
      drawLine(
        bounds.x, y, bounds.x + bounds.width, y, UIColors.GUIDE, 1
      )
    }

    for (i in 0 until puzzleWidth + 1) {
      val x = bounds.y + i * SQUARE_WIDTH
      drawLine(
        x, bounds.y, x, bounds.y + bounds.height, UIColors.GUIDE, 1
      )
    }
  }
}
