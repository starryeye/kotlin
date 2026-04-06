package sub6_dispatchers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import util.myPrint
import java.util.concurrent.Executors

/**
 * ExecutorService 를 Dispatcher 로 사용하기 위해서는 asCoroutineDispatcher() 확장함수를 이용한다.
 */
fun main() {
    val threadPool = Executors.newSingleThreadExecutor()
    CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
        myPrint("launch start")
    }
}