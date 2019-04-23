import com.ketris.core.Config.ENABLE_LOG

fun log(message: String) {
  @Suppress("ConstantConditionIf") if (ENABLE_LOG) {
    println(message)
  }
}
