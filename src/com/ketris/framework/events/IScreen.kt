package com.ketris.framework.events

import com.ketris.screens.game.Painter

interface IScreen {
  /**
   * @param dt Delta time since last render in Miliseconds
   */
  fun update(dt: Int)

  /**
   * @param p Painter instance to paint with and info about the GamePanel
   */
  fun paint(p: Painter)
}
