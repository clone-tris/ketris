package com.ketris.screens.game.warzone

import com.ketris.screens.game.Config.PUZZLE_HEIGHT
import com.ketris.screens.game.Config.PUZZLE_WIDTH
import com.ketris.screens.game.Shape
import com.ketris.screens.game.randomTetromino
import log

class Commander {
  var player = createPlayer()
  var nextPlayer = createPlayer()
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
  var inspect = false

  private fun gameOver() {
    log("Game Over !")
    gameEnded = true
  }

  private fun eatPlayer() {
    opponent.grid = opponent.grid.union(player.absoluteGrid()).toList()
    removeFullLines()
  }

  fun restart() {
    opponent.grid = emptyList()
    player = createPlayer()
  }

  private fun removeFullLines() {
//    log("Testing for full lines, after dropping ${player.grid}")

    val squaresInRows = opponent.grid.fold(mutableMapOf<Int, Int>()) { acc, square ->
      val row = square.row
      val rowCount = acc.getOrPut(row) { 0 }
      acc[row] = rowCount + 1
      acc
    }

    if (squaresInRows.any { it.value > PUZZLE_WIDTH }) {
      println(
        "something fishy here : rows are full like so $squaresInRows," + " grid is full like so ${opponent.grid}"
      )
    }

    val fullRows = squaresInRows.filter { it.value == PUZZLE_WIDTH }.keys.sorted()

    if (fullRows.isEmpty()) {
      return
    }

//    log("removing full rows : (Size : ${fullRows.size}) $fullRows")
//    log("Original grid : ${opponent.grid}")

    val filteredGrid = opponent.grid
      // remove full lines from grid
      .filter { (row) -> row !in fullRows }

//    log("remaining rows : $filteredGrid")

    opponent.grid = filteredGrid.map { square ->
      val copy = square.copy()
      fullRows.forEach { row ->
        if (row > copy.row) {
          copy.row++
        }
      }
      copy
    }

//    log("result ${opponent.grid}")
  }

  private fun createPlayer(): Shape {
    val tetromino = randomTetromino()
    return Shape(grid = tetromino.grid, row = 0, column = 0, color = tetromino.color)
  }

  private fun spawnPlayer() {
    nextPlayer.row -= nextPlayer.height
    nextPlayer.column = (PUZZLE_WIDTH - nextPlayer.width) / 2
    player = nextPlayer
    nextPlayer = createPlayer()
  }

  fun rotatePlayer() {
    val futurePlayer = player.copy()
    futurePlayer.rotate()
    if (futurePlayer.collidesWith(opponent) || !futurePlayer.withinBounds()) {
      return
    }
    player = futurePlayer
  }

  private fun movePlayer(rowDirection: Int, columnDirection: Int): Boolean {
    val futurePlayer = player.copy()
    futurePlayer.move(rowDirection, columnDirection)
    val movedSuccessfuly = !futurePlayer.collidesWith(opponent) && futurePlayer.withinBounds()
    if (movedSuccessfuly) {
      player = futurePlayer
    } else {
      if (rowDirection == +1) {
        eatPlayer()
        spawnPlayer()
        if (player.collidesWith(opponent)) {
          gameOver()
          return false
        }
      }
    }
    return movedSuccessfuly
  }

  fun moveRight() {
    movePlayer(0, +1)
  }

  fun moveLeft() {
    movePlayer(0, -1)
  }

  fun fallDown(): Boolean {
    val diIMoveThough = movePlayer(+1, 0)
    if (inspect) {
      inspect = false
    }
    return diIMoveThough
  }
}
