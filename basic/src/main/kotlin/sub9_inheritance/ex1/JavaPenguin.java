package sub9_inheritance.ex1;

public class JavaPenguin extends JavaAnimal {

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
}
