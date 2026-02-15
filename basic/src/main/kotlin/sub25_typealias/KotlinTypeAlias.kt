package sub25_typealias

typealias FruitFilterCondition = (Fruit) -> Boolean
typealias FNameMap = Map<String, List<Fruit>>

fun main() {

    /**
     * typealias 라는 키워드로 타입에 별칭을 지어줄 수 있다..
     *
     * typealias FruitFilterCondition = (Fruit) -> Boolean
     * -> (Fruit) -> Boolean 람다 타입을 FruitFilterCondition 이라는 별칭 타입으로 선언..
     *
     * typealias FNameMap = Map<String, List<Fruit>>
     * -> Map<String, List<Fruit>> 타입을 FNameMap 이라는 별칭 타입으로 선언..
     */
}

// 별칭 타입을 사용할 수 있다.
fun filterFruit(fruits: List<Fruit>, condition: FruitFilterCondition): List<Fruit> {
    return fruits.filter(condition)
}

