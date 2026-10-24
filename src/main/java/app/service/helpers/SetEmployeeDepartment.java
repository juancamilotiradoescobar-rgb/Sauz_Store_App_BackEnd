package app.service.helpers;

import app.domain.enums.DepartmentEnum;
import app.service.validations.FormTypeValidator;

public class SetEmployeeDepartment {
    private SetEmployeeDepartment() {
    }

    public static String getDepartment() {
        while (true) {
            int option = FormTypeValidator.intValidator("""
                    Seleccione el departamento del empleado:
                    1. Ventas
                    2. Logística
                    3. Administración
                    4. Atención al Cliente
                    """);
            switch (option) {
                case 1:
                    return DepartmentEnum.SALES.getValue();
                case 2:
                    return DepartmentEnum.LOGISTICS.getValue();
                case 3:
                    return DepartmentEnum.ADMINISTRATION.getValue();
                case 4:
                    return DepartmentEnum.CUSTOMER_SUPPORT.getValue();
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}

