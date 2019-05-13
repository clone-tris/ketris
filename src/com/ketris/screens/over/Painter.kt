package com.ketris.screens.over

import java.awt.Color
import java.awt.Font

import com.ketris.GameConfig.CANVAS_HEIGHT
import com.ketris.GameConfig.CANVAS_WIDTH
import com.ketris.UIColors
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.Painter
import com.ketris.screens.game.playfield.Screen as MainScreen

class Painter(width: Int, height: Int) : Painter(width, height) {
  fun drawPopup() {
    if (Game.screens.containsKey(::MainScreen)) {
      val mainScreen = Game.screens[::MainScreen] as MainScreen
      g.drawImage(mainScreen.stitcher.canvas(), 0, 0, width, height, null)
    } else {
      g.color = com.ketris.screens.game.UIColors.BACKGROUND
      g.fillRect(0, 0, width, height)
    }

    val padding = 20
    val message = "Game Over!"
    val font = Font(Font.MONOSPACED, Font.BOLD, 18)
    val fontMetrics = g.getFontMetrics(font)
    val stringWidth = fontMetrics.stringWidth(message)
    val stringHeight = fontMetrics.height
    val boxWidth = padding * 2 + stringWidth
    val boxHeight = padding * 2 + stringHeight
    val boxX = (CANVAS_WIDTH - boxWidth) / 2
    val boxY = (CANVAS_HEIGHT - boxHeight) / 3

    g.color = UIColors.POPUP_BACKGROUND
    g.fillRect(boxX, boxY, boxWidth, boxHeight)

    g.color = Color.decode("#EFEFEF")
    g.font = font
    g.drawString(message, boxX + padding, boxY + padding + font.size)
  }
}
