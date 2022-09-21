package model;

public abstract class Thing {
    private String serialNumbber;
    private String name;

    public Thing() {
    }

    public Thing(String serialNumbber, String name) {
        this.serialNumbber = serialNumbber;
        this.name = name;
    }

    abstract String getDescription();

    public String getSerialNumbber() {
        return serialNumbber;
    }

    public void setSerialNumbber(String serialNumbber) {
        this.serialNumbber = serialNumbber;
    }

}
