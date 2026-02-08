package sub18_collection.list

fun main() {


    val immutableList = listOf(1, 2, 3) // 불변 리스트임
    val mutableList = mutableListOf(1, 2, 3) // 가변 리스트임, 내부 구현은 Java ArrayList


    // add
    mutableList.add(4)


    // index 로 access
    println(immutableList.get(0))
    println(immutableList[0])


    // enhanced for loop
    for (e in immutableList) {
        println(e)
    }
    for ((index, value) in immutableList.withIndex()) {
        println("index = $index, value = $value")
    }


    // forEach
    immutableList.forEach(::println)
}