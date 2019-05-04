import com.ketris.GameConfig.ENABLE_LOG

fun log(message: String) {
  @Suppress("ConstantConditionIf") if (ENABLE_LOG) {
    println(message)
  }
}
