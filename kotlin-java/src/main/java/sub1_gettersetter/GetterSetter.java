package sub1_gettersetter;

import java.time.LocalDate;

public class GetterSetter {

    /**
     * 자바에서 코틀린 클래스를 사용해본다.
     *
     * 어려운 점이 없다.
     *
     * @JvmField 만 좀 특이하다.
     */

    public static void main(String[] args) {

        Student student = new Student();

        student.name = "AAA"; // @JvmField 가 적용되어있는 프로퍼티라 코틀린 스타일로 접근 가능
        System.out.println("name : " + student.name); // @JvmField 가 적용되어있는 프로퍼티라 코틀린 스타일로 접근 가능

        student.setBirthDate(LocalDate.of(2023, 9, 28)); // 가변 변수는 setter, getter 로 접근이 가능하다.
        System.out.println("birthDate : " + student.getBirthDate()); // 가변 변수는 setter, getter 로 접근이 가능하다.

//        student.setAge(20); // age 는 불변 변수이므로 setter 가 없다.
        System.out.println("age : " + student.getAge());

//        student.setGrade("BBB"); // 가변 변수이지만, setter 를 private 으로 막아놔서 접근 불가능
        student.changeGrade("CCC");
        System.out.println(student.getGrade());

    }
}
