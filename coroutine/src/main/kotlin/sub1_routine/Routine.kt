package sub1_routine

/**
 * Routine
 *
 * - 프로그램에서 하나의 "실행 단위(작업 흐름)"를 의미
 * - 함수(Function), 메서드(Method) 형태로 표현됨
 *
 * 실행 특징:
 * - 호출되면 위 → 아래로 순차 실행됨
 * - 실행 중 중단(suspend)하거나 상태를 저장할 수 없음
 *      - routine() 실행 중일 때 초기화된 stack 영역의 지역변수(num1, num2)는 반환되면 다시 접근 불가
 * - 작업이 끝나면 호출한 지점으로 되돌아감 (call stack 기반)
 *
 * 이 코드에서의 루틴:
 * - main()      : 프로그램 시작 루틴 (엔트리 포인트)
 * - routine()   : 계산을 수행하는 일반 루틴
 */
fun main() {
    println("start")
    routine()
    println("end")
}

fun routine() {
    val num1 = 1
    val num2 = 2
    println("total = ${num1 + num2}")
}