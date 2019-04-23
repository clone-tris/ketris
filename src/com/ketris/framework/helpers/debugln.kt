import com.ketris.Config.ENABLE_LOG

fun log(message: String) {
  @Suppress("ConstantConditionIf") if (ENABLE_LOG) {
    println(message)
  }
}
