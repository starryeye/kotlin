package sub3_flow_control

/**
 * 코틀린에서는 if - else 가 조건"문" 이 아니라
 * 조건"식" 이다.
 */

fun main() {

    // 다른 언어와 동일하게 사용 가능하다.
    val job = "software developer"

    if(job == "software developer") {
        println("개발자")
    }else {
        println("개발자 아님")
    }

    //----------------------------------------------

    // 표현식으로서의 if - else
    // 반환 값이 존재할 수 있다.
    val age: Int = 10

    val str = if(age > 19) {
        "성인"
    }else {
        "아이"
    }
    println(str)

    //----------------------------------------------

    // if - else 는 표현식이므로 코틀린은 삼항 연산자가 필요없다.
    val a = 1
    val b = 2
    val c = if (b > a) b else a
    println(c)
}