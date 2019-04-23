package com.ketris.core.screens.game

import com.ketris.core.screens.game.UIColors.DEFAULT_SQUARE_COLOR
import java.awt.Color

val Color.hex: String
  get() {
    return "#" + Integer.toHexString(this.rgb).substring(2)
  }

data class Square(var row: Int, var column: Int, var color: Color = DEFAULT_SQUARE_COLOR) {
  override fun toString(): String {
    return "{$row, $column, ${color.hex}}"
  }
}
