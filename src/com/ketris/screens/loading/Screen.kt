package com.ketris.screens.loading

import com.ketris.Config
import com.ketris.framework.engine.GameScreen
import com.ketris.screens.game.Shape
import com.ketris.screens.game.Square
import com.ketris.screens.game.randomShapeColor

class Screen : GameScreen() {
  override val painter = Painter(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT)
  private val colorChangeRate = 500L
  private var nextColorChange = 0L
  private val loadingShape = Shape(
    grid = listOf(
      Square(0, 0),
      Square(1, 0),
      Square(2, 0),
      Square(2, 1),
      Square(0, 3),
      Square(1, 3),
      Square(2, 3),
      Square(2, 4),
      Square(2, 5),
      Square(1, 5),
      Square(0, 5),
      Square(0, 4),
      Square(1, 7),
      Square(0, 8),
      Square(1, 9),
      Square(2, 9),
      Square(2, 8),
      Square(6, 0),
      Square(7, 1),
      Square(6, 2),
      Square(5, 1),
      Square(4, 2),
      Square(5, 2),
      Square(7, 4),
      Square(6, 4),
      Square(4, 4),
      Square(7, 6),
      Square(6, 6),
      Square(5, 6),
      Square(5, 7),
      Square(5, 8),
      Square(6, 8),
      Square(7, 8),
      Square(10, 0),
      Square(9, 1),
      Square(10, 2),
      Square(11, 1),
      Square(11, 2),
      Square(12, 2),
      Square(13, 1),
      Square(13, 4),
      Square(13, 5),
      Square(13, 6)
    ), row = 0, column = 0, color = randomShapeColor()
  )

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
  }
}
