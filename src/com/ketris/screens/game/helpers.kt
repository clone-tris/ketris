package com.ketris.screens.game

import java.awt.Color

fun randomShapeColor(): Color {
  return ShapeColors.values().toList().shuffled().first().color
}

fun randomShapeGrid(): List<Square> {
  return Tetromino.values().toList().shuffled().first().grid
}
