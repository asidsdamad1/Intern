import service.EmployeeService;
import service.MenuService;
import service.impl.EmployeeServiceImpl;
import service.impl.MenuServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeManagementSystem {


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuServiceImpl();

        ((MenuServiceImpl) menuService).menu();
        boolean checkExit = false;
        do {
            switch (sc.nextInt()) {
                case 1 -> menuService.saveEmployee(sc);
                case 2 -> menuService.getAllEmployee();
                case 3 -> menuService.searchEmployeeByName(sc);
                default -> checkExit = true;
            }
        } while (!checkExit);

    }
}
