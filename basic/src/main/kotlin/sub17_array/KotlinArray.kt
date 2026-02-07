package sub17_array

fun main() {

    val array = arrayOf(100, 200)

    for (i in array.indices) { // indices : array 가 가진? index 를 range 로 담는다.
        println("$i index = ${array[i]}")
    }

    for ((index, value) in array.withIndex()) { // index, value 를 pair 처럼 접근할 수 있게 해줌
        println("$index index = $value")
    }


    // array 에 값을 추가할 수 도 있음....
    // 내부적으로 copy 해서 새로운 배열로 만들고 리턴해줌
    val array2 = array.plus(300)

    println("array === array2 -> ${array === array2}") // 동일성 비교

}