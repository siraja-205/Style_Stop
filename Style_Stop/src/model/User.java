/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

// MVC: Model — represents application data and business rules for users
// OOP: Abstraction — defines what every user must have and do.
// OOP: Encapsulation — details are kept private and accessed via methods.
public abstract class User { // Abstraction: abstract base type
    private String username; // Encapsulation: private field
    private String password; // Encapsulation: private field
    private String role;     // Encapsulation: private field

    public User(String username, String password, String role) {
        this.username = username; // Encapsulation: initialize private field
        this.password = password; // Encapsulation: initialize private field
        this.role = role;         // Encapsulation: initialize private field
    }

    // Getter: safely read the username (keeps field private)
    public String getUsername() {
        return username; // Encapsulation: access through method, not direct field access
    }

    // Setter: safely change the username (validations could be added here)
    public void setUsername(String username) {
        this.username = username; // Encapsulation: modify through method
    }

    // Getter: safely read the password
    public String getPassword() {
        return password; // Encapsulation: access through method
    }

    // Setter: safely change the password
    public void setPassword(String password) {
        this.password = password; // Encapsulation: modify through method
    }

    // Getter: safely read the role
    public String getRole() {
        return role; // Encapsulation: access through method
    }

    // Setter: safely change the role
    public void setRole(String role) {
        this.role = role; // Encapsulation: modify through method
    }

    // OOP: Polymorphism — different user types return different names here,
    // but the app calls the same method. (See ManagerUser/AssistantUser.)
    // OOP: Inheritance — subclasses extend this class and fill in the details.
    public abstract String getDashboardName(); // Abstraction + Polymorphism: must be implemented by subclasses
}
