package com.ketris.screens.game

import java.awt.Color

object UIColors {
  val BACKGROUND: Color = Color(0x333333)
  val SIDEBAR_BACKGROUND: Color = Color(0x545454)
  val DEFAULT_SQUARE_COLOR: Color = Color(0xCC8081)
  val GUIDE: Color = Color(0x555555)
}

enum class ShapeColors(val color: Color) {
  CYAN(Color(0x6DECEE)),
  BLUE(Color(0x0014E6)),
  ORANGE(Color(0xE4A338)),
  YELLOW(Color(0xF0EF4F)),
  GREEN(Color(0x6EEB47)),
  PURPLE(Color(0x9225E7)),
  RED(Color(0xDC2F20))
}
