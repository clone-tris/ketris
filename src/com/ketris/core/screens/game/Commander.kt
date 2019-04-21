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

  var animating = false
  var gameEnded = false

  private fun gameOver() {
    println("Game Over !")
    gameEnded = true
  }

  private fun eatPlayer() {
    opponent.grid = opponent.grid.union(player.absoluteGrid()).toList()
    removeFullLines()
  }

  fun restart() {
    opponent.grid = emptyList()
    player = spawnPlayer()
  }

  private fun removeFullLines() {
    val fullRows = opponent.grid.fold(mutableMapOf<Int, Int>()) { acc, square ->
      val row = square.row
      val rowCount = acc.getOrPut(row) { 0 }
      acc[row] = rowCount + 1
      acc
    }.filter { it.value == PUZZLE_WIDTH }.keys

    if (fullRows.isEmpty()) {
      return
    }

    val filteredGrid = opponent.grid
      // remove full lines from grid
      .filter { (row) -> row !in fullRows }.toList()

    // translate lines above deletes lines, one row down
    fullRows.sorted().forEach { removedRow ->
      filteredGrid.forEach { square ->
        if (square.row < removedRow) {
          square.row++
        }
      }
    }

    opponent.grid = filteredGrid
  }

  private fun spawnPlayer(): Shape {
    val newPlayer = Shape(grid = randomShapeGrid(), row = 0, column = 0, color = randomShapeColor())
    newPlayer.row -= newPlayer.height
    newPlayer.column = (PUZZLE_WIDTH - newPlayer.width) / 2
    return newPlayer
  }

  private fun randomShapeGrid(): List<Square> {
    return Shapes.values().toList().shuffled().first().grid
  }

  private fun randomShapeColor(): Color {
    return ShapeColors.values().toList().shuffled().first().color
  }

  fun rotatePlayer() {
    val futurePlayer = player.copy()
    futurePlayer.rotate()
    if (futurePlayer.collidesWith(opponent) || !futurePlayer.withinBounds()) {
      return
    }
    player = futurePlayer
  }

  private fun movePlayer(rowDirection: Int, columnDirection: Int) {
    val futurePlayer = player.copy()
    futurePlayer.move(rowDirection, columnDirection)
    val canMove = !futurePlayer.collidesWith(opponent) && futurePlayer.withinBounds()
    if (canMove) {
      player = futurePlayer
    } else {
      if (rowDirection == +1) {
        eatPlayer()

        val newPlayer = spawnPlayer()
        if (newPlayer.collidesWith(opponent)) {
          gameOver()
          return
        }
        player = newPlayer
      }
    }
  }

  fun moveRight() {
    movePlayer(0, +1)
  }

  fun moveLeft() {
    movePlayer(0, -1)
  }

  fun fallDown() {
    movePlayer(+1, 0)
  }

}
