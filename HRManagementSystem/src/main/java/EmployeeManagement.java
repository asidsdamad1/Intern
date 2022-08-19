import dto.EmployeeDTO;
import model.Employee;
import service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    private void menu() {


    }

    public Employee saveEmployee() {
        validate();
        Employee entity = new Employee() {
            @Override
            public void setSsn(String ssn) {
                super.setSsn(ssn);
            }
        };

        return entity;

    }

    public List<Employee> getAllEmployee() {

        return new ArrayList<>();
    }

    public Employee searchEmployee() {
        Employee entity = new Employee() {
            @Override
            public void setSsn(String ssn) {
                super.setSsn(ssn);
            }
        };

        return entity;
    }

    public boolean validate() {

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1.Thêm nhân viên ");
        System.out.println("2.Hiển thị thông tin nhân viên ");
        System.out.println("3.Tìm kiếm nhân viên ");
        System.out.println("Nhập lựa chọn:  ");

        EmployeeServiceImpl service = new EmployeeServiceImpl();


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


                service.save(employeeDTO);
                break;
            case 2:
                service.getAll();
                break;
            case 3:
                service.search();
                break;
            default:
                break;
        }
        ;

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
