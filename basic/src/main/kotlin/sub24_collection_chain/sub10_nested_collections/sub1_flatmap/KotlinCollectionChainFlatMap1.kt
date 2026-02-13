package sub24_collection_chain.sub10_nested_collections.sub1_flatmap

import sub24_collection_chain.Fruit

fun main() {

    val nestedFruits = getNestedFruits()

    // flatMap
    val result1 = nestedFruits.flatMap { list: List<Fruit> -> list } // 입력을 그대로 반환..
    println(result1)

    val result2 = nestedFruits.flatMap { it } // 위와 동일 의미
    println(result2)

    val result3 = nestedFruits.flatten() // 위와 동일 의미
    println(result3)
    /**
     * Java 와 비교..
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
     *      입력: T, 반환: Stream<R>
     *      실행 의미: 각 요소 T(내부 List)를 Stream 으로 바꿔주는 Function 을 파라미터로 주면.. 그러면 내가 그 Stream 들의 내부 원소들을 단일 Stream 으로 합쳐주겠다
     *      .flatMap(list -> list.stream())
     *      .toList()
     *      특징 : lazy
     *
     * Kotlin 에서는..
     * inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R>
     *      입력: T, 반환: List<R>
     *      실행 의미: 각 요소 T(내부 List)를 Iterable 로 바꾸는 람다를 파라미터로 주면.. 그러면 내가 그 Iterable 들의 내부 원소들을 단일 List 로 합쳐주겠다.
     *          여기서.. list: List<Fruit> -> list 의 의미를 보면.. 내부 List 자체가 이미 Iterable 임..
     *          그래서.. 단일 List 로 만들어줌..
     *      특징 : eager
     *
     * fun <T> Iterable<Iterable<T>>.flatten(): List<T>
     *      입력: 없음, 반환: List<T>
     *      실행 의미: flatMap { it } 과 결과는 동일하지만.. flatMap 에서는 사실 T 타입의 중첩 Iterable 에서 R 타입의 List 로 나오는 것이고..
     *                  flatten 에서는 T 타입의 중첩 Iterable 에서 T 타입의 List 로 나오는 것으로..
     *                  flatMap 내부에서 뭔가 T -> R 로 변환 할 수 있는 여지가 있는 것이다.
     *
     * 참고
     * Java 에서는 원소 하나하나가 흘러가는 느낌인데 (단일 원소가 여러 Stream API 를 쭉쭉 흘러감)
     * Kotlin 은 원소가 흘러가는게 아니라서 상상할때.. 그냥 타입을 보고 전체 데이터가 변한다라고 생각해야한다..
     *      물론 단일 API 내부 실행에서는 모든 원소를 순회해서 완성된 Collection 으로 만들고 다음 Chain API 가 실행될 것이다.
     * 즉, 원소가 흐르는 생각을 버리고 그냥 타입 중심으로 생각해야한다..
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
        Fruit(5L, "banana", 4_800L, 5_800L),
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
        Fruit(11L, "watermelon", 20_000L, 25_000L),
        Fruit(12L, "watermelon", 19_000L, 24_000L),
    ),
    listOf(
        Fruit(13L, "peach", 8_000L, 9_500L),
        Fruit(14L, "peach", 8_500L, 10_000L),
    )
)