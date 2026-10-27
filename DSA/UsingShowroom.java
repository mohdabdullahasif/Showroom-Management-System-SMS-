import datastructures.ArrayQueue;
import datastructures.DoublyLinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UsingShowroom {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Predefined data
        DoublyLinkedList<Manager> managers = new DoublyLinkedList<>();
        managers.add(new Manager("manger", 923165498760L, 51, "admin", "12P@$$w0rd", 75000, "Graphics"));

        DoublyLinkedList<Customer> customers = new DoublyLinkedList<>();
        customers.add(new Customer("Abdullah Asif", 923224332434L, 101, "aby", "P@$$w0rd"));
        customers.add(new Customer("Zohair Shazib", 923492342421L, 102, "zoi", "12P@$$w0rd"));

        DoublyLinkedList<Employee> employees = new DoublyLinkedList<>();
        employees.add(new Employee("Ali", 923556655660L, 1, "ali", "12P@$$w0rd", 50000));
        employees.add(new Employee("Ali", 923556655660L, 1, "ali@emp", "12P@$$w0rd", 50000));
        employees.add(new Employee("Asad", 923228822880L, 2, "asad@emp", "12P@$$w0rd", 60000));
        employees.add(new Employee("Hassan", 923312345678L, 3, "hassan@emp", "12P@$$w0rd", 55000));
        employees.add(new Employee("Sara", 923301234567L, 4, "sara@emp", "12P@$$w0rd", 45000));
        employees.add(new Employee("Ayesha", 923332112233L, 5, "ayesha@emp", "12P@$$w0rd", 48000));
        employees.add(new Employee("Kamran", 923345678901L, 6, "kamran@emp", "12P@$$w0rd", 52000));

        Showroom showroom = new Showroom("Nova Showroom", "GRW");

        showroom.addCar(new Car(1, "Toyota", "Camry", 24000, "V6", 3000));
        showroom.addCar(new Car(2, "Toyota", "Camry", 24000, "V6", 3000));
        showroom.addCar(new Car(3, "Tesla", "Model 3", 35000, "Electric", 2000));
        showroom.addCar(new Car(4, "Honda", "Civic", 20000, "V4", 1500));
        showroom.addCar(new Car(5, "BMW", "X5", 60000, "V8", 4000));
        showroom.addCar(new Car(6, "Mercedes", "E-Class", 55000, "V6", 3500));


        ArrayQueue<Invoice> invoices = new ArrayQueue<>();
        DoublyLinkedList<Car> invoice1Cars = new DoublyLinkedList<>();

        invoice1Cars.add(new Car(1, "Toyota", "Camry", 24000, "V6", 300));

        Invoice invoice1 = new Invoice(
            "Abdullah Asif",
            invoice1Cars,
            24000,
            new Payment("1234567891011121", "Abdullah Asif", 24000)
        );

        DoublyLinkedList<Car> invoice2Cars = new DoublyLinkedList<>();
        invoice2Cars.add(new Car(2, "Tesla", "Model 3", 35000, "Electric", 200));

        Invoice invoice2 = new Invoice(
            "Zohair Shazib",
            invoice2Cars,
            35000,
            new Payment("5678123491011121", "Zohair Shazib", 35000)
        );

        invoices.enqueue(invoice1);
        invoices.enqueue(invoice2);
        showroom.setInvoices(invoices);


        System.out.println("Welcome to the Nova Showroom Management System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Sign Up (Customers Only)");
            System.out.println("2. Log In");
            System.out.println("3. Exit");

            int mainOption = getValidatedIntInput(scan, "Enter your choice: ", 1, 3);
            scan.nextLine();

            switch (mainOption) {
                case 1:
                    signUpCustomer(scan, customers);
                    break;
                case 2:
                    logInUser(scan, managers, employees, customers, showroom);
                    break;
                case 3:
                    System.out.println("Thank you for using the Nova Showroom Management System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scan.close();
    }

    private static void logInUser(Scanner scan, DoublyLinkedList<Manager> managers, DoublyLinkedList<Employee> employees, DoublyLinkedList<Customer> customers, Showroom showroom) {
        System.out.println("Choose account type to log in:");
        System.out.println("1. Manager\n2. Employee\n3. Customer");
        int loginType = getValidatedIntInput(scan, "Enter your choice: ", 1, 3);
        scan.nextLine();

        String loginUsername = getValidatedStringInput(scan, "Enter your username: ").trim();
        String loginPassword = getValidatedStringInput(scan, "Enter your password: ").trim();

        boolean loggedIn = false;
        if (loginType == 1) {loggedIn = managerLogin(scan, managers, showroom, loginUsername, loginPassword);} 
        else if (loginType == 2) {loggedIn = employeeLogin(scan, employees, showroom, loginUsername, loginPassword);} 
        else if (loginType == 3) {loggedIn = customerLogin(scan, customers, showroom, loginUsername, loginPassword);}
        if (!loggedIn) {System.out.println("Invalid username or password. Please try again.");}
    }

    private static boolean managerLogin(Scanner scan, DoublyLinkedList<Manager> managers, Showroom showroom, String username, String password) {
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username)) {
                while (!manager.getPassword().equals(password)) {
                    System.out.print("Incorrect password. Please try again: ");
                    password = scan.nextLine();
                }
                System.out.println("Login successful! Welcome, Manager " + manager.getName());
                managerOperations(scan, showroom, manager);
                return true;
            }
        }
        System.out.println("Username not found!");
        return false;
    }
    

    private static boolean employeeLogin(Scanner scan, DoublyLinkedList<Employee> employees, Showroom showroom, String username, String password) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                while (!employee.getPassword().equals(password)) {
                    System.out.print("Incorrect password. Please try again: ");
                    password = scan.nextLine();
                }
                System.out.println("Login successful! Welcome, Employee " + employee.getName());
                employeeOperations(scan, showroom, employee);
                return true;
            }
        }
        System.out.println("Username not found!");
        return false;
    }
    
    
    private static boolean customerLogin(Scanner scan, DoublyLinkedList<Customer> customers, Showroom showroom, String username, String password) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                while (!customer.getPassword().equals(password)) {
                    System.out.print("Incorrect password. Please try again: ");
                    password = scan.nextLine();
                }
                System.out.println("Login successful! Welcome, " + customer.getName());
                customerOperations(scan, showroom, customer);
                return true;
            }
        }
        System.out.println("Username not found!");
        return false;
    }
    

    private static void customerOperations(Scanner scan, Showroom showroom, Customer customer) {
        boolean keepGoing = true;
        DoublyLinkedList<Car> cart = new DoublyLinkedList<>();
    
        while (keepGoing) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Cars");
            System.out.println("2. Add Car to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Confirm Payment");
            System.out.println("5. Update Profile");   
            System.out.println("6. View Purchase History");
            System.out.println("7. Log Out");
            System.out.println("8. Remove Item from Cart");
            int choice = getValidatedIntInput(scan, "Enter your choice: ", 1, 8);
    
            switch (choice) {
                case 1:
                    showroom.printSortedCars();
                    break;
                case 2:
                    int carIdToAdd = getValidatedIntInput(scan, "Enter Car ID to add to cart: ", 1, Integer.MAX_VALUE);
                    Car car = showroom.getCarById(carIdToAdd);
                    if (car != null) {
                        cart.add(car);
                        System.out.println("Car added to cart successfully!");
                    } else {
                        System.out.println("Car not found!");
                    }
                    break;
                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty!");
                    } else {
                        System.out.println("Your Cart:");
                        for (Car c : cart) {
                            c.display();
                        }
                    }
                    break;
                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty! Add cars to your cart before confirming payment.");
                        break;
                    }
                    double totalAmount = 0;
                    for (Car c : cart) { totalAmount += c.getPrice(); }
                    System.out.printf("Total Amount: %.2f\n", totalAmount);
    
                    scan.nextLine();
                    System.out.print("Enter Card Holder Name: ");
                    String cardHolderName = scan.nextLine();
                    System.out.print("Enter Card Number: ");
                    String cardNumber = scan.nextLine();
    
                    Payment payment = new Payment(cardNumber, cardHolderName, totalAmount);
                    System.out.println("\nPayment Successful!");
                    System.out.println(payment);
    
                    Invoice invoice = new Invoice(customer.getName(), cart, totalAmount, payment);
                    showroom.addInvoice(invoice);
                    customer.addPurchaseHistory(invoice);  
    
                    for (Car c : cart) { showroom.removeCar(c.getCarId()); }
                    cart.clear();
                    break;
                case 5:
                    updateCustomerProfile(scan, customer);  
                    break;
                case 6:
                    if (customer.getPurchaseHistory().isEmpty()) { 
                        System.out.println("No purchase history available."); 
                        break;
                    }
                    viewPurchaseHistory(customer);
                    break;
                case 7:
                    keepGoing = false;
                    System.out.println("Logging out...");
                    break;
                case 8:
                    if(cart.isEmpty()){
                        System.out.println("Your cart is empty!");
                    }else{
                        System.out.println("Enter Number to remove the car form cart :");
                        int temp = scan.nextInt();
                        for (Car c : cart) {
                            if (c.getCarId() == temp) {
                                cart.remove(c);
                                System.out.println("Car removed from cart");
                                break;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    
    private static void updateCustomerProfile(Scanner scan, Customer customer) {
        System.out.println("\nUpdate Profile");
        System.out.print("Enter your current password: ");
        scan.nextLine();
        String enteredPassword = scan.nextLine();
    
        if (!customer.getPassword().equals(enteredPassword)) {
            System.out.println("Incorrect password! Profile update failed.");
            return;
        }
    
        System.out.print("Enter new name (or press Enter to keep current name): ");
        String newName = scan.nextLine().trim();
        if (!newName.isEmpty()) {
            customer.setName(newName);
        }
    
        System.out.print("Enter new username (or press Enter to keep current username): ");
        String newUsername = scan.nextLine().trim();
        if (!newUsername.isEmpty()) {
            customer.setUsername(newUsername);
        }
    
        System.out.println("Profile updated successfully!");
    }
    
    private static void viewPurchaseHistory(Customer customer) {
        System.out.println("\nPurchase History for " + customer.getName());
        
        if (customer.getPurchaseHistory().isEmpty()) {
            System.out.println("No purchase history found.");
            return;
        }
        
        for (Invoice invoice : customer.getPurchaseHistory()) {
            System.out.println(invoice);
        }
    }
    
    
    private static void signUpCustomer(Scanner scan, DoublyLinkedList<Customer> customers) {
        System.out.println("Enter your full name: ");
        String name = scan.nextLine();
        System.out.println("Enter your phone number: ");
        long phoneNumber = scan.nextLong();
        scan.nextLine();
        System.out.println("Enter your username: ");
        String username = scan.nextLine();
        System.out.println("Enter your password: ");
        String password = scan.nextLine();
    
        int customerId = customers.size() + 101;
        Customer newCustomer = new Customer(name, phoneNumber, customerId, username, password);
        customers.add(newCustomer);
        System.out.println("Signup successful.....!");
    }
    
    private static void addEmployee(Scanner scan, Showroom showroom) {
        System.out.println("Enter Employee Name: ");
        scan.nextLine();
        String name = scan.nextLine();
        int empId = getValidatedIntInput(scan, "Enter Employee ID: ", 1, Integer.MAX_VALUE);
    
        System.out.println("Enter Phone Number: ");
        long phoneNumber = scan.nextLong();
        scan.nextLine(); 
    
        System.out.println("Enter Username: ");
        String username = scan.nextLine();
    
        System.out.println("Enter Password: ");
        String password = scan.nextLine();
    
        System.out.println("Enter Salary: ");
        double salary = scan.nextDouble();
        scan.nextLine(); 

        Employee employee = new Employee(name, phoneNumber, empId, username, password, salary);
        showroom.addEmployee(employee);
        System.out.println("Employee added successfully!");
    }

    private static void addCar(Scanner scan, Showroom showroom) {
        int carId = getValidatedIntInput(scan, "Enter Car ID: ", 1, Integer.MAX_VALUE);
        scan.nextLine();  
    
        System.out.println("Enter Make: ");
        String make = scan.nextLine().trim();
        while (make.isEmpty()) {
            System.out.println("Make cannot be empty! Please enter a valid make: ");
            make = scan.nextLine().trim();
        }
    
        System.out.println("Enter Model: ");
        String model = scan.nextLine().trim();
        while (model.isEmpty()) {
            System.out.println("Model cannot be empty! Please enter a valid model: ");
            model = scan.nextLine().trim();
        }
    
        System.out.println("Enter Price: ");
        double price = scan.nextDouble();
        scan.nextLine(); 
    
        System.out.println("Enter Engine Type: ");
        String engineType = scan.nextLine().trim();
        while (engineType.isEmpty()) {
            System.out.println("Engine Type cannot be empty! Please enter a valid engine type: ");
            engineType = scan.nextLine().trim();
        }

        int horsepower = getValidatedIntInput(scan, "Enter Horsepower: ", 1, 10000); 
    
        Car car = new Car(carId, make, model, price, engineType, horsepower);
        showroom.addCar(car);
        System.out.println("Car added successfully!");
    }    
    
    private static void employeeOperations(Scanner scan, Showroom showroom, Employee loggedInEmployee) {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. View Cars");
            System.out.println("2. View Showroom Details");
            System.out.println("3. Sell Car");
            System.out.println("4. Update Profile");
            System.out.println("5. Log Out");
    
            int choice = getValidatedIntInput(scan, "Enter your choice: ", 1, 5);
    
            switch (choice) {
                case 1:
                    showroom.printSortedCars();
                    break;
                case 2:
                    showroom.display();
                    break;
                case 3:
                    int carIdToSell = getValidatedIntInput(scan, "Enter Car ID to sell: ", 1, Integer.MAX_VALUE);
                    Car carToSell = showroom.getCarById(carIdToSell);
                    if (carToSell != null) {
                        showroom.removeCar(carIdToSell);
                        System.out.println("Car sold successfully!");
                    } 
                    else {System.out.println("Car not found!");}
                    break;
                case 4:
                    updateEmployeeProfile(scan, loggedInEmployee);
                    break;
                case 5:
                    keepGoing = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updateEmployeeProfile(Scanner scan, Employee employee) {
        System.out.println("\n--- Update Profile ---");
        scan.nextLine();
        String enteredPassword = getValidatedStringInput(scan, "Enter your password to proceed: ");
        
        if (!employee.getPassword().equals(enteredPassword)) {
            System.out.println("Incorrect password! Profile update canceled.");
            return;
        }
    
        String newName = getValidatedStringInput(scan, "Enter new name (or press Enter to keep current name): ");
        if (!newName.trim().isEmpty()) {
            employee.setName(newName);
            System.out.println("Name updated successfully!");
        }
    
        String newUsername = getValidatedStringInput(scan, "Enter new username (or press Enter to keep current username): ");
        if (!newUsername.trim().isEmpty()) {
            employee.setUsername(newUsername);
            System.out.println("Username updated successfully!");
        }
    
        System.out.println("Profile updated successfully!");
    }  
    
    private static void managerOperations(Scanner scan, Showroom showroom, Manager manager) {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\nManager Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Car");
            System.out.println("3. View Showroom Details");
            System.out.println("4. View Invoices");
            System.out.println("5. Remove Employee");
            System.out.println("6. Remove Car");
            System.out.println("7. Update Car Price");
            System.out.println("8. Update Profile");
            System.out.println("9. Log Out");
    
            int choice = getValidatedIntInput(scan, "Enter your choice: ", 1, 9);
    
            switch (choice) {
                case 1:
                    addEmployee(scan, showroom);
                    break;
                case 2:
                    addCar(scan, showroom);
                    break;
                case 3:
                    showroom.display();
                    break;
                case 4:
                    showroom.printInvoices();
                    break;
                case 5:
                    System.out.println("Enter Employee ID to remove: ");
                    int empIdToRemove = scan.nextInt();
                    boolean removed = showroom.removeEmployee(empIdToRemove);
                    if (removed) {System.out.println("Employee removed successfully!");} 
                    else {System.out.println("Employee not found!");}
                    break;
                case 6:
                    int carIdToRemove = getValidatedIntInput(scan, "Enter Car ID to remove: ", 1, Integer.MAX_VALUE);
                    boolean carRemoved = showroom.removeCar(carIdToRemove);
                    if (carRemoved) {System.out.println("Car removed successfully!");}
                    else {System.out.println("Car not found!");}
                    break;
                case 7:
                    updateCarPriceByManager(scan, showroom);
                    break;
                case 8:
                    updateManagerProfile(scan, manager);
                    break;
                case 9:
                    keepGoing = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updateManagerProfile(Scanner scan, Manager manager) {
        System.out.print("\nEnter your current password for verification: ");
        scan.nextLine();
        String password = scan.nextLine();
    
        if (!manager.getPassword().equals(password)) {
            System.out.println("Incorrect password! Profile update failed.");
            return;
        }
    
        System.out.println("\nProfile Update Menu:");
        System.out.println("1. Update Name");
        System.out.println("2. Update Username");
        System.out.println("3. Cancel");
    
        int option = getValidatedIntInput(scan, "Enter your choice: ", 1, 3);
    
        switch (option) {
            case 1:
                System.out.print("Enter new name: ");
                scan.nextLine();
                String newName = scan.nextLine();
                if (!newName.trim().isEmpty()) {
                    manager.setName(newName);
                    System.out.println("Name updated successfully!");
                } 
                else {System.out.println("Invalid name. Update failed.");}
                break;
            case 2:
                System.out.print("Enter new username: ");
                String newUsername = scan.nextLine();
                if (!newUsername.trim().isEmpty()) {
                    manager.setUsername(newUsername);
                    System.out.println("Username updated successfully!");
                } 
                else {System.out.println("Invalid username. Update failed.");}
                break;
            case 3:
                System.out.println("Profile update canceled.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    
    
    
    private static void updateCarPriceByManager(Scanner scan, Showroom showroom) {
        int carId = getValidatedIntInput(scan, "Enter Car ID to update price: ", 1, Integer.MAX_VALUE);
        Car car = showroom.getCarById(carId);
        
        if (car != null) {
            System.out.println("Current Price: $" + car.getPrice());
            double newPrice = getValidatedDoubleInput(scan, "Enter new price: ", 1, Double.MAX_VALUE);
            
            boolean updated = showroom.updateCarPrice(carId, newPrice);
            if (updated) {System.out.println("Car price updated successfully!");} 
            else {System.out.println("Failed to update car price.");}
        } 
        else {System.out.println("Car not found!");}
    }

    private static double getValidatedDoubleInput(Scanner scan, String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scan.nextDouble();
                if (input < min || input > max) {
                    throw new IllegalArgumentException("Input must be between " + min + " and " + max + ".");
                }
                scan.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine();
            }
        }
    }
    
    

    private static int getValidatedIntInput(Scanner scan, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scan.nextInt();
                if (input < min || input > max) {
                    throw new IllegalArgumentException("Input must be between " + min + " and " + max + ".");
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine();
            }
        }
    }

    private static String getValidatedStringInput(Scanner scan, String prompt) {
        System.out.print(prompt);
        return scan.nextLine().trim();
    }
    
    
}