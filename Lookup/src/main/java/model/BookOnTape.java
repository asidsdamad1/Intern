package model;

public class BookOnTape extends Thing {

    public BookOnTape() {
    }

    public BookOnTape(String serialNumbber, String name) {
        super(serialNumbber, name);
    }

    @Override
    String getDescription() {
        return null;
    }
}
