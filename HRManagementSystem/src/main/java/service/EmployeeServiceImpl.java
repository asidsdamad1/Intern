package service;

import dto.EmployeeDTO;
import model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class EmployeeServiceImpl {

    public boolean validate(EmployeeDTO dto) {
        String phoneRegex = "\\d{10}";
        String birthDateRegex = "^(((0[1-9]|[12][0-9]|30)[/]?(0[13-9]|1[012])|31[/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[/]?02)[/]?[0-9]{4}|29[/]?02[/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$";
        String emailRegex = "^[a-zA-Z0-9.! #$%&'*+/=? ^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\. [a-zA-Z0-9-]+)*$";

        if(!dto.getBirthDate().matches(birthDateRegex)) {
            System.out.println("Ngày sinh không đúng định dạng dd/MM/yyyy");
            return false;
        }
        if(!dto.getPhone().matches(phoneRegex)) {
            System.out.println("Số điên thoại phải có 10 số");
            return false;
        }
        if(!dto.getEmail().matches(emailRegex)) {
            System.out.println("Email không đúng định dạng");
            return false;
        }
        return true;
    }

    public Employee save(EmployeeDTO dto) {
        if(!validate(dto))  return null;

        FileWriter fw = null;
        try {
            String csvFile = "data.csv";

            fw = new FileWriter(csvFile, true);
            BufferedWriter bw = new BufferedWriter (fw);

            bw.write(dto.getSsn()+",");
            bw.write(dto.getFirstName()+",");
            bw.write(dto.getLastName()+",");
            bw.write(dto.getBirthDate()+",");
            bw.write(dto.getPhone()+",");
            bw.write(dto.getEmail()+"\n");

            bw.close();
            fw.close();

            System.out.println("Thêm thành công");
        }  catch (IOException exception)  {
            exception.printStackTrace();
        }

        Employee entity  = new Employee() {

            public void setSsn(String ssn) {
                super.setSsn(ssn);
            }
        };

        return entity;
    }


    public List<Employee> getAll() {
        return new ArrayList<>();
    }


    public Employee search() {
        Employee entity  = new Employee() {

            public void setSsn(String ssn) {
                super.setSsn(ssn);
            }
        };

        return entity;
    }
}
