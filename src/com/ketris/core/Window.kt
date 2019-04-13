package com.ketris.core

import java.awt.Point
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class Window(title: String) : JFrame(title) {
  init {
    val windowPane = JPanel()
    val canvas = GamePanel(400, 600)
    windowPane.add(canvas)

    contentPane = windowPane
    pack()

    isVisible = true
    // at home
//        location = Point(760, 410)

    // at work
    location = Point(1200, 410)
    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

    canvas.start()
  }
}
