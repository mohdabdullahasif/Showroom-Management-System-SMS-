public class Manager extends Employee {
    private String department;

    public Manager(String name, long phone, int empId, String username, String password, double salary, String department) {
        super(name, phone, empId, username, password, salary); 
        setDepartment(department); 
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty.");
        }
        this.department = department;
    }

    public String getDepartment() {return this.department;}
    @Override
    public void setPassword(String password) {super.setPassword(password);}

    @Override
    public void display() {
        super.display(); 
        System.out.println("Department: " + this.department);
    }

    @Override
    public String toString() {
        return "Manager {" +
               "Emp ID=" + getEmpId() +
               ", Name=" + getName() +
               ", Phone=" + getPhone() +
               ", Username='" + getUsername() + '\'' +
               ", Salary=" + getSalary() +
               ", Department='" + this.department + '\'' +
               '}';
    }
}
