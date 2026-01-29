package sub2_function.ex1

// 아래 순서대로 점차 개선 가능

// 1.
fun max1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b
}

// 2.
fun max2(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

// 3.
fun max3(a: Int, b: Int): Int =
    if (a > b) {
        a
    } else {
        b
    }

// 4.
fun max4(a: Int, b: Int) =
    if (a > b) {
        a
    } else {
        b
    }


// 5.
fun max(a: Int, b: Int) = if (a > b) a else b