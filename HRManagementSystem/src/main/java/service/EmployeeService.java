package service;

import dto.EmployeeDTO;
import model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    Employee save(EmployeeDTO employeeDTO) throws IOException;
    List<Employee> getAll();
    Employee search();
}
