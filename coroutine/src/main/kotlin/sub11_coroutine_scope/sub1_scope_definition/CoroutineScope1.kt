package sub11_coroutine_scope.sub1_scope_definition

import kotlin.coroutines.ContinuationInterceptor
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * CoroutineScope 1 - definition
 *
 *      CoroutineScope 는 흔히 "코루틴 범위"라고 부르지만,
 *      실제로는 coroutineContext 를 가진 인터페이스이다.
 *
 *      public interface CoroutineScope {
 *          public val coroutineContext: CoroutineContext
 *      }
 *
 *      sub10 에서 봤듯이 coroutineContext 안에는
 *          - Job
 *          - Dispatcher
 *          - CoroutineName
 *      같은 정보가 들어 있다.
 *
 *
 * launch / async 와의 관계
 *      launch, async 는 CoroutineScope 의 확장 함수이다.
 *
 *
 *      즉, launch 를 호출한다는 것은
 *      "어떤 scope 를 부모 기준으로 삼아서 자식 코루틴을 만들겠다"는 뜻이다.
 *
 *      그 순간 함께 정해지는 것
 *          - 어떤 context 를 상속할지
 *          - 누구의 자식 Job 이 될지
 *          - 취소가 어디까지 전파될지
 *
 * 이 예제의 목적
 *      1. runBlocking 안에서 이미 부모 scope 가 하나 존재한다는 점을 본다.
 *      2. CoroutineScope(...) 로 새 scope 를 직접 만들 수 있다는 점을 본다.
 *      3. "부모에게서 상속받는 경우"와 "새 부모를 만드는 경우"를 구분해서 본다.
 *
 */
fun main(): Unit = runBlocking {

    printScopeInfo("runBlocking scope", this)

    val inheritedChild = launch(CoroutineName("inherited-child")) {
        myPrint("child of runBlocking")
    }

    val scope = CoroutineScope(
        Job() + Dispatchers.Default + CoroutineName("study-scope")
    )
    printScopeInfo("new independent scope", scope)

    val independentChild = scope.launch {
        myPrint("child of new independent scope")
    }

    inheritedChild.join()
    independentChild.join()
    myPrint("done")
}

private fun printScopeInfo(label: String, scope: CoroutineScope) {
    myPrint(
        "$label, " +
            "job=${scope.coroutineContext[Job]}, " +
            "dispatcher=${scope.coroutineContext[ContinuationInterceptor]}, " +
            "name=${scope.coroutineContext[CoroutineName]}"
    )
}

/**
 * 실행 결과 예시
 *
 *      [main] runBlocking scope, job=BlockingCoroutine{Active}@..., dispatcher=BlockingEventLoop@..., name=null
 *      [main] new independent scope, job=JobImpl{Active}@..., dispatcher=Dispatchers.Default, name=CoroutineName(study-scope)
 *      [main] child of runBlocking
 *      [DefaultDispatcher-worker-1] child of new independent scope
 *      [main] done
 *
 * 설명
 *      - runBlocking 블록 안에서는 이미 부모 scope 가 하나 만들어져 있다.
 *        따라서 그냥 launch { ... } 를 호출하면 그 runBlocking scope 의 자식 코루틴이 된다.
 *
 *      - 반면 CoroutineScope(Job() + Dispatchers.Default + ...) 를 직접 만들면
 *        runBlocking 과는 별개의 새 부모 기준을 하나 만든 것이다.
 *        즉, "부모 Job 을 새로 하나 만든다"에 가깝다.
 *
 *      - 그래서 launch { ... } 와 scope.launch { ... } 는 둘 다 "자식 코루틴 생성"이지만
 *        누구의 자식인지가 다르다.
 *
 *      - 첫 번째 자식은 runBlocking 의 context 를 상속하므로 main 쪽에서 실행된다.
 *      - 두 번째 자식은 새 scope 의 Dispatchers.Default 를 상속하므로
 *        worker thread 에서 실행될 수 있다.
 *
 * 핵심
 *      - CoroutineScope 의 핵심은 coroutineContext 다.
 *      - launch / async 는 "현재 scope 의 자식 코루틴"을 만든다.
 *      - 새 CoroutineScope(...) 를 만들면 새 부모 기준을 하나 더 만드는 셈이다.
 */
