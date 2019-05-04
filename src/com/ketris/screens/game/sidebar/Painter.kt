package com.ketris.screens.game.sidebar

import com.ketris.screens.game.Config.SQUARE_WIDTH
import com.ketris.screens.game.warzone.Painter
import java.awt.Rectangle

open class Painter(width: Int, height: Int) : Painter(width, height) {
  fun background() {
    clear()
    drawSmallGuide()
  }

  fun drawSmallGuide() {
    drawGuide(Rectangle(SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH * 4, SQUARE_WIDTH * 2))
  }
}
