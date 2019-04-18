package com.ketris.core.screens.game

import com.ketris.core.screens.game.Square as S

enum class Shapes(val grid: List<S>) {
  T(
    listOf(
      S(0, 0), S(0, 1), S(0, 2),
      /*     */S(1, 1)
    )
  ),
  Z(
    listOf(
      S(0, 0), S(0, 1),
      /*     */S(1, 1), S(1, 2)
    )
  ),
  ZMirrored(
    listOf(
      /*         */S(0, 1), S(0, 2),//
      /**/S(1, 0), S(1, 1)//
    )
  ),
  L(
    listOf(
      S(0, 0), S(0, 1), S(0, 2), //
      S(1, 0)
    )
  ),
  LMirrored(
    listOf(
      S(0, 0), //
      S(1, 0), S(1, 1), S(1, 2)
    )
  ),
  Square(
    listOf(
      S(0, 0), S(0, 1), //
      S(1, 0), S(1, 1)
    )
  ),
  I(
    listOf(
      S(0, 0), S(0, 1), S(0, 2), S(1, 1)
    )
  )
}
