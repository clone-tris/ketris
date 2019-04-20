package com.ketris.core.screens.game

enum class Shapes(val grid: List<Square>) {
  T(
    listOf(
      Square(0, 0), Square(0, 1), Square(0, 2),
      /*          */Square(1, 1)
    )
  ),
//  Z(
//    listOf(
//      Square(0, 0), Square(0, 1),
//      /*          */Square(1, 1), Square(1, 2)
//    )
//  ),
//  S(
//    listOf(
//      /*              */Square(0, 1), Square(0, 2),//
//      /**/Square(1, 0), Square(1, 1)//
//    )
//  ),
//  L(
//    listOf(
//      Square(0, 0), Square(0, 1), Square(0, 2), //
//      Square(1, 0)
//    )
//  ),
//  J(
//    listOf(
//      Square(0, 0), //
//      Square(1, 0), Square(1, 1), Square(1, 2)
//    )
//  ),
  O(
    listOf(
      Square(0, 0), Square(0, 1), //
      Square(1, 0), Square(1, 1)
    )
  ),
//  I(
//    listOf(
//      Square(0, 0), Square(0, 1), Square(0, 2), Square(0, 3)
//    )
//  )
}
