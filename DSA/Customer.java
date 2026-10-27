import datastructures.DoublyLinkedList;

public class Customer extends Person {
    private int customerId;
    private String username;
    private String password;
    private DoublyLinkedList<Car> ownedCars; 
    private DoublyLinkedList<Invoice> purchaseHistory;

    public Customer() {
        super();
        initialize();
        setCustomerId(0);
        setUsername("");
        setPassword("");
    }

    public Customer(String name, long phoneNumber, int customerId, String username, String password) {
        super(name, phoneNumber);
        initialize();
        setCustomerId(customerId);
        setUsername(username);
        setPassword(password);
    }

    private void initialize() {
        this.ownedCars = new DoublyLinkedList<>();
        this.purchaseHistory = new DoublyLinkedList<>();
    }

    public void setCustomerId(int customerId) {
        if (customerId <= 0) {throw new IllegalArgumentException("Customer ID must be positive.");}
        this.customerId = customerId;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {throw new IllegalArgumentException("Username cannot be null or empty.");}
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {throw new IllegalArgumentException("Password must exceed 8 characters.");}
        boolean hasUpperCase = false, hasLowerCase = false, hasDigit = false, hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            else if (Character.isLowerCase(c)) hasLowerCase = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecialChar = true;
        }

        if (!(hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar)) {
            throw new IllegalArgumentException(
                "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character."
            );
        }

        this.password = password;
    }

    public int getCustomerId() {return customerId;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public DoublyLinkedList<Car> getOwnedCars() {return ownedCars;}
    
    public void addPurchaseHistory(Invoice invoice) {
        if (invoice == null) throw new IllegalArgumentException("Invoice cannot be null.");
        this.purchaseHistory.add(invoice);  // ✅ Now it will not be null
    }
    
    public DoublyLinkedList<Invoice> getPurchaseHistory() { return purchaseHistory; }

    public void addCar(Car car) {
        if (car == null) {throw new IllegalArgumentException("Car cannot be null.");}
        if (ownedCars.searchById(car.getCarId()) != null) {
            throw new IllegalArgumentException("Car with ID " + car.getCarId() + " is already owned by the customer.");
        }
        ownedCars.add(car);
    }

    @Override
    public void display() {
        System.out.println("\nCustomer Details:");
        super.display();
        System.out.println("Customer ID: " + this.getCustomerId() +
                           "\nUsername: " + this.getUsername() +
                           "\nPassword: " + this.getPassword() +
                           "\nOwned Cars:");

        if (ownedCars.isEmpty()) {System.out.println("No cars owned.");} 
        else {ownedCars.display();}
    }
}
