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

  private fun eatPlayer() {
    opponent.grid = opponent.grid.union(player.absoluteGrid()).toList()
    player = spawnPlayer()
  }

  private fun spawnPlayer(): Shape {
    val newPlayer = Shape(grid = randomShapeGrid(), row = 0, column = 0, color = randomShapeColor())
    newPlayer.column = (PUZZLE_WIDTH - newPlayer.width) / 2
    return newPlayer
  }

  private fun randomShapeGrid(): List<Square> {
    return Shapes.values().toList().shuffled().first().grid
  }

  private fun randomShapeColor(): Color {
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

    val futurePlayerGrid = player.absoluteGrid(rowDirection, columnDirection)
    val opponentGrid = opponent.absoluteGrid()

    return !shapesCollide(futurePlayerGrid, opponentGrid)
  }


  private fun shapesCollide(a: List<Square>, b: List<Square>): Boolean {
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

  private fun validRotationGrid(playerGrid: List<Square>): Boolean {
    if (shapesCollide(playerGrid, opponent.grid)) {
      return false
    }

    val afterRight = playerGrid.any { square -> square.column >= PUZZLE_WIDTH }
    if (afterRight) {
      return false
    }

    val bellowBottom = playerGrid.any { square -> square.row >= PUZZLE_HEIGHT }
    if (bellowBottom) {
      return false
    }

    val beforeLeft = playerGrid.any { square -> square.column < 0 }
    if (beforeLeft) {
      return false
    }

    return true
  }

  fun rotate() {
    val futurePlayer = player.copy()
    futurePlayer.grid =
      futurePlayer.grid.map { it.copy(row = it.column, column = player.height - it.row - 1) }

    if (!validRotationGrid(futurePlayer.absoluteGrid())) {
      return
    }

    player.grid = futurePlayer.grid
    player.computeSize()
  }

  private fun move(rowDirection: Int, columnDirection: Int) {
    if (!mayMove(rowDirection, columnDirection)) {
      if (rowDirection == +1) {
        eatPlayer()
      }
      return
    }

    player.row += rowDirection
    player.column += columnDirection
  }
}
