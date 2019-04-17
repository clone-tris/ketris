package com.ketris.core.screens.game

import java.awt.Color

class Shape(var grid: Array<IntArray>, var row: Int, var column: Int, var color: Color) {
  fun move(rowDirection: Int, columnDirection: Int) {
    if (!mayMove(rowDirection, columnDirection)) {
      return
    }

    if (rowDirection != 0) {
      row += rowDirection
    }

    if (columnDirection != 0) {
      column += columnDirection
    }

  }

  private fun mayMove(rowDirection: Int, columnDirection: Int): Boolean {
    return true
  }

  fun moveRight() {
    move(0, +1)
  }

  fun moveLeft() {
    move(0, -1)
  }

  fun fallDown() {
    move(+1, 0)
  }

  fun defyGravity() {
    move(-1, 0)
  }
}
