/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


// JDBC: central place to obtain DB connections
// Uses DriverManager with a JDBC URL (SQLite by default)
// Singleton: one shared DBConnection for the whole app
public class DBConnection {
    private static DBConnection instance;
    private final String url = System.getProperty("db.url", "jdbc:sqlite:sirajastyleshop.db");
    private final String user = System.getProperty("db.user", "");
    private final String pass = System.getProperty("db.pass", "");

    private DBConnection() {}

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public java.sql.Connection getConnection() throws java.sql.SQLException {
        ensureDriver(); // JDBC: make sure the right driver class is loaded
        return java.sql.DriverManager.getConnection(url, user, pass); // opens a new DB connection
    }

    private void ensureDriver() {
        String lower = url.toLowerCase();
        try {
            if (lower.startsWith("jdbc:sqlite")) {
                Class.forName("org.sqlite.JDBC"); // SQLite driver
            } else if (lower.startsWith("jdbc:mysql")) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL driver
            } else if (lower.startsWith("jdbc:postgresql")) {
                Class.forName("org.postgresql.Driver"); // PostgreSQL driver
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
