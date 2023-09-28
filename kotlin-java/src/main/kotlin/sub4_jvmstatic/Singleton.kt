package sub4_jvmstatic

object Singleton {

    @JvmField
    val id = 456

    const val SINGLETON_NAME = "BBB"

    @JvmStatic
    fun world() = "world"
}