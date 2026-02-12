package sub24_collection_chain.sub8_groupby

import sub24_collection_chain.Fruit

fun main() {

    val fruits = getFruits()

    // groupBy
    //      람다 결과를 기준으로 그룹핑된다.. (Map 으로 나옴.)
    //      key 는 그루핑된 기준 값, value 는 그루핑 대상 인스턴스
    val result1: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
0
    println(result1::class) // 이렇게 하면 JVM runtime class 메타데이터 정보가 나와서.. 의미 없음.. (Kotlin 타입 보고 싶으면 변수 위에 마우스 커서올려라..)
    println(result1)
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