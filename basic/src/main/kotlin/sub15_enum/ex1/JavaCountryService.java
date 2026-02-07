package sub15_enum.ex1;

public class JavaCountryService {

    public int handle(JavaCountry javaCountry) {

        return switch (javaCountry) {
            case KOREA -> doSomething1();
            case JAPAN -> doSomething2();
        };
    }

    private int doSomething1() {
        return 0;
    }

    private int doSomething2() {
        return 1;
    }
}
