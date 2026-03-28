package util

fun myPrint(str: String) {
    /**
     * VM 옵션에
     *      -Dkotlinx.coroutines.debug
     * 을 추가해주면 Thread.currentThread().name 에 현재의 코루틴 정보가 함께 출력된다.
     */
    println("[${Thread.currentThread().name}] $str]")
}