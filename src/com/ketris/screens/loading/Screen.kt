package com.ketris.screens.loading

import com.ketris.Config
import com.ketris.framework.components.Button
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.screens.game.warzone.Screen as MainGameScreen
import com.ketris.screens.game.Shape
import com.ketris.screens.game.Square
import com.ketris.screens.game.randomShapeColor
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent

class Screen(game: Game, width: Int, height: Int) : GameScreen(game, width, height) {
  override val painter = Painter(width, height)
  val startButton = Button(
    text = "Start (S)", x = 6 * Config.SQUARE_WIDTH, y = 17 * Config.SQUARE_WIDTH
  )
  private val colorChangeRate = 500L
  private var nextColorChange = 0L
  private val loadingShape = Shape(
    grid = loadingGrid, row = 0, column = 0, color = randomShapeColor()
  )

  override fun keyPressed(e: KeyEvent) {
    when (e.keyCode) {
      KeyEvent.VK_S -> game.useScreen(::MainGameScreen)
    }
  }

  override fun mousePressed(e: MouseEvent) {
    if (startButton.bounds.contains(e.point)) {
      game.useScreen(::MainGameScreen)
    }
  }

  override fun update(dt: Int) {
    val time = System.currentTimeMillis()
    if (time >= nextColorChange) {
      nextColorChange = time + colorChangeRate
      loadingShape.color = randomShapeColor()
    }
  }

  override fun paint() {
    painter.clear()
    painter.drawShape(loadingShape)
    painter.drawButton(startButton)
  }
}
