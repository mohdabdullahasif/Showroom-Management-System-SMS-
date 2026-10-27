
public class Engine {
    private String type;
    private int horsepower;

    public Engine(String type, int horsepower) {
        setType(type);
        setHorsepower(horsepower);
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Engine type cannot be empty.");
        }
        this.type = type;
    }

    public void setHorsepower(int horsepower) {
        if (horsepower <= 0) {
            throw new IllegalArgumentException("Horsepower must be positive.");
        }
        this.horsepower = horsepower;
    }

    public void display() {System.out.println("Engine Type: " + this.type + "\nHorsepower: " + this.horsepower);}
    @Override
    public String toString() {
        return "Engine Type: " + this.type + "\nHorsepower: " + this.horsepower;
    }
}
