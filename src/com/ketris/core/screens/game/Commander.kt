package com.ketris.core.screens.game

import java.awt.Color

class Commander {
  val player = Shape(
    listOf(
      listOf(0, 0), listOf(0, 1), listOf(0, 2), listOf(1, 1)
    ), 0, 0, Colors.DEFAULT_SQUARE_COLOR
  )

  val opponent = Shape(
    emptyList(), 0, 0, Color.ORANGE
  )

  private fun mayMove(rowDirection: Int, columnDirection: Int): Boolean {
    val futurePlayer = player.grid.map {
      listOf(
        it[0] + player.row + rowDirection, it[1] + player.column + columnDirection
      )
    }

    val opponentMatrix = opponent.grid.map {
      listOf(
        it[0] + opponent.row, it[1] + opponent.column
      )
    }

    val collides =
      futurePlayer.any { cell -> opponentMatrix.any { opponentCell -> opponentCell == cell } }

    return !collides
  }

  fun move(rowDirection: Int, columnDirection: Int) {
    if (!mayMove(rowDirection, columnDirection)) {
      return
    }

    if (rowDirection != 0) {
      player.row += rowDirection
    }

    if (columnDirection != 0) {
      player.column += columnDirection
    }
  }

  fun moveRight() {
    move(0, +1)
  }

  fun moveLeft() {
    move(0, -1)
  }

  fun fallDown() {
    move(+1, 0)
  }

  fun defyGravity() {
    move(-1, 0)
  }
}
