package com.ketris.framework.engine

import java.awt.image.BufferedImage

interface GameScreen {
  val painter: GraphicsPainter
  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int) {}

  fun paint()
  fun canvas(): BufferedImage {
    return painter.buffer
  }

  fun unload() {}
}
