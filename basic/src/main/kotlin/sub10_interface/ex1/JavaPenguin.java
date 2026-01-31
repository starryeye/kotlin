package sub10_interface.ex1;

public class JavaPenguin extends JavaAnimal implements JavaSwimmable, JavaFlyable{

    private final int wingCount;

    public JavaPenguin(String species) {
        super(species, 2);
        this.wingCount = 2;
    }

    @Override
    public void move() {
        System.out.println("Java Penguin");
    }

    @Override
    public int getLegCount() {
        return super.legCount + wingCount;
    }

    @Override
    public void fly() {
        System.out.println("Java Penguin Flying");
    }

    @Override
    public void act() { // JavaSwimmable, JavaFlyable 두 인터페이스 모두 act 라는 default 메서드를 가지고 있어서 새로 구현해줘야함.
        JavaSwimmable.super.act();
        JavaFlyable.super.act();
    }
}
