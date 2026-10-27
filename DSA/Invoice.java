import datastructures.DoublyLinkedList;

class Invoice {
    private String customerName;
    private DoublyLinkedList<Car> purchasedCars;
    private double totalAmount;
    private Payment paymentDetails;

    public Invoice(String customerName, DoublyLinkedList<Car> purchasedCars, double totalAmount, Payment paymentDetails) {
        this.customerName = customerName;
        this.purchasedCars = new DoublyLinkedList<>();

        DoublyLinkedList<Car>.ListIterator iterator = purchasedCars.getIterator();
        while (iterator.hasNext()) {
            this.purchasedCars.add(iterator.next());
        }

        this.totalAmount = totalAmount;
        this.paymentDetails = paymentDetails;
    }

    public String getCustomerName() {return customerName;}
    public DoublyLinkedList<Car> getPurchasedCars() {return purchasedCars;}
    public double getTotalAmount() {return totalAmount;}
    public Payment getPaymentDetails() {return paymentDetails;}

    @Override
    public String toString() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Invoice for: ").append(customerName).append("\n")
               .append("Purchased Cars:\n");

        DoublyLinkedList<Car>.ListIterator iterator = purchasedCars.getIterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            invoice.append("  - ").append(car.getMake())
                   .append(" ").append(car.getModel())
                   .append(", Price: ").append(car.getPrice()).append("\n");
        }

        invoice.append("Total Amount: ").append(totalAmount).append("\n")
               .append("Payment Details:\n")
               .append(paymentDetails.toString()).append("\n");
        return invoice.toString();
    }
}
