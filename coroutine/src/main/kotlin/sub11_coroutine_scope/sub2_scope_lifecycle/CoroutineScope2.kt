package sub11_coroutine_scope.sub2_scope_lifecycle

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * CoroutineScope 2 - lifecycle and cancellation
 *
 *      CoroutineScope 가 중요한 진짜 이유는
 *      "누가 부모이고, 취소가 어디까지 퍼지는가"를 정하기 때문이다.
 *
 *      쉽게 말하면:
 *          - 부모 코루틴에서 그냥 launch 하면 부모의 자식이 된다.
 *          - 새 CoroutineScope(Job() + ...) 를 만들면 새 부모 집합을 하나 더 만드는 것이다.
 *
 * 부모에서 상속되는 경우
 *      runBlocking 안에서 launch { ... } 를 호출하면
 *      그 코루틴은 runBlocking 의 자식이 된다.
 *
 *      이 경우 부모를 cancel 하면 자식에게 취소가 전파된다.
 *
 * 새 부모를 만드는 경우
 *      CoroutineScope(Job() + ...) 를 직접 만들면
 *      그 scope 는 독립된 Job 을 가진다.
 *
 *      여기서 독립된다는 말은
 *      "현재 runBlocking 의 Job 과는 다른 부모 Job 을 가진다"는 뜻이다.
 *      즉, 취소와 완료 관리가 별도 그룹으로 나뉜다.
 *
 *      따라서 이 scope 아래 launch 한 코루틴들은
 *      방금 만든 그 Job 을 부모로 삼는다.
 *
 *      즉,
 *          runBlocking 의 Job
 *              └─ inherited child
 *
 *          independent scope 의 Job
 *              ├─ independent child1
 *              └─ independent child2
 *
 *      이런 식으로 부모 그룹이 나뉜다.
 *
 * 이 예제의 목적
 *      - runBlocking 의 자식 하나를 만든다.
 *      - 별도 scope 의 자식 둘을 만든다.
 *      - independent scope 만 cancel 해서
 *        같은 부모를 가진 애들만 함께 취소되는 것을 본다.
 *
 *      그러면 "취소가 부모 단위로 전파된다"는 점이 더 명확해진다.
 */
fun main(): Unit = runBlocking {

    val inheritedChild = launch(CoroutineName("inherited-child")) {
        try {
            repeat(5) { index ->
                myPrint("inherited child tick ${index + 1}")
                delay(300L)
            }
            myPrint("inherited child completed normally")
        } finally {
            myPrint("inherited child finally")
        }
    }

    val independentScope = CoroutineScope(
        Job() + Dispatchers.Default + CoroutineName("study-scope")
    )

    val job1 = independentScope.launch {
        try {
            repeat(10) { index ->
                myPrint("independent job1 tick ${index + 1}")
                delay(300L)
            }
            myPrint("independent job1 completed normally")
        } finally {
            myPrint("independent job1 finally")
        }
    }

    val job2 = independentScope.launch {
        try {
            repeat(10) { index ->
                myPrint("independent job2 tick ${index + 1}")
                delay(300L)
            }
            myPrint("independent job2 completed normally")
        } finally {
            myPrint("independent job2 finally")
        }
    }

    delay(950L)
    myPrint("cancel independent scope only")
    independentScope.cancel()

    joinAll(inheritedChild, job1, job2)
    myPrint("all jobs finished")
}

/**
 * 실행 결과 예시
 *
 *      [main] inherited child tick 1
 *      [DefaultDispatcher-worker-1] independent job1 tick 1
 *      [DefaultDispatcher-worker-2] independent job2 tick 1
 *      [main] inherited child tick 2
 *      [DefaultDispatcher-worker-1] independent job1 tick 2
 *      [DefaultDispatcher-worker-2] independent job2 tick 2
 *      [main] cancel independent scope only
 *      [DefaultDispatcher-worker-1] independent job1 finally
 *      [DefaultDispatcher-worker-2] independent job2 finally
 *      [main] inherited child tick 3
 *      [main] inherited child tick 4
 *      [main] inherited child tick 5
 *      [main] inherited child completed normally
 *      [main] inherited child finally
 *      [main] all jobs finished
 *
 * 설명
 *      - inherited child 는 runBlocking 의 자식이다.
 *      - independent job1, job2 는 independentScope 의 자식들이다.
 *
 *      - independentScope.cancel() 을 호출했기 때문에
 *        independent job1, job2 에게만 취소가 전파된다.
 *      - 그래서 두 코루틴은 delay() 에서 취소를 감지하고 finally 로 들어간다.
 *        delay() 는 cancellable suspend 함수이므로
 *        취소 상태를 확인하고 CancellationException 흐름으로 종료 절차를 밟는다.
 *
 *      - 반면 inherited child 는 부모가 다르다.
 *        그래서 independentScope 를 cancel 해도 영향을 받지 않고 끝까지 실행된다.
 *
 *      - 이 차이가 바로 scope 를 나누는 이유다.
 *        어떤 코루틴들을 같은 생명주기로 묶을지, 어떤 코루틴들을 독립적으로 관리할지를
 *        scope / Job 경계로 결정할 수 있다.
 *
 * 핵심
 *      - 취소는 "같은 부모 Job 아래에 묶인 코루틴들"을 따라 전파된다.
 *      - 새 CoroutineScope(Job() + ...) 를 만들면 새 부모 그룹을 만드는 셈이다.
 *      - 따라서 어떤 코루틴이 누구의 자식인지가 lifecycle 을 결정한다.
 */
