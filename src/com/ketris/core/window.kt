package com.ketris.core

import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class Window(title: String) : JFrame(title) {
    init {
        val windowPane = JPanel()
        val canvas = object : JPanel() {
            override fun getPreferredSize(): Dimension {
                return Dimension(400, 600)
            }
        }

        windowPane.add(canvas)
        contentPane = windowPane
        pack()

        isVisible = true
        location = Point(800, 410)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }
}
