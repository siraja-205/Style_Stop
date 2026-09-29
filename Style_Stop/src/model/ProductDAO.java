/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

// MVC: Model — DAO that reads/writes Product data
// JDBC: uses Connection/Statement/PreparedStatement/ResultSet to talk to the DB
public class ProductDAO {
    private final java.util.List<product> cache = new java.util.ArrayList<>();

    public java.util.List<product> all() {
        // JDBC (read): open a connection, create a statement, run a SELECT, read rows
        // try-with-resources closes everything automatically
        try (java.sql.Connection con = Database.DBConnection.getInstance().getConnection();
             java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery("SELECT product_id, name, category, brand, size, price, stock FROM products")) {
            java.util.List<product> list = new java.util.ArrayList<>();
            while (rs.next()) {
                // Each rs.getXxx reads a column from the current row
                list.add(new product(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>(cache);
        }
    }

    public java.util.List<product> getAll() {
        return all();
    }

    public product getById(String id) {
        for (product p : all()) {
            if (p.getProductId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void add(product p) {
        // JDBC (create): PreparedStatement with ? placeholders prevents SQL injection
        // setXxx binds values; executeUpdate writes data
        try (java.sql.Connection con = Database.DBConnection.getInstance().getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO products(product_id, name, category, brand, size, price, stock) VALUES(?,?,?,?,?,?,?)")) {
            ps.setString(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getBrand());
            ps.setString(5, p.getSize());
            ps.setInt(6, p.getPrice());
            ps.setInt(7, p.getStock());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            cache.add(p);
        }
    }

    public void update(product t) {
        updateById(t.getProductId(), t);
    }

    public void updateById(String oldId, product p) {
        // JDBC (update): update existing row where product_id matches
        try (java.sql.Connection con = Database.DBConnection.getInstance().getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(
                     "UPDATE products SET product_id=?, name=?, category=?, brand=?, size=?, price=?, stock=? WHERE product_id=?")) {
            ps.setString(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getBrand());
            ps.setString(5, p.getSize());
            ps.setInt(6, p.getPrice());
            ps.setInt(7, p.getStock());
            ps.setString(8, oldId);
            ps.executeUpdate();
        } catch (Exception e) {
            for (int i = 0; i < cache.size(); i++) {
                if (cache.get(i).getProductId().equals(oldId)) {
                    cache.set(i, p);
                    break;
                }
            }
        }
    }

    public void deleteById(String id) {
        // JDBC (delete): delete a row by id
        try (java.sql.Connection con = Database.DBConnection.getInstance().getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE product_id=?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            cache.removeIf(x -> x.getProductId().equals(id));
        }
    }

    public void delete(String id) {
        deleteById(id);
    }
}
