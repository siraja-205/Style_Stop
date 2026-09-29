package model;

// MVC: Model — concrete user type used by the system
public class ManagerUser extends User { // Inheritance: extends User
    public ManagerUser(String username, String password) {
        super(username, password, "Manager");
    }

    @Override
    // Polymorphism: override base method with Manager-specific answer
    public String getDashboardName() {
        return "Manager Dashboard";
    }
}
