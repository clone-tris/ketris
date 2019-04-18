package com.ketris.core.screens.game

import com.ketris.core.Config.PUZZLE_HEIGHT
import com.ketris.core.Config.PUZZLE_WIDTH
import java.awt.Color

class Commander {
  var player = spawnPlayer()
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
    player = spawnPlayer()
  }

  fun spawnPlayer(): Shape {
    return Shape(grid = randomShapeGrid(), row = 0, column = 0, color = randomShapeColor())
  }

  fun randomShapeGrid(): List<Square> {
    Math.random()
    return Shapes.values().toList().shuffled().first().grid
  }

  fun randomShapeColor(): Color {
    return ShapeColors.values().toList().shuffled().first().color
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

    return !shapesCollide(futurePlayer, opponentMatrix)
  }

  fun shapesCollide(a: List<Square>, b: List<Square>): Boolean {
    return a.any { cellA ->
      b.any { cellB ->
        listOf(cellB.row, cellB.column) == listOf(cellA.row, cellA.column)
      }
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
