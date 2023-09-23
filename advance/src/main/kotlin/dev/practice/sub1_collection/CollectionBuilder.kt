package dev.practice.sub1_collection

fun main() {

    //Collection builder 를 사용하여 리스트를 만들 수 있다.
    //buildList 내부에서는 Mutable 로 동작하였지만...
    // 반환된 리스트는 immutable 이다.
    val buildList: List<Int> = buildList<Int> {
        add(1)
        add(2)
        add(3)
    }


}