package com.ketris

object Config {
  const val SQUARE_WIDTH = 20
  const val SQUARE_BORDER_WIDTH = 2
  const val PUZZLE_WIDTH = 10
  const val PUZZLE_HEIGHT = 20
  const val CANVAS_WIDTH: Int = PUZZLE_WIDTH * SQUARE_WIDTH
  const val CANVAS_HEIGHT: Int = PUZZLE_HEIGHT * SQUARE_WIDTH

  // META
  const val DEBUG_GRAPHICS = false
  const val ENABLE_LOG = true
}
