package sub9_inheritance.ex1;

public class JavaCat extends JavaAnimal{


    public JavaCat(String species) {
        super(species, 4);
    }

    @Override
    public void move() {
        System.out.println("Java Cat");
    }
}
