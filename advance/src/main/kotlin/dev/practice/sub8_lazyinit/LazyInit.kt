package dev.practice.sub8_lazyinit

import kotlin.concurrent.thread

/**
 * 코틀린에서의 지연 초기화에 대해 알아본다.
 *
 * 아래 코드는 정상 동작한다.
 *
 * 하지만..
 * 지연 초기화를 위해 가변 변수를 사용하였는데..
 * 가변 변수를 사용하는 것은 몇가지 위험성이 존재하므로 될 수 있으면 불변 변수로 사용하는게 좋다.. -> DataClassAndCopy.kt 에 나온다.
 * -> 이를 위해 코틀린에서는 불변을 유지하면서 지연 초기화를 사용할 수 있는 기능을 제공한다.
 */

class HelloBot {
    // 지연 초기화를 위해 가변변수 var 키워드를 사용, 안전 연산자를 사용하여 nullable type 으로 선언(null 참조가 이루어져도 NPE 발생하지 않음)
    var greeting: String? = null

    fun sayHello() = println(greeting)
}

/**
 * by lazy
 *
 * val 키워드만 가능하다.
 *
 * 변수를 사용하는 최초의 시점에 1회 초기화가 이루어진다.
 * -> lazy 함수 내부에 보면 구현체로 SynchronizedLazyImpl 를 사용한다.
 * -> 이는 multi-thread 환경에서도 안전하게 수행할수 있도록(thread-safe) 락을 통해 동기화 처리가 되어있는 것을 볼 수 있다.
 * -> 멸티 스레드 환경에서도 최초 1회만 초기화 이루어짐
 */
class HelloBotWithByLazy {
    // 지연 초기화도 하고 싶고.. 가변변수도 사용하고 싶지 않을때 사용
    // lazy 파라미터로 LazyThreadSafetyMode.NONE 으로 하면 락을 통해 동기화 처리를 하지 않는다.
    // 즉, 멀티스레드환경에서 thread-safe 하지 않다.. 동일한 객체를 가지고 동시에 lazy 함수를 호출하게 하면 초기화가 여러번 이루어질수 있다는 것이다.
    // -> 실행마다 초기화 횟수는 달라질 수 있다.
    // 기본값은 LazyThreadSafetyMode.SYNCHRONIZED 이다.
    // TODO LazyThreadSafetyMode.NONE 와 LazyThreadSafetyMode.PUBLICATION 차이
    val greeting: String by lazy {
        println("지연 초기화")
        getHello()
    }

    fun sayHello() = println(greeting)
}


fun getHello() = "Hello"

fun main() {

    val helloBot = HelloBot()

    //..
    //do something
    //..

    helloBot.greeting = getHello() // 지연 초기화
    helloBot.sayHello()

    // -----------------------------------------------------------

    val helloBotWithByLazy = HelloBotWithByLazy()

    //..
    //do something
    //..

    helloBotWithByLazy.sayHello() // sayHello 함수 내부에 greeting 변수가 사용된다. 이때 초기화가 이루어진다.


    // -----------------------------------------------------------

    val helloBotWithByLazy2 = HelloBotWithByLazy()

    //..
    //do something
    //..

    for(i in 1 .. 5) {
        thread {
            helloBotWithByLazy2.sayHello() // multi thread 환경에서도 최초 1회만 초기화 된다.
        }
    }
}