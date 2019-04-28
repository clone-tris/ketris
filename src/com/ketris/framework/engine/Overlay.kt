package com.ketris.framework.engine

import com.ketris.Config
import com.ketris.screens.game.Painter
import java.awt.image.BufferedImage

class Overlay(val width: Int, val height: Int) : IScreen {
  private val painter = Painter(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT)

  override fun paint(): BufferedImage {
    // Smoothing up things so that we get good graphics instead of pixely things

    return painter.canvas()
  }
}
