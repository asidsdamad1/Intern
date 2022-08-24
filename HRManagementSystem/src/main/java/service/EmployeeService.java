package service;

import dto.EmployeeDTO;
import model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO dto) throws IOException;

    List<Employee> getAll() throws IOException;

    List<Employee> searchByName(String keyword) throws IOException;
}
