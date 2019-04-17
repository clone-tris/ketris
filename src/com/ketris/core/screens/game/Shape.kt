package com.ketris.core.screens.game

import com.ketris.core.Config.PUZZLE_HEIGHT
import com.ketris.core.Config.PUZZLE_WIDTH
import java.awt.Color

class Shape(var grid: List<Square>, var row: Int, var column: Int, var color: Color? = null) {
  var height: Int = 0
  var width: Int = 0

  init {
    computeSize()
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

  fun absoluteGrid(): List<Square> {
    return grid.map { Square(it.row + row, it.column + column) }
  }
}
