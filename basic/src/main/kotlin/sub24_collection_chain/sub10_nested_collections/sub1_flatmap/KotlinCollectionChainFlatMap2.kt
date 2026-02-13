package sub24_collection_chain.sub10_nested_collections.sub1_flatmap

import sub24_collection_chain.Fruit

fun main() {

    val nestedFruits = getNestedFruits()

    // flatMap
    val result1 = nestedFruits.flatMap { list ->
        list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    }
    println(result1)
    /**
     * 각 내부 리스트마다 filter 실행
     * 작은 중간 리스트 생성
     * 그 결과들을 flatMap으로 합침
     */


    // flatMap, 위와 결과가 동일함.
    //      위 방법은 내부 리스트들에 대해 각각 filter 처리하고 새로운 리스트들이 만들어진다. 그 이후 flatMap 을 하기 때문에.. (중간 컬렉션이 생성됨)
    //      아래 방법은 내부 리스트들은 이미 존재하고 바로 flatMap 을 한다. 그리고 filter 처리
    val result2 = nestedFruits.flatMap { it }
        .filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    println(result2)
    /**
     * 먼저 전부 펼쳐서
     * 하나의 큰 List<Fruit> 생성
     * 그 다음 전체를 대상으로 filter
     */

}

private fun getNestedFruits(): List<List<Fruit>> = listOf(
    listOf(
        Fruit(1L, "apple", 10_000L, 12_000L),
        Fruit(2L, "apple", 9_500L, 11_500L),
        Fruit(3L, "apple", 11_000L, 13_000L),
    ),
    listOf(
        Fruit(4L, "banana", 5_000L, 6_000L),
        Fruit(5L, "banana", 4_800L, 4_800L),
        Fruit(6L, "banana", 5_200L, 6_200L),
    ),
    listOf(
        Fruit(7L, "orange", 7_000L, 8_500L),
        Fruit(8L, "orange", 6_800L, 8_200L),
    ),
    listOf(
        Fruit(9L, "grape", 15_000L, 18_000L),
        Fruit(10L, "grape", 14_500L, 17_500L),
    ),
    listOf(
        Fruit(11L, "watermelon", 20_000L, 20_000L),
        Fruit(12L, "watermelon", 19_000L, 24_000L),
    ),
    listOf(
        Fruit(13L, "peach", 8_000L, 9_500L),
        Fruit(14L, "peach", 8_500L, 10_000L),
    )
)