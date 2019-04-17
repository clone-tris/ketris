package com.ketris.core.screens.game

import com.ketris.core.screens.game.Colors.DEFAULT_SQUARE_COLOR
import java.awt.Color

class Square(var row: Int, var column: Int, var color: Color = DEFAULT_SQUARE_COLOR) {
  fun coordinates(): List<Int> {
    return listOf(row, column)
  }
}
