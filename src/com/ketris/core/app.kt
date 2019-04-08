package com.ketris.core

import java.awt.Point
import javax.swing.WindowConstants

fun main(args: Array<String>) {
    val frame = Window("Ketris")
    frame.isVisible = true
    frame.location = Point(800, 410)
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}
