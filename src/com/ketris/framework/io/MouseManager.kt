package com.ketris.framework.io

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

object MouseManager : MouseAdapter() {
  private val listeners = mutableListOf<IListensToMouse>()

  override fun mousePressed(e: MouseEvent) {
    // thou shalt not delete from a list while iterating it. so iterate over a copy instead
    listeners.toList().forEach { listener ->
      listener.mousePressed(e)
    }
  }

  fun addListener(listener: IListensToMouse) {
    listeners.add(listener)
  }

  fun removeListener(listener: IListensToMouse) {
    listeners.remove(listener)
  }
}
