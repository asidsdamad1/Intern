package service.impl;

import dto.EmployeeDTO;
import model.Employee;
import service.EmployeeService;
import service.MenuService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {
    EmployeeService service = new EmployeeServiceImpl();

    public void menu() {
        System.out.println("===================================");
        System.out.println("1. Thêm nhân viên ");
        System.out.println("2. Hiển thị thông tin nhân viên ");
        System.out.println("3. Tìm kiếm nhân viên ");
        System.out.println("4. Thoát");
        System.out.println("===================================");

        System.out.println("Nhập lựa chọn:  ");
    }

    @Override
    public void saveEmployee(Scanner sc) throws IOException {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        System.out.println("Nhâp thông tin nhân viên.");

        System.out.print("Nhập số an sinh: ");
        employeeDTO.setSsn(sc.next());
        System.out.print("Nhập tên: ");
        employeeDTO.setFirstName(sc.next());
        System.out.print("Nhập họ: ");
        employeeDTO.setLastName(sc.next());
        System.out.print("Nhập ngày sinh: ");
        employeeDTO.setBirthDate(sc.next());
        System.out.print("Nhập ngày số điện thoại: ");
        employeeDTO.setPhone(sc.next());
        System.out.print("Nhập ngày email: ");
        employeeDTO.setEmail(sc.next());

        if(((EmployeeServiceImpl) service).validate(employeeDTO))
            service.save(employeeDTO).display();

        menu();

    }

    @Override
    public void getAllEmployee() throws IOException {
        List<Employee> employees = service.getAll();
        if (employees == null) System.out.println("Không có nhân viên");
        else
            for(Employee employee: employees)
                employee.display();

        menu();
    }

    @Override
    public void searchEmployeeByName(Scanner sc) throws IOException {
        System.out.print("Nhập tên nhân viên: ");
        String name = sc.next();

        List<Employee> employeesByName = service.searchByName(name);
        if(employeesByName == null) System.out.println("Không có nhân viên tên: " + name);
        else
            for(Employee employee: employeesByName)
                employee.display();

        menu();
    }
}
