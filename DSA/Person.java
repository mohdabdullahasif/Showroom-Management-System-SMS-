public class Person {
    private String name;
    private long phone;

    public Person() {
        this.name = "";
        this.phone = 0;
    }

    public Person(String name, long phone) {
        setName(name);
        setPhone(phone);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setPhone(long phone) {
        String phoneString = String.valueOf(phone);
        if (phoneString.length() < 10) {
            throw new IllegalArgumentException("Phone number must have at least 10 digits.");
        }
        if (phoneString.startsWith("03")) {
            phoneString = "92" + phoneString.substring(1);
        }
        if (phoneString.startsWith("92") && phoneString.length() == 12) {
        } else if (phoneString.length() > 12) {
            throw new IllegalArgumentException("Phone number exceeds valid length. Use the format 923XXXXXXXXX.");
        } else {
            throw new IllegalArgumentException("Invalid phone number. Use the format 923XXXXXXXXX.");
        }

        this.phone = Long.parseLong(phoneString);
    }

    public String getName() {return name;}
    public long getPhone() {return phone;}

    public void display() {
        System.out.println("Name: " + this.name);
        System.out.println("Phone: " + this.phone);
    }
}
