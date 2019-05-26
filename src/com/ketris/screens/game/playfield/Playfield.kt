package com.ketris.screens.game.playfield

import com.ketris.GameConfig.DEBUG_GRAPHICS
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.screens.game.Config
import com.ketris.screens.game.Score
import com.ketris.screens.game.Shape
import com.ketris.screens.game.randomTetromino
import com.ketris.screens.over.Screen as GameOverScreen

class Playfield(width: Int, height: Int) : GameScreen {
  override val painter = Painter(width, height)
  var fallRate = 1000L
  val floorRate = 500L
  var onFloor = false
  var endOfLock = 0L
  var player = createPlayer()
  var nextPlayer = createPlayer()
  private val opponent = Shape(
    grid = emptyList(),
    row = 0,
    column = 0,
    height = Config.PUZZLE_HEIGHT,
    width = Config.PUZZLE_WIDTH,
    computeHeight = false
  )

  var animating = false
  var inspect = false

  init {
    Score.reset()
    spawnPlayer()
  }

  override fun paint() {
    painter.drawBackground()
    painter.drawShape(player)
    painter.drawShape(opponent)

    // keep the following last as it need to be on top of everything
    if (DEBUG_GRAPHICS) {
      painter.drawPlayerInfo(player)
    }
  }

  private fun gameOver() {
    Game.useScreen(::GameOverScreen)
  }

  private fun eatPlayer() {
    opponent.grid = opponent.grid.union(player.absoluteGrid()).toList()
    val linesRemoved = removeFullLines()
    if (linesRemoved == 0) {
      return
    }
    val currentLevel = Score.level
    applyScore(linesRemoved)
    if (currentLevel != Score.level) {
      fallRate -= fallRate / 3
    }
  }

  private fun applyScore(linesRemoved: Int) {
    val points = when (linesRemoved) {
      1 -> 40
      2 -> 100
      3 -> 300
      4 -> 1200
      else -> 0
    } * (Score.level + 1)

    Score.total += points
    Score.linesCleared += linesRemoved
    Score.level = Score.linesCleared / 10
  }

  fun restart() {
    opponent.grid = emptyList()
    player = createPlayer()
  }

  private fun removeFullLines(): Int {
    val squaresInRows = opponent.grid.fold(mutableMapOf<Int, Int>()) { acc, square ->
      val row = square.row
      val rowCount = acc.getOrPut(row) { 0 }
      acc[row] = rowCount + 1
      acc
    }

    if (squaresInRows.any { it.value > Config.PUZZLE_WIDTH }) {
      println(
        "something fishy here : rows are full like so $squaresInRows," + " grid is full like so ${opponent.grid}"
      )
    }

    val fullRows = squaresInRows.filter { it.value == Config.PUZZLE_WIDTH }.keys.sorted()

    if (fullRows.isEmpty()) {
      return 0
    }

    val filteredGrid = opponent.grid.filter { (row) -> row !in fullRows }

    opponent.grid = filteredGrid.map { square ->
      val copy = square.copy()
      fullRows.forEach { row ->
        if (row > copy.row) {
          copy.row++
        }
      }
      copy
    }

    return fullRows.size
  }

  private fun createPlayer(): Shape {
    val tetromino = randomTetromino()
    return Shape(grid = tetromino.grid, row = 0, column = 0, color = tetromino.color)
  }

  private fun spawnPlayer() {
    nextPlayer.row -= nextPlayer.height
    nextPlayer.column = (Config.PUZZLE_WIDTH - nextPlayer.width) / 2
    player = nextPlayer
    nextPlayer = createPlayer()
  }

  fun rotatePlayer() {
    val foreshadow = player.copy()
    foreshadow.rotate()
    if (foreshadow.collidesWith(opponent) || !foreshadow.withinBounds()) {
      return
    }
    player = foreshadow
  }

  private fun movePlayer(rowDirection: Int, columnDirection: Int): Boolean {
    val foreshadow = player.copy()
    val movingDown = rowDirection == +1
    foreshadow.move(rowDirection, columnDirection)
    val ableToMove = !foreshadow.collidesWith(opponent) && foreshadow.withinBounds()
    if (ableToMove) {
      player = foreshadow
      if (movingDown) {
        onFloor = false
      }
    } else if (movingDown) {
      handleFallingDown()
    }
    return ableToMove
  }

  fun handleFallingDown() {
    val time = System.currentTimeMillis()
    if (!onFloor) {
      onFloor = true
      endOfLock = time + floorRate
      return
    } else if (time < endOfLock) {
      return
    }

    eatPlayer()
    spawnPlayer()
    if (player.collidesWith(opponent)) {
      gameOver()
    }
  }

  fun moveRight() {
    movePlayer(0, +1)
  }

  fun moveLeft() {
    movePlayer(0, -1)
  }

  fun fallDown(): Boolean {
    val ableToMove = movePlayer(+1, 0)
    if (inspect) {
      inspect = false
    }
    return ableToMove
  }
}
