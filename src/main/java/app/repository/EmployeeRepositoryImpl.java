package app.repository;

import app.domain.Employee;
import app.service.outputport.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee saveEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        return Collections.unmodifiableList(new ArrayList<>(employees));
    }

    @Override
    public Employee selectEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                return employee;
            }
        }
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }
}

