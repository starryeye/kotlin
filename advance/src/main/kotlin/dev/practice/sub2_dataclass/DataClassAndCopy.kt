package dev.practice.sub2_dataclass

data class User(var name: String, val age: Int)
data class User2(val name: String, val age: Int)

fun main() {

    /**
     * 현재 User data class 의 name 프로퍼티는 var 이다..
     * 따라서.. name 프로퍼티는 set 기능이 생긴다.
     * 그러면 불변성이 깨진 것이고 아래와 같은 상황이 발생한다.
     *
     * 그래서.. data class 를 사용하더라도 가변객체가 된 것이고.. thread-safe(불변객체의 특징) 하지 않게 된 것이다.
     * + set 기능이 생겨버려서 이슈 발생시 추적이 매우 힘들다..
     */
    val user1 = User(name = "user1", age = 10)
    val set = hashSetOf(user1) // 해시 Set 에 넣기
    println(set.contains(user1))

    user1.name = "unknown" // unknown 으로 변경!! -> data 클래스 내부에서 hashcode 를 도출하는 코드로 hash 값이 변경되어버림.
    println(set.contains(user1)) // 프로퍼티 값이 변경되어서 hashcode 값이 변경되어서 다른 객체로 인식되어 false 이다.
    /**
     * 참고,
     * Java Object 에서는..
     * equals(동등성) 은 기본적으로 동일성 검사를한다. ("==" 연산자는 equals 메서드와 같다.)
     * hashcode 는 동일성 검사를 위한 메서드로 객체의 메모리 주소를 리턴한다.
     *
     * 특정 식별자를 바탕으로 동등성, 동일성을 보장해야할 필요성이 있다면.. (ex. JPA Entity)
     * 동등성 보장을 위해 특정 식별자 비교로 equals 를 재정의..
     * 동일성 보장을 위해 특정 식별자 비교로 hashcode 를 재정의..
     * 이렇게 해버리면..
     * Java 는 reference 기반으로 복사가 되니까.. 컬렉션에 넣은 것도 동일할거야.. 라고 생각해버리고..
     * 변경해도 문제 없겠지라고 생각할 수 있는데..
     * 실제로는..
     * hash 계열의 collection 등을 사용할때.. 위와 같이 다른 객체로 인식하는.. 불이익이 생긴다.
     * (내부적으로 hashcode 를 통해 저장위치를 정하고 fix 해버림.. 그래서 변경해도 찾을수 없음)
     *
     * 의문사항.. hash 계열의 collection 은 왜 .. 메모리 주소로 동일성 비교를 하지 않지..?
     * -> JVM 특성이다. 추상 언어임. native 가 아니기 때문
     *
     */


    /**
     * 그래서 기존에 불변 객체가 있는 상황에서 수정하고 싶을땐 어떻게 하는것이 좋은가..?
     * -> 그냥 copy 해서 사용하는 편이다. (객체 새로만들기)
     * -> 이를 도와주는것이 data class 의 copy 함수이다.
     */
    val user2 = User2(name = "user2", age = 10)
    val set2 = hashSetOf(user2)
    println(set2.contains(user2))

    val copy = user2.copy(name = "unknown") // 새로운 불변객체 생성
    println(set2.contains(user2)) // user2 는 불변객체로 값을 변경할수없고 하지도 않았으며, 변경하기 위한 새로운 객체를 만들었기 때문에 당연히 true
}
