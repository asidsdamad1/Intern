import dto.EmployeeDTO;
import model.Employee;
import service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {

    private void menu()  {
        System.out.println("===================================");
        System.out.println("1. Thêm nhân viên ");
        System.out.println("2. Hiển thị thông tin nhân viên ");
        System.out.println("3. Tìm kiếm nhân viên ");
        System.out.println("4. Thoát");
        System.out.println("===================================");

        System.out.println("Nhập lựa chọn:  ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeServiceImpl service = new EmployeeServiceImpl();
        EmployeeManagement management = new EmployeeManagement();

        management.menu();
        boolean checkExit = false;
        do {
            switch (sc.nextInt()) {
                case 1:
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

                    System.out.println(service.save(employeeDTO));
                    management.menu();
                    break;
                case 2:
                    System.out.println(service.getAll());
                    management.menu();
                    break;
                case 3:
                    System.out.print("Nhập tên nhân viên: ");
                    String keyword = sc.next();
                    System.out.println(service.searchByName(keyword));
                    management.menu();
                    break;
                default:
                    checkExit = true;
                    break;
            }
        } while (!checkExit);



        /* menu 4 chuc nang:
            1. them nhan vien (luu vao file)
                - validate:
                    + birthDate theo format: dd/MM/yyyy.
                    + phone 10 so.
                    + email dung  dinh dang.
//
            2. hien thi thong tin nhan vien. (doc tu file csv)
            3. tim kiem (theo).
            4. thoat.
        * */
    }
}
