package model;

public class Furniture extends Thing{

    public Furniture() {
    }

    public Furniture(String serialNumbber, String name) {
        super(serialNumbber, name);
    }

    @Override
    String getDescription() {
        return null;
    }
}
