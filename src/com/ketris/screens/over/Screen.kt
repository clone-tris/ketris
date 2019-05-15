package com.ketris.screens.over

import com.ketris.framework.components.Button
import com.ketris.framework.engine.Game
import com.ketris.framework.engine.GameScreen
import com.ketris.framework.io.IListenToKeyboard
import com.ketris.framework.io.IListenToMouse
import com.ketris.framework.io.KeyManager
import com.ketris.framework.io.MouseManager
import com.ketris.screens.game.Config
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import com.ketris.screens.game.Screen as MainGameScreen

class Screen : GameScreen, IListenToMouse, IListenToKeyboard {
  override val painter = Painter(Game.width, Game.height)
  private val restartButton = Button(
    text = "Restart (R)", x = 6 * Config.SQUARE_WIDTH, y = 17 * Config.SQUARE_WIDTH
  )

  init {
    MouseManager.addListener(this)
    KeyManager.addListener(this)
  }

  override fun keyPressed(e: KeyEvent) {
    when (e.keyCode) {
      KeyEvent.VK_R -> Game.useScreen(::MainGameScreen)
    }
  }

  override fun mousePressed(e: MouseEvent) {
    if (restartButton.bounds.contains(e.point)) {
      Game.useScreen(::MainGameScreen)
    }
  }

  override fun paint() {
    painter.drawPopup()
    painter.fillButton(restartButton)
  }

  override fun unload() {
    MouseManager.removeListener(this)
    KeyManager.removeListener(this)
  }
}
