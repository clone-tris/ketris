package com.ketris.screens.loading

import com.ketris.framework.engine.GraphicsPainter
import com.ketris.framework.engine.IScreen

class Screen : IScreen {
  override val painter = ::Painter

  override fun paint(p: GraphicsPainter) {
  }
}

