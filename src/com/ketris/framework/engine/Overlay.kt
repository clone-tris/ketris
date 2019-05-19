package com.ketris.framework.engine

class Overlay : GameScreen {
  override val painter = OverlayPainter(Game.width, Game.height)
  var fps = GameFPS()
  override fun paint() {
    painter.drawFPS(fps.value())
  }
}
