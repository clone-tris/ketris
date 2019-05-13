package com.ketris.screens.game

object Score {
  var linesCleared: Int = 0
  var total: Int = 0
  var level: Int = 0
  fun reset() {
    linesCleared = 0
    total = 0
    level = 0
  }
}
