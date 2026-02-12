package sub24_collection_chain.sub1_filter

import sub24_collection_chain.Fruit

fun main() {

    val fruits = getFruits()

    // filter
    val result1 = fruits.filter { fruit -> fruit.name == "apple" }

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