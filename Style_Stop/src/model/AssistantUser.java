package model;

// MVC: Model — concrete user type used by the system
public class AssistantUser extends User { // Inheritance: extends User
    public AssistantUser(String username, String password) {
        super(username, password, "Assistant");
    }

    @Override
    // Polymorphism: override base method with Assistant-specific answer
    public String getDashboardName() {
        return "Product Dashboard";
    }
}
