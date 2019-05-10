package com.ketris.framework.io

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

object MouseManager : MouseAdapter(), IListenToThings<IListenToMouse> {
  override val listeners = mutableListOf<IListenToMouse>()

  override fun mousePressed(e: MouseEvent) {
    // thou shalt not delete from a list while iterating it. so iterate over a copy instead
    listeners.toList().forEach { listener ->
      listener.mousePressed(e)
    }
  }
}
