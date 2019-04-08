package com.ketris.core

import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

class Canvas(width: Int, height: Int) : JPanel() {
    init {
        preferredSize = Dimension(width, height)
        isFocusable = true
        background = Color.decode("#333333")
    }

}
