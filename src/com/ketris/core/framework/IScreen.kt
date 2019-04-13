package com.ketris.core.framework

import java.awt.Graphics2D

interface IScreen {
  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int)

  /**
   * @param g Graphics2D instance to paint with
   */
  fun paint(g: Graphics2D)
}
