public class Employee extends Person {
    private int empId;
    private String username;
    private String password;
    private double salary;

    public Employee(String name, long phone, int empId, String username, String password, double salary) {
        super(name, phone);
        setEmpId(empId);
        setUsername(username);
        setPassword(password);
        setSalary(salary);
    }

    public void setEmpId(int empId) {
        if (empId <= 0) {throw new IllegalArgumentException("Employee ID must be positive.");}
        this.empId = empId;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must exceed 8 characters.");
        }

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

    public void setSalary(double salary) {
        if (salary < 0) {throw new IllegalArgumentException("Salary cannot be negative.");}
        this.salary = salary;
    }

    public int getEmpId() {return this.empId;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public double getSalary() {return this.salary;}

    @Override
    public void display() {
        System.out.println("Employee Details:");
        super.display(); 
        System.out.println("Emp ID: " + this.empId +
                           "\nUsername: " + this.username +
                           "\nSalary: " + this.salary);
    }

    @Override
    public String toString() {
        return "Employee {" +
               "Emp ID=" + empId +
               ", Name=" + getName() +
               ", Phone=" + getPhone() + 
               ", Username='" + username + '\'' +
               ", Salary=" + salary +
              '}';
}

}
