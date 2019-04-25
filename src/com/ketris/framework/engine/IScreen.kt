package com.ketris.framework.engine

import kotlin.reflect.KFunction

interface IScreen {
  val painter: KFunction<GraphicsPainter>

  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int) {}

  /**
   * @param p GraphicsPainter instance to paint with and info about the GamePanel
   */
  fun paint(p: GraphicsPainter)
}
