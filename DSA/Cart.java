import datastructures.DoublyLinkedList;

class Cart {
    private DoublyLinkedList<Car> items; 
    public Cart() {this.items = new DoublyLinkedList<>();}

    public void addToCart(Car car) {
        if (car != null) {
            items.add(car);
            System.out.println(car.getMake() + " " + car.getModel() + " added to the cart.");
        } else {
            System.out.println("Cannot add null to the cart.");
        }
    }

    public void removeFromCart(Car car) {
        if (items.remove(car)) {System.out.println(car.getMake() + " " + car.getModel() + " removed from the cart.");} 
        else {System.out.println("Car not found in the cart.");}
    }

    public void clearCart() {
        items.clear();
        System.out.println("Cart has been cleared.");
    }

    public boolean isEmpty() {return items.isEmpty();}
    public DoublyLinkedList<Car> getItems() {return items;}

    public double calculateTotal() {
        double total = 0.0;
        DoublyLinkedList<Car>.ListIterator iterator = items.getIterator();
        while (iterator.hasNext()) {
            total += iterator.next().getPrice();
        }
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        }else{
                System.out.println("-------------------------");
                System.out.println("Items in the Cart:");
                items.display();
                System.out.println("-------------------------");
        }
    }
}
