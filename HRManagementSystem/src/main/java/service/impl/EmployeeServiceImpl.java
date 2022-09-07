package service.impl;

import dto.EmployeeDTO;
import model.Employee;
import service.EmployeeService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {
    private final String csvFile = "data.csv";

    public boolean validate(EmployeeDTO dto) {
        String phoneRegex = "\\d{10}";
        String birthDateRegex = "^(((0[1-9]|[12][0-9]|30)[/]?(0[13-9]|1[012])|31[/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[/]?02)[/]?[0-9]{4}|29[/]?02[/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$";
        String emailRegex = "^(([^<>()\\\\.,;:\\s@\"]+(\\.[^<>()\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        if (!dto.getBirthDate().matches(birthDateRegex)) {
            System.out.println("Ngày sinh không đúng định dạng dd/MM/yyyy");
            return false;
        }
        if (!dto.getPhone().matches(phoneRegex)) {
            System.out.println("Số điên thoại phải có 10 số");
            return false;
        }
        if (!dto.getEmail().matches(emailRegex)) {
            System.out.println("Email không đúng định dạng");
            return false;
        }
        return true;
    }

    @Override
    public Employee save(EmployeeDTO dto) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(csvFile, true);
            bw = new BufferedWriter(fw);

            bw.write(dto.getSsn() + ",");
            bw.write(dto.getFirstName() + ",");
            bw.write(dto.getLastName() + ",");
            bw.write(dto.getBirthDate() + ",");
            bw.write(dto.getPhone() + ",");
            bw.write(dto.getEmail());
            bw.write("\n");

            bw.close();
            System.out.println("Thêm thành công");
        } finally {
            if (fw != null)
                fw.close();
        }

        Employee entity = new Employee() {
        };

        entity.setSsn(dto.getSsn());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        return entity;
    }

    @Override
    public List<Employee> getAll() throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        BufferedReader lineReader = null;
        try {
            lineReader = new BufferedReader(new FileReader(csvFile));
            String lineText = null;
            Employee entity = null;

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                entity = new Employee() {};

                entity.setSsn(data[0]);
                entity.setFirstName(data[1]);
                entity.setLastName(data[2]);
                entity.setBirthDate(data[3]);
                entity.setPhone(data[4]);
                entity.setEmail(data[5]);

                employeeList.add(entity);
            }

            if (employeeList.size() == 0)
                return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (lineReader != null)
                lineReader.close();
        }

        return employeeList;
    }

    @Override
    public List<Employee> searchByName(String keyword) throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        BufferedReader lineReader = null;
        try {
            lineReader = new BufferedReader(new FileReader(csvFile));
            String lineText = null;
            Employee entity = new Employee() {
            };

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                if (!data[1].equals(keyword))
                    continue;

                entity.setSsn(data[0]);
                entity.setFirstName(data[1]);
                entity.setLastName(data[2]);
                entity.setBirthDate(data[3]);
                entity.setPhone(data[4]);
                entity.setEmail(data[5]);

                employeeList.add(entity);
            }

            if(employeeList.size() <= 0) return null;
        }  catch (IOException e)  {
            e.printStackTrace();
        } finally {
            if (lineReader != null)
                lineReader.close();
        }
        return employeeList;
    }

}
