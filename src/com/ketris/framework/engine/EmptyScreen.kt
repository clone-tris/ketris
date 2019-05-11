package com.ketris.framework.engine

class EmptyScreen : GameScreen {
  override fun paint() {}
  override val painter: GraphicsPainter = Painter(1, 1)
}