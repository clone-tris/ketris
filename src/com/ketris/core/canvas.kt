package com.ketris.core

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.concurrent.TimeUnit
import javax.swing.JPanel

class Player(var x: Float, var y: Float, var width: Float, var height: Float)

const val FRAMES_PER_SECOND = 60
val REFRESH_INTERVAL_MS: Long = TimeUnit.SECONDS.toMillis(1) / FRAMES_PER_SECOND

class Canvas(width: Int, height: Int) : JPanel() {
  private var g: Graphics? = null
  private var g2D: Graphics2D? = null
  private var player = Player(100f, 100f, 30f, 30f)

  private var timeLastRunMs = System.currentTimeMillis()

  var wIsDown: Boolean = false
  var sIsDown: Boolean = false
  var aIsDown: Boolean = false
  var dIsDown: Boolean = false

  private var isRunning: Boolean = true

  init {
    preferredSize = Dimension(width, height)
    isFocusable = true
    background = Color.decode("#333333")

    println(REFRESH_INTERVAL_MS)
    println(TimeUnit.SECONDS.toMillis(1))

    addKeyListener(object : KeyListener {
      override fun keyTyped(e: KeyEvent?) {

      }

      override fun keyPressed(e: KeyEvent?) {


        when (e?.keyCode) {
          KeyEvent.VK_W -> wIsDown = true
          KeyEvent.VK_S -> sIsDown = true
          KeyEvent.VK_A -> aIsDown = true
          KeyEvent.VK_D -> dIsDown = true
        }
      }

      override fun keyReleased(e: KeyEvent?) {
        when (e?.keyCode) {
          KeyEvent.VK_W -> wIsDown = false
          KeyEvent.VK_S -> sIsDown = false
          KeyEvent.VK_A -> aIsDown = false
          KeyEvent.VK_D -> dIsDown = false
        }
      }
    })
  }

  fun start() {
    val thread = Thread(Runnable { loop() })
    thread.start()
  }

  private fun loop() {
    println("starting loop")

    // update the game repeatedly
    while (isRunning) {
      val durationMs = redraw()
      try {
        Thread.sleep(Math.max(0, REFRESH_INTERVAL_MS - durationMs))
      } catch (e: InterruptedException) {
        e.printStackTrace()
      }

    }
  }

  private fun redraw(): Long {
    val t = System.currentTimeMillis()
    val deltaTime: Float = (t - timeLastRunMs) / 1000f

    updateState(deltaTime)

    // asynchronously signals the paint to happen in the swing thread
    repaint()

    // use a lock here that is only released once the paintComponent
    // has happened so that canvas.repaint() calls don't queue up that
    // are delayed and we get jerky drawing

    // waitForPaint()

    timeLastRunMs = t
    return System.currentTimeMillis() - t
  }

  /**
   * @param dt Delta time since last render devided by 1000
   */
  private fun updateState(dt: Float) {
    val translate = 400 * dt

    if (wIsDown) {
      player.y -= translate
    }
    if (sIsDown) {
      player.y += translate
    }

    if (aIsDown) {
      player.x -= translate
    }

    if (dIsDown) {
      player.x += translate
    }

  }

  private fun paint() {
    g2D?.color = Color(54, 214, 250)
    g2D?.fillRect(
      player.x.toInt(), player.y.toInt(), player.width.toInt(), player.height.toInt()
    )
  }

  override fun paintComponent(g: Graphics?) {
    super.paintComponent(g)
    val g2D = g as Graphics2D
    this.g2D = g2D
    this.g = g

    // Smoothing up things so that we get good graphics instead of pixely things
    val rh = RenderingHints(
      RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
    )
    rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
    g2D.setRenderingHints(rh)

    // Before painting :
    // - in debug mode showing the guide is essential to know what is being done
//        drawGuide()
//
    paint()

    // After painting :
    // - in debug mode Showing some info about current location and frame rates are essential
    // TODO: add debugging stuff.

    Toolkit.getDefaultToolkit().sync()
    g.dispose()
  }
}
