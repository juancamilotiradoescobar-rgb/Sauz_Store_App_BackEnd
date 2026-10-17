package app.domain.enums;

public enum DepartmentEnum {
    SALES("Ventas"),
    LOGISTICS("Logística"),
    ADMINISTRATION("Administración"),
    CUSTOMER_SUPPORT("Atención al Cliente");

    private final String value;

    DepartmentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

