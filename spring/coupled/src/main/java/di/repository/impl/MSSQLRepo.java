package di.repository.impl;

import di.repository.AbstractRepo;

public class MSSQLRepo implements AbstractRepo {

    @Override
    public void insert() {
        System.out.println("MSSQL insert");
    }

    @Override
    public void update() {
        System.out.println("MSSQL update");

    }

    @Override
    public void delete() {
        System.out.println("MSSQL delete");
    }
}
