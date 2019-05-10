package com.ketris.framework.engine

import com.ketris.screens.game.playfield.Painter

class Overlay(width: Int, height: Int) : GameScreen {
  override val painter = Painter(width, height)

  override fun paint() {
    // Smoothing up things so that we get good graphics instead of pixely things
  }
}
