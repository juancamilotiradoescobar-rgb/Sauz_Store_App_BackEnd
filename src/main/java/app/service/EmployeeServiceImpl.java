package app.service;

import app.domain.Employee;
import app.service.inputport.EmployeeService;
import app.service.outputport.EmployeeRepository;

import java.util.Date;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(int id, String docType, String name, String lastName, String phone,
                                   String email, String address, String password, Date birthDate,
                                   String position, double salary, String department) {
        validateEmployeeData(id, name, lastName, position, salary);
        if (employeeRepository.selectEmployeeById(id) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con el ID " + id + ".");
        }
        Employee employee = new Employee(id, docType, name, lastName, phone, email, address, password,
                birthDate, position, salary, department);
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee selectEmployeeById(int id) {
        return employeeRepository.selectEmployeeById(id);
    }

    @Override
    public List<Employee> selectAllEmployees() {
        return employeeRepository.selectAllEmployees();
    }

    @Override
    public Employee updateEmployee(int id, String docType, String name, String lastName, String phone,
                                   String email, String address, String password, Date birthDate,
                                   String position, double salary, String department) {
        validateEmployeeData(id, name, lastName, position, salary);
        if (employeeRepository.selectEmployeeById(id) == null) {
            throw new IllegalArgumentException("No existe un empleado con el ID " + id + ".");
        }
        Employee employee = new Employee(id, docType, name, lastName, phone, email, address, password,
                birthDate, position, salary, department);
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }

    @Override
    public double calculateBonus(int id, double bonusPercentage) {
        Employee employee = employeeRepository.selectEmployeeById(id);
        if (employee == null) {
            throw new IllegalArgumentException("No existe un empleado con el ID " + id + ".");
        }
        return employee.calculateBonus(bonusPercentage);
    }

    private void validateEmployeeData(int id, String name, String lastName, String position, double salary) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del empleado debe ser mayor que cero.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío.");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("El apellido del empleado no puede estar vacío.");
        }
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("El cargo del empleado no puede estar vacío.");
        }
        if (!Double.isFinite(salary) || salary < 0) {
            throw new IllegalArgumentException("El salario debe ser un número válido y no negativo.");
        }
    }
}

