package sub19_extension_function

fun main() {

    /**
     * Kotlin 은 Java 와 완벽히 호환되면서.. Java 에 추가 기능을 넣고 싶어서..
     * Java class 내에 있는 맴버 메서드가 아니지만, 외부에서 마치 맴버 메서드가 있는 것처럼 메서드를 추가할 수 있도록함.
     * -> Kotlin 확장함수
     *
     * 특징
     *      1. 확장함수에서 확장 대상 클래스의 private 함수를 쓰는 것은 캡슐화가 깨진 것이라 볼 수 있음..
     *          확장함수에서는 private, protected 맴버(변수, 메서드)를 사용 불가
     *      2. 확장함수 시그니처가 확장 대상 클래스에 존재하는 함수의 시그니처와 동일 하다면..
     *          확장 대상 클래스에 존재하는 함수가 호출됨.
     *      3. A class 를 상속하는 B class 가 있다고 하고.. A class, B class 에 동일한 시그니처로 확장함수를 선언했다면..
     *          현재 타입으로 확장함수가 호출됨.
     *              다형성을 위배되는 것처럼 보일 수 있음
     *                  타입은 A 로 선언하고 구현체는 B 인 상태에서 해당 인스턴스로 함수를 호출하면 A 의 확장함수가 호출됨..
     *
     * 참고
     *      확장함수말고 확장프로퍼티도 가능하다.
     *          custom getter/setter 도 가능하다.
     */

    val str = "hello world"
    println(str.lastChar())
}

// 아래는 String.kt 에 함수를 추가한 것임.
// {확장함수를 넣고 싶은 class 명}.{확장 함수 명} 으로 선언가능
fun String.lastChar(): Char {
    // this 를 통해 불려진 인스턴스 접근 가능.
    return this[this.length - 1] // 문자열의 마지막 문자를 리턴
}


// 특징 1 예시
fun User.printEmail() {
//    println(email)      // 컴파일 에러
//    println(secret())   // 컴파일 에러
}
class User(
    private val email: String
) {
    private fun secret() = "secret:$email"
}


// 특징 2 예시
fun JavaUser.nextYearAge(): Int { // 확장대상 class 인 JavaUser 에 nextYearAge 가 존재하므로 이 확장함수는 호출 되지 않음
    println("Kotlin 확장함수")
    return this.age + 1 // 특징 1 에서 생각하면 age 가 private 이라 접근불가능해야하는것 아닌가.. 라고 생각할 수 있는데 JavaUser::getAge() 로 접근한것임.
}