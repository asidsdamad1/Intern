package di.repository.impl;

import di.repository.AbstractRepo;

public class MySQLRepo implements AbstractRepo {

    @Override
    public void insert() {
        System.out.println("MySQL insert");
    }

    @Override
    public void update() {
        System.out.println("MySQL update");

    }

    @Override
    public void delete() {
        System.out.println("MySQL delete");
    }
}
