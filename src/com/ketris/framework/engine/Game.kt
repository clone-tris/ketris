package com.ketris.framework.engine

import com.ketris.framework.events.KeyManager
import com.ketris.framework.events.MouseManager
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent

typealias InstantiateScreen = (game: Game, width: Int, height: Int) -> GameScreen

class Game(
  val screen: InstantiateScreen, val width: Int, val height: Int
) {
  val canvas: GamePanel = GamePanel(
    width = width, height = height, screen = screen(this, width, height), game = this
  )
  private var keyManager = KeyManager(this)
  private var mouseManager = MouseManager(this)

  init {
    canvas.addKeyListener(keyManager)
    canvas.addMouseListener(mouseManager)
  }

  fun useScreen(newScreen: InstantiateScreen) {
    canvas.screen = newScreen(this, width, height)
  }

  fun keyPressed(e: KeyEvent) {
    canvas.screen.keyPressed(e)
  }
  fun keyReleased(e: KeyEvent) {
    canvas.screen.keyReleased(e)
  }
  fun mousePressed(e: MouseEvent) {
    canvas.screen.mousePressed(e)
  }
}
