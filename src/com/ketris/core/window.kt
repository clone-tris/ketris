package com.ketris.core

import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

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
    }
}
