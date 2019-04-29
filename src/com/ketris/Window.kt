package com.ketris

import com.ketris.framework.engine.GamePanel
import java.awt.Point
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class Window(title: String, canvas: GamePanel) : JFrame(title) {
  init {
    val windowPane = JPanel()
    windowPane.add(canvas)

    contentPane = windowPane
    pack()

    isVisible = true
    // at home
    location = Point(1000, 110)

    // at work
//    location = Point(1200, 410)
    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

    canvas.startGameLoop()
  }
}
