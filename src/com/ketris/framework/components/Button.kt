package com.ketris.framework.components

import java.awt.Rectangle

class Button(val text: String, var x: Int, var y: Int) {
  val width: Int
  val height: Int
  val tx: Int
  val ty: Int
  val bounds: Rectangle

  init {
    val paddingTop = 8
    val paddingLeft = 10
    val letterWidth = 6.7
    val wordWidth = (letterWidth * text.length).toInt()
    width = 2 * paddingLeft + wordWidth
    height = 2 * paddingTop + 12

    tx = x + (width - wordWidth) / 2
    ty = y + (height - 14) / 2

    bounds = Rectangle(x, y, width, height)
  }
}
