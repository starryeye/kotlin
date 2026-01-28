package sub7_exception.ex1;

import org.jetbrains.annotations.NotNull;

public class JavaException {

    public static void main(String[] args) {

    }

    private int parseIntOrThrow(@NotNull String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("string s is invalid, s = " + s);
        }
    }

    private Integer parseIntOrNull(@NotNull String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
