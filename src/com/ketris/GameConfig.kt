package com.ketris

import com.ketris.screens.game.Config.PUZZLE_HEIGHT
import com.ketris.screens.game.Config.SIDEBAR_WIDTH
import com.ketris.screens.game.Config.SQUARE_WIDTH
import com.ketris.screens.game.Config.WAR_ZONE_WIDTH

object GameConfig {
  const val CANVAS_WIDTH: Int = SIDEBAR_WIDTH + WAR_ZONE_WIDTH
  const val   CANVAS_HEIGHT: Int = PUZZLE_HEIGHT * SQUARE_WIDTH
  const val DEBUG_GRAPHICS = false
  const val ENABLE_LOG = true
}
