package dev.practice.sub11_use

import java.io.FileWriter

/**
 * 코틀린에서 예외처리에 대해 알아본다.
 *
 * Java 에서는 JDK 7 부터 제공하는 try-with-resource 구문을 사용하면 자동으로 리소스를 close 처리해준다.
 * Closeable 클래스(AutoCloseable 인터페이스 구현) 에 대해 자동으로 close 메서드를 호출한다.
 * -> java.TryWithResource.java 참고
 *
 * 코틀린에서는 use 라는 확장 함수를 사용하면 된다.
 */

fun main() {

    FileWriter("test.txt")
        .use { // use 구현을 보면 finally 에서 close 를 처리해주고 있는 것을 볼 수 있다.
            it.write("Hello, world!!")
        }
}