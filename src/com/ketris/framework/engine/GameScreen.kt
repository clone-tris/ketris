package com.ketris.framework.engine

import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage

abstract class GameScreen {
  protected abstract val painter: GraphicsPainter
  /**
   * @param dt Delta time since last render in Miliseconds
   */

  open fun update(dt: Int) {}

  abstract fun paint()

  fun paintCanvas(): BufferedImage {
    paint()
    return painter.canvas()
  }

  open fun keyPressed(e: KeyEvent) {}

  fun mousePressed(e: MouseEvent) {}
}
