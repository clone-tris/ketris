package com.ketris.framework.engine

import kotlin.reflect.KFunction

class Game(
  val screens: List<KFunction<GameScreen>>, val width: Int, val height: Int
) {
  val canvas = GamePanel(width, height)
}
