package com.ketris.core.screens.game

import com.ketris.core.Config.PUZZLE_HEIGHT
import com.ketris.core.Config.PUZZLE_WIDTH
import com.ketris.core.screens.game.UIColors.DEFAULT_SQUARE_COLOR
import java.awt.Color

class Shape(
  var grid: List<Square>,
  var row: Int,
  var column: Int,
  val color: Color = DEFAULT_SQUARE_COLOR,
  var width: Int = 0,
  var height: Int = 0,
  var computeHeight: Boolean = true
) {
  init {
    if (computeHeight) {
      computeSize()
    }
    grid.forEach { square -> square.color = color }
  }

  private fun computeSize() {
    var minRow = PUZZLE_HEIGHT
    var maxRow = 0
    var minColumn = PUZZLE_WIDTH
    var maxColumn = 0

    grid.forEach { cell ->
      if (cell.row > maxRow) {
        maxRow = cell.row
      }
      if (cell.column > maxColumn) {
        maxColumn = cell.column
      }
      if (cell.row < minRow) {
        minRow = cell.row
      }
      if (cell.column < minColumn) {
        minColumn = cell.column
      }
    }

    height = maxRow - minRow + 1
    width = maxColumn - minColumn + 1
  }

  fun absoluteGrid(translateRow: Int = 0, translateColumn: Int = 0): List<Square> {
    return grid.map {
      it.copy(row = it.row + row + translateRow, column = it.column + column + translateColumn)
    }
  }

  fun copy(): Shape {
    return Shape(
      grid = grid.map { it.copy() },
      row = row,
      column = column,
      color = color,
      width = width,
      height = height,
      computeHeight = computeHeight
    )
  }

  fun collidesWith(b: Shape): Boolean {
    return absoluteGrid().any { cellA ->
      b.absoluteGrid().any { cellB ->
        listOf(cellB.row, cellB.column) == listOf(cellA.row, cellA.column)
      }
    }
  }

  fun withinBounds(): Boolean {
    val absoluteMatrix = absoluteGrid()

    val afterRight = absoluteMatrix.any { square -> square.column >= PUZZLE_WIDTH }
    if (afterRight) {
      return false
    }

    val bellowBottom = absoluteMatrix.any { square -> square.row >= PUZZLE_HEIGHT }
    if (bellowBottom) {
      return false
    }

    val beforeLeft = absoluteMatrix.any { square -> square.column < 0 }
    if (beforeLeft) {
      return false
    }

    return true
  }

  fun rotate() {
    grid = grid.map { it.copy(row = it.column, column = height - it.row - 1) }
    computeSize()
  }

  fun move(rowDirection: Int, columnDirection: Int) {
    row += rowDirection
    column += columnDirection
  }
}
