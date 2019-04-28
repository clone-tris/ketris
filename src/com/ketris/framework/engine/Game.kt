package com.ketris.framework.engine

typealias InstantiateScreen = (game: Game, width: Int, height: Int) -> GameScreen

class Game(
  val screen: InstantiateScreen, val width: Int, val height: Int
) {
  val canvas: GamePanel = GamePanel(
    width = width, height = height, screen = screen(this, width, height), game = this
  )

  fun useScreen(newScreen: InstantiateScreen) {
    canvas.screen = newScreen(this, width, height)
  }
}
