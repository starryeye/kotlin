package sub10_coroutine_context.sub2_context_merge

import kotlin.coroutines.ContinuationInterceptor
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * CoroutineContext 2 - merge and inheritance
 *
 *      CoroutineContext 는 "+" 연산으로 합성할 수 있다.
 *
 *      여기서 합성한다는 것은 "여러 설정 조각을 하나의 context 로 묶는다"는 뜻이다.
 *
 *      예:
 *          Job() + Dispatchers.Default + CoroutineName("worker")
 *      이렇게 만들면
 *          - job 설정은.. Job()
 *          - dispatcher 설정은.. Dispatchers.Default
 *          - 이름은 CoroutineName("worker")
 *      로 정해진다.
 *
 *
 * 상속 방식
 *      부모 코루틴에서 launch(context = ...) 를 호출하면, 부모 코루틴이 가지고 있던 context 값을 자식이 기본값으로 물려받는다
 *          부모 coroutineContext + launch 에 전달한 추가 context
 *          -> 자식 context
 *
 *      즉,
 *          - 지정하지 않은 요소는 부모 것을 상속한다.
 *          - 같은 key 를 새로 지정하면 그 요소만 덮어쓴다.
 *
 *
 * 이 예제의 목적
 *      - 부모 코루틴의 context 를 출력한다.
 *      - launch 에서 이름만 추가한 경우 무엇이 상속되는지 본다.
 *      - launch 에서 Dispatcher 까지 바꾼 경우 무엇이 덮어써지는지 본다.
 */
fun main(): Unit = runBlocking(CoroutineName("parent")) {

    myPrint(
        "parent, " +
            "job=${coroutineContext[Job]}, " +
            "dispatcher=${coroutineContext[ContinuationInterceptor]}, " +
            "name=${coroutineContext[CoroutineName]}"
    )

    val child1 = launch(CoroutineName("child-name-only")) {
        myPrint(
            "child1, " +
                "job=${coroutineContext[Job]}, " +
                "dispatcher=${coroutineContext[ContinuationInterceptor]}, " +
                "name=${coroutineContext[CoroutineName]}"
        )
    }

    val child2 = launch(Dispatchers.Default + CoroutineName("child-default")) {
        myPrint(
            "child2, " +
                "job=${coroutineContext[Job]}, " +
                "dispatcher=${coroutineContext[ContinuationInterceptor]}, " +
                "name=${coroutineContext[CoroutineName]}"
        )
    }

    child1.join()
    child2.join()
}

/**
 * 실행 결과 예시
 *
 *      [main] parent, job=BlockingCoroutine{Active}@..., dispatcher=BlockingEventLoop@..., name=CoroutineName(parent)
 *      [main] child1, job=StandaloneCoroutine{Active}@..., dispatcher=BlockingEventLoop@..., name=CoroutineName(child-name-only)
 *      [DefaultDispatcher-worker-1] child2, job=StandaloneCoroutine{Active}@..., dispatcher=Dispatchers.Default, name=CoroutineName(child-default)
 *
 * 설명
 *      - child1 은 이름만 새로 지정했다.
 *        그래서 Dispatcher 는 부모 것을 그대로 상속하고,
 *        이름만 child-name-only 로 바뀐다.
 *        즉, 부모 context 에서 name 항목만 새 값으로 바꾼 셈이다.
 *
 *      - child2 는 Dispatchers.Default 와 이름을 함께 지정했다.
 *        그래서 Dispatcher 는 부모 것 대신 Default 로 덮어써진다.
 *        이때 Job 도 부모와 완전히 같은 객체를 쓰는 것이 아니라
 *        "부모 아래 연결된 새 자식 Job"으로 만들어진다.
 *
 *      - 두 자식 모두 자기 자신의 Job 을 새로 가진다.
 *        다만 그 Job 들은 부모 코루틴 아래에 연결된 자식 Job 이다.
 *
 *      - 그래서 launch(context = ...) 는
 *        기존 코루틴과 완전히 무관한 새 세계를 만드는 것이 아니라,
 *        기본적으로는 부모 코루틴 위에 자식을 만들면서 일부 설정만 바꾸는 방식이다.
 *
 * 핵심
 *      - 자식 coroutineContext 는 부모 context 를 기반으로 만들어진다.
 *      - 추가한 element 가 같은 key 를 가지면 그 요소만 덮어써진다.
 *      - 그래서 launch(context = ...) 는 "전부 새로 만드는 것"이 아니라
 *        "부모 context 에 일부 설정을 더하는 것"에 가깝다.
 */
