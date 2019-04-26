package com.ketris.framework.events

import com.ketris.framework.engine.IScreen
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class MouseManager(var screen: IScreen<*>) : MouseAdapter() {
  override fun mousePressed(e: MouseEvent) {
    screen.mousePressed(e)
  }
}
