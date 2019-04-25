package com.ketris.framework.engine

import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import kotlin.reflect.KFunction

interface IScreen {
  val painterClass: KFunction<GraphicsPainter>

  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int) {}

  /**
   * @param p GraphicsPainter instance to paint with and info about the GamePanel
   */
  fun paint(p: GraphicsPainter)

  fun keyPressed(e: KeyEvent) {}

  fun mousePressed(e: MouseEvent) {}
}
