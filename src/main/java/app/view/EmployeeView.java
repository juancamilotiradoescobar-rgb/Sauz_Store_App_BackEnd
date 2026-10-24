package app.view;

import app.domain.Employee;
import app.service.helpers.SetEmployeeDepartment;
import app.service.inputport.EmployeeService;
import app.service.validations.FormTypeValidator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

public class EmployeeView {
    private final EmployeeService employeeService;

    public EmployeeView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void createEmployee() {
        int id = FormTypeValidator.intValidator("Ingrese el ID del empleado");
        String docType = FormTypeValidator.stringValidator("Ingrese el tipo de documento del empleado");
        String name = FormTypeValidator.stringValidator("Ingrese el nombre del empleado");
        String lastName = FormTypeValidator.stringValidator("Ingrese el apellido del empleado");
        String phone = FormTypeValidator.stringValidator("Ingrese el teléfono del empleado");
        String email = FormTypeValidator.stringValidator("Ingrese el correo electrónico del empleado");
        String address = FormTypeValidator.stringValidator("Ingrese la dirección del empleado");
        String password = FormTypeValidator.stringValidator("Ingrese la contraseña del empleado");
        Date birthDate = getBirthDateFromInput();
        String position = FormTypeValidator.stringValidator("Ingrese el cargo del empleado (ej: Vendedor, Cajero, Gerente)");
        double salary = FormTypeValidator.doubleValidator("Ingrese el salario mensual del empleado");
        String department = SetEmployeeDepartment.getDepartment();

        try {
            employeeService.createEmployee(id, docType, name, lastName, phone, email, address,
                    password, birthDate, position, salary, department);
            System.out.println("Empleado registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo registrar el empleado: " + e.getMessage());
        }
    }

    public void showAllEmployees() {
        List<Employee> employees = employeeService.selectAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (Employee employee : employees) {
            System.out.println(employee.getDetails());
        }
    }

    public void calculateBonus() {
        int id = FormTypeValidator.intValidator("Ingrese el ID del empleado para calcular su bono");
        Employee employee = employeeService.selectEmployeeById(id);
        if (employee == null) {
            System.out.println("No se encontró ningún empleado con el ID " + id + ".");
            return;
        }

        double percentage = FormTypeValidator.doubleValidator("Ingrese el porcentaje de bono a calcular (ej: 10 para 10%)");
        try {
            double bonus = employeeService.calculateBonus(id, percentage);
            double total = employee.calculateTotalCompensation(percentage);
            System.out.println("Empleado: " + employee.getFullName());
            System.out.println("Salario Base: $" + String.format("%.2f", employee.getSalary()));
            System.out.println("Bono (" + percentage + "%): $" + String.format("%.2f", bonus));
            System.out.println("Total a pagar con bono: $" + String.format("%.2f", total));
        } catch (IllegalArgumentException e) {
            System.out.println("Error al calcular el bono: " + e.getMessage());
        }
    }

    private Date getBirthDateFromInput() {
        while (true) {
            String birthDateString = FormTypeValidator.stringValidator(
                    "Ingrese la fecha de nacimiento del empleado (formato: yyyy-MM-dd)");
            try {
                return Date.from(LocalDate.parse(birthDateString).atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Use el formato yyyy-MM-dd e ingrese una fecha real.");
            }
        }
    }
}

