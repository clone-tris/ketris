package com.ketris.framework.events

import com.ketris.framework.engine.IScreen
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

var wIsDown: Boolean = false
var sIsDown: Boolean = false
var aIsDown: Boolean = false
var dIsDown: Boolean = false

class KeyManager(var screen: IScreen<*>) : KeyAdapter() {
  override fun keyPressed(e: KeyEvent) {
    screen.keyPressed(e)
    when (e.keyCode) {
      KeyEvent.VK_W -> wIsDown = true
      KeyEvent.VK_S -> sIsDown = true
      KeyEvent.VK_A -> aIsDown = true
      KeyEvent.VK_D -> dIsDown = true
    }
  }

  override fun keyReleased(e: KeyEvent) {
    when (e.keyCode) {
      KeyEvent.VK_W -> wIsDown = false
      KeyEvent.VK_S -> sIsDown = false
      KeyEvent.VK_A -> aIsDown = false
      KeyEvent.VK_D -> dIsDown = false
    }
  }
}
