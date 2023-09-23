package dev.practice.sub1_collection

import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList


/**
 * 코틀린의 Collection 에 대해 알아본다.
 *
 * mutable, immutable
 *
 * 요소들의 접근과 변경
 *
 * stream..
 */
fun main() {

    //immutable list
    val immutableCityList = listOf("seoul", "daegu", "busan")

    //mutable list
    val mutableCityList = mutableListOf<String>()
    mutableCityList.add("seoul") // 생성자를 통해 초기화도 가능하지만, mutable 이므로 CRUD 함수 사용가능
    mutableCityList.add("daegu")
    mutableCityList.add("busan")

    val mutableCityList2 = mutableListOf<String>().apply {// inline 함수로 add
        // apply 는 java 로 치면 람다 consumer 함수형 인터페이스를 받는데.. 지금 보는것처럼 람다식을 작성하는 문법이
        // 다른 것을 알수 있다.. Java 에선.. apply(() -> {})
        this.add("seoul") // this 를 생략 할 수 있다.
        add("daegu")
        add("busan")
    }

    //--------------------------------------------

    //immutable set
    val immutableNumberSet = setOf(1, 2, 3)

    //mutable set
    val mutableNumberSet = mutableSetOf<Int>().apply {
        add(1)
        add(2)
        add(3)
    }

    //--------------------------------------------

    //immutable map
    val immutableNumberMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    val key = "one"
    val value = immutableNumberMap.get("one")
    println(value)

    //mutable map
    val mutableNumberMap = mutableMapOf<String, Int>()
    mutableNumberMap["one"] = 1
    mutableNumberMap.put("two", 2) //이와 같이 넣을 수도 있지만, "one" 처럼 넣는 것을 권장함
    mutableNumberMap["three"] = 3


    //--------------------------------------------


    // 특정 구현체를 사용하고 싶을 경우엔 그냥 구현체를 직접 사용하면된다.

    // linked list ... java class 임.....
    val linkedList = LinkedList<Int>().apply {
        addFirst(1)
        add(2)
        addLast(3)
    }

    // array list
//    arrayListOf() //이걸로도 가능
    ArrayList<Int>().apply {
        add(1)
        add(2)
        add(3)
    }

    //--------------------------------------------

    // Collection 은 Iterable 를 상속 받으므로 순차접근이 가능함
    val iterator = immutableCityList.iterator()
    while (iterator.hasNext()) {
        println("iterator : ${iterator.next()}")
    }

    // 일반적인 for loop
    for (city in immutableCityList) {
        println("forEach : $city")
    }

    // forEach 사용 (람다식)
    immutableCityList.forEach {
        println(it) //it.. 람다..
    }

    // 메서드 레퍼런스도 가능한듯
    immutableCityList.forEach(System.out::println)

    // stream 은 자바 코드이다. stream 을 사용하면 자바와 같이 map 에는 stream 이 반환될 뿐이다. (타입 참고..)
    // toList 를 사용하면 mutable list 가 반환되긴 하네..
    val map: Stream<String> = immutableCityList.stream()
        .map { it.uppercase() }


    // 아래와 같이 코틀린 Collection 의 inline 함수 map 을 사용하면 자바에서 toList 쓴것처럼 실제 uppercase 가 수행된 것이 반환된다. (타입 참고..)
    val map1: List<String> = immutableCityList.map { it.uppercase() }

    // Java 의 Stream 처럼 intermediate operation, terminal operation 개념으로 사용하려면... asSequence 를 사용하면 된다.
    // asSequence 를 사용하지 않으면.. 각 intermediate operation 별로 collection 이 하나씩 만들어지는 비효율적인 메모리 사용이 생긴다...
    // 사용하는 데이터가 많으면 asSequence 를 사용하자. (하지만, 데이터가 많지 않다면... 5만건 이하.. asSequence 없는게 퍼포먼스가 더 좋긴함)
    val toList = immutableCityList
        .asSequence()
        .map { it.uppercase() }
        .toList()

    // filter 도 써보자..
    val filter = map1.filter { it.startsWith("D") }
    println("filtered : $filter")

}