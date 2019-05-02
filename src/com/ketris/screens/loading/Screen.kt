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
    grid = listOf(
      Square(1, 1),
      Square(2, 1),
      Square(3, 1),
      Square(3, 2),
      Square(1, 4),
      Square(2, 4),
      Square(3, 4),
      Square(3, 5),
      Square(3, 6),
      Square(2, 6),
      Square(1, 6),
      Square(1, 5),
      Square(2, 8),
      Square(1, 9),
      Square(2, 10),
      Square(3, 10),
      Square(3, 9),
      Square(7, 1),
      Square(8, 2),
      Square(7, 3),
      Square(6, 2),
      Square(5, 3),
      Square(6, 3),
      Square(8, 5),
      Square(7, 5),
      Square(5, 5),
      Square(8, 7),
      Square(7, 7),
      Square(6, 7),
      Square(6, 8),
      Square(6, 9),
      Square(7, 9),
      Square(8, 9),
      Square(11, 1),
      Square(10, 2),
      Square(11, 3),
      Square(12, 2),
      Square(12, 3),
      Square(13, 3),
      Square(14, 2),
      Square(14, 5),
      Square(14, 6),
      Square(14, 7)
    ), row = 0, column = 0, color = randomShapeColor()
  )

  override fun keyPressed(e: KeyEvent) {
    when (e.keyCode) {
      KeyEvent.VK_ENTER -> game.useScreen(::MainGameScreen)
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
