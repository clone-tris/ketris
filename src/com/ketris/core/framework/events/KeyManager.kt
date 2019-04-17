package com.ketris.core.framework.events

import com.ketris.core.screens.game.Screen
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

var wIsDown: Boolean = false
var sIsDown: Boolean = false
var aIsDown: Boolean = false
var dIsDown: Boolean = false

class KeyManager(var screen: Screen) : KeyAdapter() {
  override fun keyPressed(e: KeyEvent?) {
    screen.keyPressed(e)
    when (e?.keyCode) {
      KeyEvent.VK_W -> wIsDown = true
      KeyEvent.VK_S -> sIsDown = true
      KeyEvent.VK_A -> aIsDown = true
      KeyEvent.VK_D -> dIsDown = true
    }
  }

  override fun keyReleased(e: KeyEvent?) {
    when (e?.keyCode) {
      KeyEvent.VK_W -> wIsDown = false
      KeyEvent.VK_S -> sIsDown = false
      KeyEvent.VK_A -> aIsDown = false
      KeyEvent.VK_D -> dIsDown = false
    }
  }
}
