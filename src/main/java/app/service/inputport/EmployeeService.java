package app.service.inputport;

import app.domain.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(int id, String docType, String name, String lastName, String phone,
                            String email, String address, String password, Date birthDate,
                            String position, double salary, String department);

    Employee selectEmployeeById(int id);

    List<Employee> selectAllEmployees();

    Employee updateEmployee(int id, String docType, String name, String lastName, String phone,
                            String email, String address, String password, Date birthDate,
                            String position, double salary, String department);

    void deleteEmployee(int id);

    double calculateBonus(int id, double bonusPercentage);
}

