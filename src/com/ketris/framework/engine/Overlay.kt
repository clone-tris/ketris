package com.ketris.framework.engine

import com.ketris.Config
import com.ketris.screens.game.Painter

class Overlay(val width: Int, val height: Int) : GameScreen() {
  override val painter = Painter(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT)

  override fun paint() {
    // Smoothing up things so that we get good graphics instead of pixely things
  }
}
