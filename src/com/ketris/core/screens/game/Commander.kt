package com.ketris.core.screens.game

import com.ketris.core.Config.PUZZLE_HEIGHT
import com.ketris.core.Config.PUZZLE_WIDTH
import com.ketris.core.screens.game.Shapes.Square as SquareShape

class Commander {
  val player = Shape(randomShapeGrid(), 0, 0)
  val opponent = Shape(
    grid = emptyList(),
    row = 0,
    column = 0,
    height = PUZZLE_HEIGHT,
    width = PUZZLE_WIDTH,
    computeHeight = false
  )

  fun eatPlayer() {
    opponent.grid = opponent.grid.union(player.absoluteGrid()).toList()
    player.column = 0
    player.row = 0
    player.grid = randomShapeGrid()
    player.computeSize()
  }

  fun randomShapeGrid(): List<Square> {
    Math.random()
    return Shapes.values().toList().shuffled().take(1)[0].grid
  }

  private fun mayMove(rowDirection: Int, columnDirection: Int): Boolean {
    // left bound
    if (columnDirection == -1 && player.column == 0) {
      return false
    }

    // right bound
    if (columnDirection == 1 && PUZZLE_WIDTH - player.width == player.column) {
      return false
    }

    // bottom bound
    if (columnDirection == 0 && PUZZLE_HEIGHT - player.height == player.row) {
      return false
    }

    val futurePlayer = player.absoluteGrid(rowDirection, columnDirection)
    val opponentMatrix = opponent.absoluteGrid()

    val collides = futurePlayer.any { cell ->
      opponentMatrix.any { opponentCell ->
        opponentCell.coordinates() == cell.coordinates()
      }
    }

    return !collides
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

  fun move(rowDirection: Int, columnDirection: Int) {
    if (!mayMove(rowDirection, columnDirection)) {
      if (rowDirection == +1) {
        eatPlayer()
      }
      return
    }

    if (rowDirection != 0) {
      player.row += rowDirection
    }

    if (columnDirection != 0) {
      player.column += columnDirection
    }
  }
}
