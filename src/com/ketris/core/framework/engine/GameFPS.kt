package com.ketris.core.framework.engine

import java.util.Timer
import java.util.TimerTask


/**
 * todo : investigate how to make it show 60, cuz this is only TETRIS
 */
class GameFPS {
  private var accumulator = 0
  private var fps = 0

  init {
    Timer().scheduleAtFixedRate(object : TimerTask() {
      override fun run() {
        updateFPS()
      }
    }, 0, 1000)
  }

  fun increment() {
    accumulator++
  }

  fun updateFPS() {
    fps = accumulator
    accumulator = 0
  }

  fun value(): Int {
    return fps
  }

}
