package sub2_exception

import java.io.IOException

class KotlinThrow {

    fun throwCheckedException() {
        throw IOException()
    }

    @Throws(IOException::class) // Java 코드에서 기존 처럼 예외 처리가 강제되도록 해준다.
    fun throwCheckedException2() {
        throw  IOException()
    }
}