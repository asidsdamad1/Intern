package di.repository.impl;

import di.repository.AbstractRepo;

public  class PostgreSQLRepo implements AbstractRepo {

    @Override
    public void insert() {
        System.out.println("PostgreSQL insert");
    }

    @Override
    public void update() {
        System.out.println("PostgreSQL update");

    }

    @Override
    public void delete() {
        System.out.println("PostgreSQL delete");
    }
}
