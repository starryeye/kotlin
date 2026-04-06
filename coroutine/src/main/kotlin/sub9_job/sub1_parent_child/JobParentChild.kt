package sub9_job.sub1_parent_child

import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.myPrint

/**
 * Job - parent and child (Job 을 코루틴이라 부르기도함)
 *
 *      현재 코루틴 안에서 launch { ... } 로 새 코루틴을 만들면
 *      그 새 코루틴은 현재 코루틴의 자식 Job 이 된다.
 *
 *      예를 들면 runBlocking 안에서 launch 하면
 *
 *          runBlocking(Job)
 *              └─ launch(Job)
 *
 *      같은 구조가 만들어진다.
 *
 *      이 관계가 중요한 이유는 두 가지다.
 *          1. 부모는 자식이 끝날 때까지 기다린다.
 *          2. 부모가 취소되면 자식에게도 취소가 전파된다.
 *
 *      아래 예제는 이 두 가지를 순서대로 보여준다.
 *
 * 참고
 *      코루틴(Job)의 Lifecycle 에 대해서는 sub11 > sub6 을 참고
 */
fun main() {
    myPrint("example1 start")
    example1()
    myPrint("example1 end")

    println()

}

private fun example1() = runBlocking {
    myPrint("example1 parent start, current job = ${coroutineContext.job}, parent job = ${coroutineContext.job.parent}")

    launch {
        myPrint("example1 child start, job = ${coroutineContext.job}, parent job = ${coroutineContext.job.parent}")
        delay(1000L)
        myPrint("example1 child end")
    }

    myPrint("example1 parent end line")
}


/**
 * example1 설명
 *      - runBlocking 이 부모 Job 이다.
 *      - launch 로 만든 코루틴은 자식 Job 이다.
 *      - 부모 코드의 마지막 줄이 먼저 실행되어도
 *        부모(runBlocking)는 자식이 끝날 때까지 바로 종료되지 않는다.
 *
 *      즉, "부모가 자식을 기다린다"는 말은
 *      부모 블록의 마지막 코드가 끝났어도
 *      자식 Job 이 남아 있으면 부모 전체는 아직 끝난 것이 아니라는 뜻이다.
 *
 *
 * 취소 전파는 언제 보이나?
 *      - 부모 Job 이 실제로 cancel 되면 자식에게도 취소가 전파된다.
 *      - 그 경우 자식은 delay 같은 cancellable suspend 함수에서 취소를 감지한다.
 *      - 그 흐름은 다음 scope / context 장에서 더 자세히 이어서 본다.
 *
 * 핵심
 *      - launch 로 만든 코루틴은 현재 코루틴의 자식 Job 이 된다.
 *      - 부모는 자식이 끝날 때까지 종료되지 않는다.
 *      - 그래서 Job 의 부모/자식 관계는 "누가 누구를 관리하는가"를 나타낸다.
 */
