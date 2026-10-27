    import java.util.Iterator;
    import datastructures.DoublyLinkedList;
    import datastructures.HashMap;
    import datastructures.AVLTree;
    import datastructures.ArrayList;
    import datastructures.ArrayQueue;

    public class Showroom implements Iterable<Employee> {
        private String name;
        private String location;
        private HashMap<Integer, Car> cars;
        private DoublyLinkedList<Employee> employees;
        private AVLTree<Car> sortedCars;
        private ArrayList<Invoice> invoices;

        public Showroom(String name, String location) {
            this.name = name;
            this.location = location;
            this.cars = new HashMap<>();
            this.employees = new DoublyLinkedList<>();
            this.sortedCars = new AVLTree<>();
            this.invoices = new ArrayList<>();
        }
        public void setInvoices(ArrayQueue<Invoice> invoicesQueue) { while (!invoicesQueue.isEmpty()) { invoices.add(invoicesQueue.dequeue());}}

        public void addCar(Car car) {
            if (car == null) throw new IllegalArgumentException("Car cannot be null.");
            cars.put(car.getCarId(), car);
            sortedCars.insert(car);
        }

        public boolean removeCar(int carId) {
            Car car = cars.get(carId);
            if (car != null) {
                cars.remove(carId);
                sortedCars.delete(car);
                return true;
            }
            System.out.println("Car not found!");
            return false;
        }

        public boolean updateCarPrice(int carId, double newPrice) {
            Car car = cars.get(carId);
            if (car != null) {
                sortedCars.delete(car);
                car.setPrice(newPrice);
                sortedCars.insert(car);
                System.out.println("Car price updated: " + car.getModel() + " -> " + newPrice);
                return true;
            }
            System.out.println("Car not found!");
            return false;
        }

        public void addEmployee(Employee employee) {
            if (employee == null) throw new IllegalArgumentException("Employee cannot be null.");
            employees.add(employee);
        }
        public Car getCarById(int carId) {return cars.get(carId);}

        public boolean removeEmployee(int empId) {
            Iterator<Employee> iterator = employees.getIterator(); 
            while (iterator.hasNext()) {
                Employee employee = iterator.next();
                if (employee.getEmpId() == empId) {
                    iterator.remove();
                    return true;
                }
            }
            System.out.println("Employee not found!");
            return false;
        }

        public boolean updateEmployeeSalary(int empId, double newSalary) {
            Iterator<Employee> iterator = employees.getIterator(); 
            while (iterator.hasNext()) {
                Employee employee = iterator.next();
                if (employee.getEmpId() == empId) {
                    employee.setSalary(newSalary);
                    System.out.println("Salary updated for " + employee.getName() + " -> " + newSalary);
                    return true;
                }
            }
            System.out.println("Employee not found!");
            return false;
        }

        public void addInvoice(Invoice invoice) {
            if (invoice == null) throw new IllegalArgumentException("Invoice cannot be null.");
            invoices.add(invoice);
            System.out.println("Invoice added successfully.");
        }

        public void printInvoices() {
            if (invoices.isEmpty()) {
                System.out.println("No invoices available.");}
                else {
                System.out.println("Invoices:");
                for (int i = 0; i < invoices.size(); i++) { 
                    System.out.println("-------------");
                    System.out.println(invoices.get(i));
                    System.out.println("-------------");
                }
            }
        }

        public void display() {
            System.out.println("Showroom Name: " + this.name);
            System.out.println("Location: " + this.location);
            System.out.println("\nCars in the showroom:");
            sortedCars.inOrderTraversal();
            System.out.println("\nEmployees in the showroom:");
            printEmployees();
            printInvoices();
        }

        public void printEmployees() {
            if (employees.isEmpty()) {System.out.println("No employees available in the showroom.");} 
            else {
                Iterator<Employee> iterator = employees.getIterator();
                while (iterator.hasNext()) {
                    Employee employee = iterator.next();
                    employee.display();
                    System.out.println();
                }
            }
        }
        
        public void printSortedCars() {
            System.out.println("Cars sorted by price/model:");
            sortedCars.inOrderTraversal();
        }

        @Override
        public Iterator<Employee> iterator() {return employees.getIterator();}
    }
