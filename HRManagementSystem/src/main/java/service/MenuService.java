package service;

import java.io.IOException;
import java.util.Scanner;

public interface MenuService {
    void saveEmployee(Scanner sc) throws IOException;
    void getAllEmployee() throws IOException;
    void searchEmployeeByName(Scanner sc) throws IOException;
}
