package com.ketris.framework.io

import com.ketris.framework.engine.Game
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyManager(var game: Game) : KeyAdapter() {
  var wIsDown: Boolean = false
  var sIsDown: Boolean = false
  var aIsDown: Boolean = false
  var dIsDown: Boolean = false

  override fun keyPressed(e: KeyEvent) {
    game.keyPressed(e)
    when (e.keyCode) {
      KeyEvent.VK_W -> wIsDown = true
      KeyEvent.VK_S -> sIsDown = true
      KeyEvent.VK_A -> aIsDown = true
      KeyEvent.VK_D -> dIsDown = true
    }
  }

  override fun keyReleased(e: KeyEvent) {
    game.keyReleased(e)
    when (e.keyCode) {
      KeyEvent.VK_W -> wIsDown = false
      KeyEvent.VK_S -> sIsDown = false
      KeyEvent.VK_A -> aIsDown = false
      KeyEvent.VK_D -> dIsDown = false
    }
  }
}
