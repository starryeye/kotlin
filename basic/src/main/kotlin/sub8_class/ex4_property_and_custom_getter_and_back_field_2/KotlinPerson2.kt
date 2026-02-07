package sub8_class.ex4_property_and_custom_getter_and_back_field_2

class KotlinPerson2(val name: String, var age: Int) {

    /**
     * JavaPerson, KotlinPerson1 에서는 각각 getName(), custom getter + backing field 를 사용해서
     * uppercase name 을 제공해주었는데
     * 사실.. 아래와 같이 원본은 놔두고 새로운 함수를 제공하는게 좋다.
     */
    fun getUppercaseName(): String = this.name.uppercase() // this.name 은 내부적으로 this.getName() 으로 호출된다.
}