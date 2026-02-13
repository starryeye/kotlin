package sub24_collection_chain.sub9_associateby

import sub24_collection_chain.Fruit

fun main() {

    val fruits = getFruits()

    // associateBy
    //      람다 결과를 기준으로 매핑된다.. (Map<람다결과, 원소> 로 나옴.)
    //      key 는 그루핑된 기준 값, value 는 대상 인스턴스
    //      참고
    //          여러개의 원소가 동일한 key 에 매핑되더라도 하나만 들어간다.
    val result1: Map<Long, Fruit> = fruits.associateBy { fruit -> fruit.id }

    println(result1::class) // 이렇게 하면 JVM runtime class 메타데이터 정보가 나와서.. 의미 없음.. (Kotlin 타입 보고 싶으면 변수 위에 마우스 커서올려라..)
    println(result1)

    //      associateBy 파라미터에 하나가아닌 두개의 람다가 들어가면.. (Map<첫번째람다결과, 두번째람다결과> 로 나옴.)
    //      key 는 첫번째 람다 결과, value 는 대상 인스턴스의 두번째 람다 결과로 구성
    val result2 = fruits.associateBy({ fruit -> fruit.id }, { fruit -> fruit.factoryPrice })

    println(result2)
}

private fun getFruits(): List<Fruit> = listOf(
    Fruit(1L, "apple", 10_000L, 12_000L),
    Fruit(2L, "apple", 9_500L, 11_500L),
    Fruit(3L, "apple", 11_000L, 13_000L),

    Fruit(4L, "banana", 5_000L, 6_000L),
    Fruit(5L, "banana", 4_800L, 5_800L),
    Fruit(6L, "banana", 5_200L, 6_200L),

    Fruit(7L, "orange", 7_000L, 8_500L),
    Fruit(8L, "orange", 6_800L, 8_200L),

    Fruit(9L, "grape", 15_000L, 18_000L),
    Fruit(10L, "grape", 14_500L, 17_500L),

    Fruit(11L, "watermelon", 20_000L, 25_000L),
    Fruit(12L, "watermelon", 19_000L, 24_000L),

    Fruit(13L, "peach", 8_000L, 9_500L),
    Fruit(14L, "peach", 8_500L, 10_000L)
)