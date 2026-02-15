package sub26_as_import

import sub26_as_import.a.printHelloWorld as printHelloWorldA
import sub26_as_import.b.printHelloWorld as printHelloWorldB

/**
 * 서로 다른 패키지인데 함수 시그니처가 동일..할 때
 * as 키워드를 활용할 수 있다.
 */

fun main() {
    printHelloWorldA()
    printHelloWorldB()
}