package com.ketris.framework.engine

import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import kotlin.reflect.KFunction

interface IScreen<P> {
  val painterClass: KFunction<P>

  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int) {}

  /**
   * @param p GraphicsPainter instance to paint with and info about the GamePanel
   */
  fun paint(painter: P)

  fun keyPressed(e: KeyEvent) {}

  fun mousePressed(e: MouseEvent) {}
}
