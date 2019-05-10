package com.ketris.framework.io

import java.awt.event.KeyEvent

interface IListenToKeyboard {
  fun keyPressed(e: KeyEvent) {}
  fun keyReleased(e: KeyEvent) {}
  fun keyTyped(e: KeyEvent) {}
}
