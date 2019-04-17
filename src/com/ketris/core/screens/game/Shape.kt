package com.ketris.core.screens.game

import java.awt.Color

class Shape(var grid: Array<IntArray>, var row: Int, var column: Int, var color: Color) {
  fun moveRight() {
    column++
  }

  fun moveLeft() {
    column--
  }

  fun fallDown() {
    row++
  }

  fun rotate() {

  }
}
