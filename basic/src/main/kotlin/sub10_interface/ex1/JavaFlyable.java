package sub10_interface.ex1;

public interface JavaFlyable {

    void fly();

    default void act() {
        System.out.println("Java Flyable");
    }
}
