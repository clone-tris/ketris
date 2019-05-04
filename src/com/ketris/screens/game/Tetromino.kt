package com.ketris.screens.game

import java.awt.Color

enum class Tetromino(val grid: List<Square>, val color: Color) {
  T(
    listOf(
      Square(0, 0), Square(0, 1), Square(0, 2),
      /*          */Square(1, 1)
    ), ShapeColors.PURPLE.color
  ),
  Z(
    listOf(
      Square(0, 0), Square(0, 1),
      /*          */Square(1, 1), Square(1, 2)
    ),
    ShapeColors.RED.color
  ),
  S(
    listOf(
      /*              */Square(0, 1), Square(0, 2),//
      /**/Square(1, 0), Square(1, 1)//
    ),
    ShapeColors.GREEN.color
  ),
  L(
    listOf(
      Square(0, 0), Square(0, 1), Square(0, 2), //
      Square(1, 0)
    ),
    ShapeColors.ORANGE.color
  ),
  J(
    listOf(
      Square(0, 0), //
      Square(1, 0), Square(1, 1), Square(1, 2)
    ),
    ShapeColors.BLUE.color
  ),
  O(
    listOf(
      Square(0, 0), Square(0, 1), //
      Square(1, 0), Square(1, 1)
    ),
    ShapeColors.YELLOW.color
  ),
  I(
    listOf(
      Square(0, 0), Square(0, 1), Square(0, 2), Square(0, 3)
    ),
    ShapeColors.CYAN.color
  )
}
