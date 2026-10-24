package app.service.outputport;

import app.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee saveEmployee(Employee employee);

    List<Employee> selectAllEmployees();

    Employee selectEmployeeById(int id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(int id);
}

