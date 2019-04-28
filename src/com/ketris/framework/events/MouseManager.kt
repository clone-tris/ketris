package com.ketris.framework.events

import com.ketris.framework.engine.GameScreen
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class MouseManager(var screen: GameScreen) : MouseAdapter() {
  override fun mousePressed(e: MouseEvent) {
    screen.mousePressed(e)
  }
}
