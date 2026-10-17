package app.userinterface;

import app.domain.Person;
import app.repository.CustomerRepositoryImpl;
import app.repository.EmployeeRepositoryImpl;
import app.repository.ProductRepositoryImpl;
import app.repository.SaleRepositoryImpl;
import app.service.CustomerServiceImpl;
import app.service.EmployeeServiceImpl;
import app.service.ProductServiceImpl;
import app.service.SaleServiceImpl;
import app.service.inputport.CustomerService;
import app.service.inputport.EmployeeService;
import app.service.inputport.ProductService;
import app.service.inputport.SaleService;
import app.service.outputport.CustomerRepository;
import app.service.outputport.EmployeeRepository;
import app.service.outputport.ProductRepository;
import app.service.outputport.SaleRepository;
import app.service.validations.FormTypeValidator;
import app.view.CustomerView;
import app.view.EmployeeView;
import app.view.ProductView;
import app.view.SaleView;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final ProductService productService = new ProductServiceImpl(productRepository);
    private final ProductView productView = new ProductView(productService);

    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private final CustomerService customerService = new CustomerServiceImpl(customerRepository);
    private final CustomerView customerView = new CustomerView(customerService);

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
    private final EmployeeView employeeView = new EmployeeView(employeeService);

    private final SaleRepository saleRepository = new SaleRepositoryImpl();
    private final SaleService saleService = new SaleServiceImpl(saleRepository, customerRepository, productRepository);
    private final SaleView saleView = new SaleView(saleService);

    public void menuApp() {
        int option;
        do {
            option = FormTypeValidator.intValidator("""
                    Bienvenido a Sauz Store
                    Seleccione una opción:
                    1. Registrar producto
                    2. Listar productos
                    3. Registrar cliente
                    4. Listar clientes
                    5. Registrar venta
                    6. Listar ventas
                    7. Registrar empleado
                    8. Listar empleados
                    9. Calcular bono de empleado
                    10. Listar todas las personas (Polimorfismo: Clientes y Empleados)
                    0. Salir
                    """);

            switch (option) {
                case 1:
                    productView.createProduct();
                    break;
                case 2:
                    productView.showAllProducts();
                    break;
                case 3:
                    customerView.createCustomer();
                    break;
                case 4:
                    customerView.showAllCustomers();
                    break;
                case 5:
                    saleView.createSale();
                    break;
                case 6:
                    saleView.showAllSales();
                    break;
                case 7:
                    employeeView.createEmployee();
                    break;
                case 8:
                    employeeView.showAllEmployees();
                    break;
                case 9:
                    employeeView.calculateBonus();
                    break;
                case 10:
                    showAllPeople();
                    break;
                case 0:
                    System.out.println("Gracias por usar Sauz Store");
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
            }
        } while (option != 0);
    }

    private void showAllPeople() {
        List<Person> people = new ArrayList<>();
        people.addAll(customerService.selectAllCustomers());
        people.addAll(employeeService.selectAllEmployees());

        if (people.isEmpty()) {
            System.out.println("No hay personas registradas (ni clientes ni empleados).");
            return;
        }

        System.out.println("\n--- LISTA GENERAL DE PERSONAS (DEMOSTRACIÓN DE POLIMORFISMO POO) ---");
        for (Person person : people) {
            System.out.println(person.getDetails() + " | [Rol Polimórfico: " + person.getRole() + "]");
        }
    }
}
