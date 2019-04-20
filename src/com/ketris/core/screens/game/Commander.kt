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

  var ended = false

  private fun gameOver() {
    println("Game Over !")
    ended = true
  }

  private fun eatPlayer() {
    opponent.grid = opponent.grid.union(player.absoluteGrid()).toList()
    val newPlayer = spawnPlayer()
    if (newPlayer.collidesWith(opponent)) {
      gameOver()
      return
    }
    player = newPlayer
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

  fun rotate() {
    val futurePlayer = player.copy()
    futurePlayer.rotate()
    if (futurePlayer.collidesWith(opponent) || !futurePlayer.withinBounds()) {
      return
    }
    player = futurePlayer
  }

  private fun move(rowDirection: Int, columnDirection: Int) {
    val futurePlayer = player.copy()
    futurePlayer.move(rowDirection, columnDirection)

    val cantMove = futurePlayer.collidesWith(opponent) || !futurePlayer.withinBounds()

    if (cantMove) {
      if (rowDirection == +1) {
        eatPlayer()
      }
      return
    }

    player = futurePlayer
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

}
