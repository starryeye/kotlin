package sub15_enum.ex1;

public enum JavaCountry {

    KOREA("KO"),
    JAPAN("JP"),
    ;

    private final String code;

    JavaCountry(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
