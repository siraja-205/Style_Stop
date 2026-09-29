package model;

import java.util.ArrayList;
import java.util.List;

// MVC: Model — DAO that stores and retrieves User objects
// Polymorphism: works with the base type (User) but can hold subclasses
public class UserDAO {
    private static final List<User> users = new ArrayList<>(); // Polymorphism: collection of base type

    static { // Inheritance + Polymorphism: storing subclass instances in base-type list
        users.add(new ManagerUser("Manager", "manager123"));   // subclass instance
        users.add(new AssistantUser("Assistant", "assistant123")); // subclass instance
    }

    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    public User getById(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public void add(User user) {
        users.add(user);
    }

    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                return;
            }
        }
    }

    public void delete(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
    }

    public User validateUser(String username, String password) {
        // Encapsulation: validation logic hidden behind DAO API
        User user = getById(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
