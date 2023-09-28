package sub1_gettersetter

import java.time.LocalDate

class Student {

    @JvmField // Java 에서 해당 프로퍼티를 접근할 때, 코틀린 스타일로 접근할 수 있다.
    var name: String? = null

    var birthDate: LocalDate? = null

    val age: Int = 10

    var grade: String? = null
        private set // 가변변수라 setter 가 생성되는데.. setter 를 private 로 막았다.


    fun changeGrade(grade:String) {
        this.grade = grade // grade 변수의 변경을 명시적인 함수로 생성
    }
}