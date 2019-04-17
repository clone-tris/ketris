package com.ketris.core.screens.game

import java.awt.Color

class Commander {
  val player = Shape(
    listOf(
      Square(0, 0), Square(0, 1), Square(0, 2), Square(1, 1)
    ), 0, 0, Color.RED
  )

  val opponent = Shape(emptyList(), 0, 0)

  private fun mayMove(rowDirection: Int, columnDirection: Int): Boolean {
    val futurePlayer = player.grid.map {
      Square(
        it.row + player.row + rowDirection, it.column + player.column + columnDirection
      )
    }

    val opponentMatrix = opponent.grid.map {
      Square(
        it.row + opponent.row, it.column + opponent.column
      )
    }

    val collides =
      futurePlayer.any { cell -> opponentMatrix.any { opponentCell -> opponentCell.coordinates() == cell.coordinates() } }

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

  fun rotate() {

  }
}
