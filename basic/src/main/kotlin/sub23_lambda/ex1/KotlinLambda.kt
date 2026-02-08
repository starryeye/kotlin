package sub23_lambda.ex1

import sub23_lambda.ex1.Fruit
import kotlin.collections.forEach

/**
 * Java 에서는 코드조각(함수)을 변수에 할당하거나 리턴값으로 넘길수 있는 것 "처럼" 설명하지만..
 * 사실상 함수형 "인터페이스"를 구현한 객체(람다, 익명 함수)를 변수에 할당하거나 리턴값으로 넘길 수 있으므로..
 * Java 의 메서드는 2급 시민이다.
 *
 * Kotlin 에서는 함수가 실제로 1급 시민이다.
 */

fun main() {
    val fruits = listOf(
        Fruit("apple", 10_000),
        Fruit("apple", 12_000),
        Fruit("apple", 12_000),
        Fruit("apple", 15_000),
        Fruit("banana", 15_000),
        Fruit("banana", 15_000),
        Fruit("banana", 18_000),
        Fruit("water melon", 20_000),
    )

    /**
     * 익명 함수(람다) 를 선언하는 방법 2가지
     *      (fun 이후 함수 이름이 없는 것을 볼 수 있음..)
     *
     * 참고.
     *      익명함수 중괄호 버전에서 여러줄일 경우 return 을 따로 사용하지 않아도 마지막줄 결과가 return 된다.
     */
    // 람다 선언 1번 방법
    val isApple: (Fruit) -> Boolean = fun(fruit: Fruit): Boolean { // 해당 변수의 타입은 "(Fruit) -> Boolean" 으로 추론되므로 생략가능
        return fruit.name == "apple"
    }
    // 람다 선언 2번 방법
    val isBanana = { fruit: Fruit -> fruit.name == "banana" } // 변수의 타입이 생략됨.
    // 참고 예시
    val isWater = { fruit: Fruit ->
        val result = (fruit.name == "water")
        result // return 생략 가능
    }


    /**
     * 익명 함수(람다) 를 실행하는 방법 2가지
     */
    // 람다 실행 1번 방법
    isApple(fruits[0])

    // 람다 실행 2번 방법
    isBanana.invoke(fruits[1])


    /**
     * 함수의 마지막 파라미터가 함수일 경우 특별하게 아래와 같이.. 함수 파라미터 외부로 중괄호를 사용해서 .. 가능..
     */
    filterFruits(fruits) { fruit: Fruit -> fruit.name == "banana" }
    filterFruits(fruits) { fruit -> fruit.name == "banana" } // 타입 생략 가능
    filterFruits(fruits) { it.name == "banana" } // 익명함수의 파라미터가 한개일 경우 it 지시어를 사용할 수 있다.


    /**
     * Java 에서는 람다에서 외부 변수를 사용할 때.. effectively final 변수여야 사용가능 했지만..
     * Kotlin 에서는 람다가 실행되는 시점에 쓰고 있는 모든 변수를 포획하는 데이터구조(Closure) 를 가지기 때문에
     *      effectively final 변수가 아니라도 가능하다....
     */
    var targetFruitName: String = "banana"
    targetFruitName = "apple"
    val filtered = filterFruits(fruits) { it.name == targetFruitName }
    filtered.forEach { println(it) }


    /**
     * 람다 함수 분석해보기.. 다음은 Closeable.kt 에 정의된 함수이다.
     * public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {...}
     *
     * 분석해보자면..
     * Closeable 의 구현체에 대한 확장함수 use 이다.
     *      T 는 Closeable 에 대해 상속함
     *      T.use 를 사용해서 T 에 대한 확장함수임을 선언
     * 파라미터로는 익명함수를 받는다.
     *      block: (T) -> R
     *          파라미터 이름은 block, 타입은 (T) -> R
     * 리턴 타입은 R 이다.
     *      : R
     */


}

private fun filterFruits(
    fruits: List<Fruit>,
    filter: (Fruit) -> Boolean // 함수를 파라미터로 받을 수 있다.
): List<Fruit> {

    val result = mutableListOf<Fruit>()

    for (fruit in fruits) {
        if (filter(fruit)) {
            result.add(fruit)
        }
    }
    return result
}