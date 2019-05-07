package com.ketris.framework.engine

import com.ketris.framework.io.KeyManager
import java.awt.event.KeyEvent

typealias InstantiateScreen = (game: Game, width: Int, height: Int) -> GameScreen

class Game(
  val screen: InstantiateScreen, val width: Int, val height: Int
) {
  val canvas: GamePanel = GamePanel(
    width = width, height = height, screen = screen(this, width, height), game = this
  )
  private var keyManager = KeyManager(this)

  init {
    canvas.addKeyListener(keyManager)
  }

  fun useScreen(newScreen: InstantiateScreen) {
    canvas.screen.unload()
    canvas.screen = newScreen(this, width, height)
  }

  fun keyPressed(e: KeyEvent) {
    canvas.screen.keyPressed(e)
  }

  fun keyReleased(e: KeyEvent) {
    canvas.screen.keyReleased(e)
  }
}
