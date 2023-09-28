package sub4_jvmstatic

class CompanionObject {

    companion object {

        @JvmField
        val id = 123

        const val OBJECT_NAME = "AA"

        @JvmStatic
        fun hello() = "Hello"
    }
}