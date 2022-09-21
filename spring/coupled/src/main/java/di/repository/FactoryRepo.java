package di.repository;

import di.repository.impl.MSSQLRepo;
import di.repository.impl.MySQLRepo;
import di.repository.impl.PostgreSQLRepo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface FactoryRepo {
    public static AbstractRepo getRepository() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/config.properties");
            // load properties file
            prop.load(input);

            String database = prop.getProperty("database");

            if(database.equals("1")) {
                return new MSSQLRepo();
            }
            if(database.equals("2")) {
                return new MySQLRepo();
            }
            if(database.equals("3")) {
                return new PostgreSQLRepo();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
