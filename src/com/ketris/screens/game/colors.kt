package com.ketris.screens.game

import java.awt.Color

object UIColors {
  val BACKGROUND: Color = Color.decode("#333333")
  val DEFAULT_SQUARE_COLOR: Color = Color.decode("#CC8081")
  val GUIDE: Color = Color.decode("#555555")
}

enum class ShapeColors(val color: Color) {
  CYAN(Color.decode("#6DECEE")),
  BLUE(Color.decode("#0014E6")),
  ORANGE(Color.decode("#E4A338")),
  YELLOW(Color.decode("#F0EF4F")),
  GREEN(Color.decode("#6EEB47")),
  PURPLE(Color.decode("#9225E7")),
  RED(Color.decode("#DC2F20"))
}
