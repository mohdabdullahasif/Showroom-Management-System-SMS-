public class Car implements Comparable<Car> {
    private int carId;
    private String make;
    private String model;
    private double price;
    private Engine engine;

    public Car(int carId, String make, String model, double price, String engineType, int horsepower) {
        setCarId(carId);
        setMake(make);
        setModel(model);
        setPrice(price);
        this.engine = new Engine(engineType, horsepower);
    }

    public void setCarId(int carId) {
        if (carId <= 0) {throw new IllegalArgumentException("Car ID must be positive.");}
        this.carId = carId;
    }

    public void setMake(String make) {
        if (make == null || make.trim().isEmpty()) {throw new IllegalArgumentException("Make cannot be empty.");}
        this.make = make;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {throw new IllegalArgumentException("Model cannot be empty.");}
        this.model = model;
    }

    public void setPrice(double price) {
        if (price < 0) {throw new IllegalArgumentException("Price cannot be negative.");}
        this.price = price;
    }
    

    // Getters
    public int getCarId() { return this.carId; }
    public String getMake() { return this.make; }
    public String getModel() { return this.model; }
    public double getPrice() { return this.price; }
    public Engine getEngine() { return this.engine; }

    @Override
    public int compareTo(Car other) {
        int priceComparison = Double.compare(this.price, other.price);
        if (priceComparison != 0) {return priceComparison;}
        return this.model.compareTo(other.model);
    }

    public void display() {
        System.out.println("Car ID: " + this.carId +
                "\nMake: " + this.make +
                "\nModel: " + this.model +
                "\nPrice: $" + String.format("%.2f", this.price));
        this.engine.display();
    }

    @Override
    public String toString() {
        return "Car : \n" +
               "ID : " + carId +
               "\nMake : " + make +
               "\nModel : " + model +
               "\nPrice : " + String.format("%.2f", price) +
               "\nEngine : " + engine.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return carId == car.carId;
    }

    @Override
    public int hashCode() {return Integer.hashCode(carId);}
}
