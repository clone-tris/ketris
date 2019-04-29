package com.ketris.framework.events

import com.ketris.framework.engine.Game
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class MouseManager(var game: Game) : MouseAdapter() {
  override fun mousePressed(e: MouseEvent) {
    game.mousePressed(e)
  }
}