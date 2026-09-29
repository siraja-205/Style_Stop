/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


// JDBC: uses statements to create tables and insert starter data
// Responsibility: database schema creation and initial seeding
public class DBIntilzetion {
    public void init() {
        try (java.sql.Connection con = DBConnection.getInstance().getConnection();
             java.sql.Statement st = con.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS products (" +
                            "product_id TEXT PRIMARY KEY," +
                            "name TEXT NOT NULL," +
                            "category TEXT," +
                            "brand TEXT," +
                            "size TEXT," +
                            "price INTEGER NOT NULL," +
                            "stock INTEGER NOT NULL" +
                            ")"
            );
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void seedIfEmpty() {
        try (java.sql.Connection con = DBConnection.getInstance().getConnection();
             java.sql.Statement st = con.createStatement()) {
            try (java.sql.ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM products")) {
                if (rs.next() && rs.getInt(1) == 0) {
                    st.executeUpdate("INSERT INTO products(product_id,name,category,brand,size,price,stock) VALUES" +
                            "('P001','Formal Shirt','Mens wear','BrandX','L',2500,3)," +
                            "('P002','Kids T-Shirt','KIDS','BrandY','S',1500,2)," +
                            "('P003','Women Dress','Womens wear','BrandZ','M',3200,10)");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
