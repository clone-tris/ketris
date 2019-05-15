package com.ketris

import com.ketris.framework.engine.GamePanel
import java.awt.Point
import javax.swing.JFrame
import javax.swing.WindowConstants

class Window(title: String, canvas: GamePanel) : JFrame(title) {
  init {
    contentPane = canvas
    pack()

    isVisible = true
    // at home
//    location = Point(1400, 110)

    // at work
    location = Point(1500, 410)
    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
  }
}
