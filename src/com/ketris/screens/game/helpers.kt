package com.ketris.screens.game

import java.awt.Color

fun randomShapeColor(): Color {
  return ShapeColors.values().toList().shuffled().first().color
}

fun randomTetromino(): Tetromino {
  return Tetromino.values().toList().shuffled().first()
}
