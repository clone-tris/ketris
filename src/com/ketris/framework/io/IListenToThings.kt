package com.ketris.framework.io

interface IListenToThings<T> {
  val listeners: MutableList<T>

  fun addListener(listener: T) {
    listeners.add(listener)
  }

  fun removeListener(listener: T) {
    listeners.remove(listener)
  }
}
