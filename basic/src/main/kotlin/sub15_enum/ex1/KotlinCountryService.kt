package sub15_enum.ex1

class KotlinCountryService {

    fun handle(country: KotlinCountry): Int =
        when (country) {
            KotlinCountry.KOREA -> doSomething1()
            KotlinCountry.JAPAN -> doSomething2()
        }


    private fun doSomething1() = 0
    private fun doSomething2() = 1

}