package com.ketris.screens.game.sidebar

import com.ketris.screens.game.Config.SQUARE_WIDTH
import com.ketris.screens.game.Score
import com.ketris.screens.game.playfield.Painter
import java.awt.Rectangle

open class Painter(width: Int, height: Int) : Painter(width, height) {
  fun background() {
    clear()
    drawSmallGuide()
  }

  fun drawSmallGuide() {
    drawGuide(Rectangle(SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH * 4, SQUARE_WIDTH * 2))
  }

  fun drawLevel() {
    drawText("Level : ${Score.level}", SQUARE_WIDTH, SQUARE_WIDTH * 4)
  }

  fun drawLinesCleared() {
    drawText("Cleared : ${Score.linesCleared}", SQUARE_WIDTH, SQUARE_WIDTH * 5)
  }

  fun drawScore() {
    drawText("Score : ${Score.total}", SQUARE_WIDTH, SQUARE_WIDTH * 6)
  }
}
