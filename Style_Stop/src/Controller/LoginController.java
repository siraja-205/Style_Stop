package Controller;

import model.User;
import model.UserDAO;

// MVC: Controller — handles user input and coordinates Model and View
// Uses UserDAO (Model) to check credentials, View shows messages/screens
public class LoginController {
    private final UserDAO userDAO = new UserDAO();

    public String validate(String name, String pass) {
        if (name.trim().isEmpty() || pass.trim().isEmpty()) {
            return "Enter name and password";
        }
        return null;
    }

    public User authenticate(String name, String pass) {
        return userDAO.validateUser(name, pass);
    }
}
