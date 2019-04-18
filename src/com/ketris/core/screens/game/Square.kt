package com.ketris.core.screens.game

import com.ketris.core.screens.game.UIColors.DEFAULT_SQUARE_COLOR
import java.awt.Color

data class Square(var row: Int, var column: Int, var color: Color = DEFAULT_SQUARE_COLOR)
