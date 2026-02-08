package sub18_collection.map

fun main() {

    val immutableMap = mapOf( // 불변
        1 to "one",
        2 to "two"
    )

    val mutableMap = mutableMapOf<Int, String>() // 가변, Java HashMap
    mutableMap.put(1, "one")
    mutableMap[2] = "two"


    // key
    for (key in immutableMap.keys) {
        println("key : $key, value : ${immutableMap[key]}")
    }

    // entry
    for ((key, value) in immutableMap) {
        println("key : $key, value : $value")
    }

}