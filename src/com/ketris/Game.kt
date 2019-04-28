package com.ketris

import com.ketris.framework.engine.GamePanel
import com.ketris.framework.engine.GameScreen
import kotlin.reflect.KFunction

class Game(
  val screens: List<KFunction<GameScreen>>, val width: Int, val height: Int
) {
  val canvas = GamePanel(width, height)
}
