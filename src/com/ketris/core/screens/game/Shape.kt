package com.ketris.core.screens.game

import com.ketris.core.Config.PUZZLE_HEIGHT
import com.ketris.core.Config.PUZZLE_WIDTH
import java.awt.Color

class Shape(
  var grid: List<Square>,
  var row: Int,
  var column: Int,
  val color: Color? = null,
  var width: Int = 0,
  var height: Int = 0,
  var computeHeight: Boolean = true
) {
  init {
    if (computeHeight) {
      computeSize()
    }
    if (color != null) {
      grid.forEach { square -> square.color = color }
    }
  }

  fun computeSize() {
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
}
