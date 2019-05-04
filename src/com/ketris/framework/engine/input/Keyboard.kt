package com.ketris.framework.engine.input

import java.awt.event.KeyEvent

interface IKeyboard {
  fun keyPressed(e: KeyEvent) {}
  fun keyReleased(e: KeyEvent) {}
  fun keyTyped(e: KeyEvent) {}
}
