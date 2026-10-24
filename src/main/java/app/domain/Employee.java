package app.domain;

import java.util.Date;

public class Employee extends Person {
    private String position;
    private double salary;
    private String department;

    public Employee() {
    }

    public Employee(int id, String docType, String name, String lastName, String phone,
                    String email, String address, String password, Date birthDate,
                    String position, double salary, String department) {
        super(id, docType, name, lastName, phone, email, address, password, birthDate);
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double calculateBonus(double bonusPercentage) {
        if (bonusPercentage < 0) {
            throw new IllegalArgumentException("El porcentaje de bono no puede ser negativo.");
        }
        return this.salary * (bonusPercentage / 100.0);
    }

    public double calculateTotalCompensation(double bonusPercentage) {
        return this.salary + calculateBonus(bonusPercentage);
    }

    @Override
    public String getRole() {
        return "Empleado - " + position + " (" + department + ")";
    }

    @Override
    public String getDetails() {
        return "ID: " + getId()
                + " | Nombre: " + getFullName()
                + " | Documento: " + getDocType()
                + " | Cargo: " + position
                + " | Departamento: " + department
                + " | Salario: $" + String.format("%.2f", salary)
                + " | Correo: " + getEmail()
                + " | Teléfono: " + getPhone();
    }
}

