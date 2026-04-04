package sub11_coroutine_scope.sub3_exception

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * launch vs async 3 - SupervisorJob
 *
 *      LaunchVsAsync2 에서는 runBlocking 안에서 만든 자식 코루틴의 실패가
 *      부모(runBlocking) 쪽으로 전파될 수 있다는 점을 봤다.
 *
 *      이번 예제는 launch / async 를 시작할 때 SupervisorJob() 을 추가해서
 *      "부모-자식 예외 전파를 일부 끊은 경우"를 본다.
 *
 * SupervisorJob 이란?
 *      일반 Job 은 자식 실패가 부모 취소로 이어질 수 있다.
 *      반면 SupervisorJob 은
 *      "자식 하나의 실패가 곧바로 다른 형제나 부모 전체 실패로 번지는 것"을 막기 위한 Job 이다.
 *
 *      지금 단계에서는 이렇게 이해하면 된다.
 *          - Job            : 자식 실패가 부모 쪽에 강하게 전파될 수 있음
 *          - SupervisorJob  : 그 전파를 완화해서 더 독립적으로 관리함
 *
 * 이 예제의 포인트
 *      1. async(SupervisorJob()) 도 예외를 Deferred 안에 저장한다.
 *      2. launch(SupervisorJob()) 는 예외를 await 할 그릇이 없으므로
 *         처리되지 않으면 uncaught 예외 경로로 간다.
 *      3. 하지만 둘 다 일반 자식(Job)처럼 runBlocking 을 바로 실패시키는 흐름과는 다르게 볼 수 있다.
 *
 */
fun main(): Unit = runBlocking {

    /**
     * async + SupervisorJob
     *
     *      SupervisorJob 을 추가했기 때문에
     *      이 async 는 일반적인 runBlocking 자식과는 다른 Job 경계를 가진다.
     *
     *      그래도 async 라는 사실은 그대로이므로
     *      예외는 즉시 현재 runBlocking 으로 던져지지 않고 Deferred 안에 저장된다.
     *
     *      따라서 join() 은 완료만 기다리고,
     *      실제 예외를 현재 코루틴에서 받고 싶다면 await() 가 필요하다.
     */
    val job1 = async(SupervisorJob()) {
        throw IllegalStateException("async exception")
    }

    job1.join()

    /**
     * launch + SupervisorJob
     *
     *      launch 는 예외를 담아둘 Deferred 가 없다.
     *      따라서 launch 블록 내부 예외를 현재 코루틴이 직접 꺼내는 구조가 아니다.
     *
     *      여기서도 SupervisorJob 이 붙어 있으므로
     *      LaunchVsAsync2 처럼 단순한 "부모 runBlocking 자식 실패 전파"와는 결이 다르다.
     *
     *      하지만 launch 자체의 성격은 유지되므로
     *      예외가 내부에서 처리되지 않으면 uncaught 예외 처리 경로로 넘어갈 수 있다.
     */
    val job2 = launch(SupervisorJob()) {
        throw IllegalStateException("launch exception")
    }

    delay(1000)
    myPrint("runBlocking end") // 정상 출력됨
}

