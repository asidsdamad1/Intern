package model;

public class Video extends Thing {

    public Video() {
    }

    public Video(String serialNumbber, String name) {
        super(serialNumbber, name);
    }

    @Override
    String getDescription() {
        return null;
    }
}
