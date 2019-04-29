package com.ketris.screens.game.warzone

import com.ketris.Config
import com.ketris.Config.DEBUG_GRAPHICS
import com.ketris.Config.PUZZLE_HEIGHT
import com.ketris.Config.PUZZLE_WIDTH
import com.ketris.Config.SQUARE_BORDER_WIDTH
import com.ketris.Config.SQUARE_WIDTH
import com.ketris.framework.engine.GraphicsPainter
import com.ketris.screens.game.Shape
import com.ketris.screens.game.UIColors
import com.ketris.screens.game.UIColors.BACKGROUND
import java.awt.BasicStroke
import java.awt.Color

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

  fun drawShape(shape: Shape) {
    shape.grid.forEach { square ->
      drawSquareAt(
        shape.row + square.row, shape.column + square.column, square.color
      )
    }

    if (DEBUG_GRAPHICS) {
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

  fun clear() {
    g.color = BACKGROUND
    g.fillRect(0, 0, width, height)
  }

  private fun drawGuide() {
    val canvasHeight = Config.CANVAS_HEIGHT
    val canvasWidth = Config.WAR_ZONE_WIDTH

    for (i in 0 until PUZZLE_HEIGHT + 1) {
      drawLine(
        0, i * SQUARE_WIDTH, canvasWidth, i * SQUARE_WIDTH,
        UIColors.GUIDE, 1
      )
    }

    for (i in 0 until PUZZLE_WIDTH + 1) {
      drawLine(
        i * SQUARE_WIDTH, 0, i * SQUARE_WIDTH, canvasHeight,
        UIColors.GUIDE, 1
      )
    }
  }
}