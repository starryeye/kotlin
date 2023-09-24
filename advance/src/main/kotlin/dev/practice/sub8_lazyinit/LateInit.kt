package dev.practice.sub8_lazyinit

/**
 * LazyInit.kt 에서 이어진다.
 *
 * 외부 프레임워크나 라이브러리에 동작에 의해 실제로 가변 프로퍼티에 의한 지연초기화가 필요한 경우도 있다.
 * 대표적으로..
 * 1. 스프링에서 필드주입방식...
 * 2. 스프링 Test 코드에서 @Setup 어노테이션에 의한 지연 초기화
 * 3. 스프링 Test 코드에서 테스트 대상 필드주입..
 *
 * 이를 위해 코틀린에서는 lateinit 키워드를 제공한다.
 * 가변 변수 키워드(var) 와 함께 사용된다. (val 와는 사용 불가능)
 *
 */

class LateInit {
    //lateinit 키워드를 사용하여..
    //non null type 이며 초기화가 이루어지지 않았는데도 컴파일 에러가 발생하지 않음
    lateinit var text: String

    // 초기화가 되지 않은 것을 확인하고 초기화를 시켜주는 코드 없으면..
    // null 참조가 이루어지는데 이 경우 초기화 관련 예외가 발생한다. (런타임)
    // 즉, 코틀린 컴파일러가.. 컴파일 타임에 초기화 되지 않겠다는 것을 예상확인 하지 않는다.
    // -> 외부 프레임워크나 라이브러리에 의해 지연 초기화를 타겟으로 만들어졌기 때문
    // 따라서 아래와 같이 초기화 확인 코드를 작성하는게 안전할 수 있다...
    fun printText() {
        if(this::text.isInitialized) {
            println("이미 초기화 됨")
        }else {
            println("초기화 되지 않아서 초기화 진행")
            this.text = "string"
        }
        println(this.text)
    }

    // 클래스 외부에서는 isInitialized 를 직접 참조 할 수 없다.
    // 따라서.. 필요할 경우 아래와 같이 getTextInitialized 와 같은 함수를 제작
    fun getTextInitialized(): Boolean {
        return this::text.isInitialized
    }
}

fun main() {

    val lateInit = LateInit()

    lateInit.printText()

    lateInit.printText()
}