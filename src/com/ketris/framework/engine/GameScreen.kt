package com.ketris.framework.engine

import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage

interface GameScreen {
  val painter: GraphicsPainter
  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int) {}

  fun paint()
  fun paintCanvas(): BufferedImage {
    paint()
    return painter.canvas()
  }

  fun keyPressed(e: KeyEvent) {}
  fun keyReleased(e: KeyEvent) {}
  fun mousePressed(e: MouseEvent) {}
}
