package com.ketris.framework.io

import java.awt.event.MouseEvent

interface IListenToMouse {
  fun mousePressed(e: MouseEvent) {}
}
