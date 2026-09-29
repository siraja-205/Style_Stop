/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Sithumini
 */
// MVC: Controller — reacts to UI actions and updates the Model
// Talks to ProductDAO (Model) and tells the View what to show
public class ProductController {
    private final model.ProductDAO dao;

    public ProductController(model.ProductDAO dao) {
        this.dao = dao;
    }
    
    public model.product get(int index) {
        return dao.getAll().get(index);
    }

    public String validate(String pid, String pname, String brand, String size, String priceStr, String stockStr) {
        if (pid.isEmpty() || pname.isEmpty() || brand.isEmpty() || size.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
            return "Please fill all required fields";
        }
        try {
            Integer.parseInt(priceStr);
            Integer.parseInt(stockStr);
        } catch (NumberFormatException ex) {
            return "Price and Stock must be integers";
        }
        return null;
    }

    public void add(String pid, String pname, String category, String brand, String size, String priceStr, String stockStr) {
        int price = Integer.parseInt(priceStr);
        int stock = Integer.parseInt(stockStr);
        dao.add(new model.product(pid, pname, category, brand, size, price, stock));
    }

    public void update(int index, String pid, String pname, String category, String brand, String size, String priceStr, String stockStr) {
        int price = Integer.parseInt(priceStr);
        int stock = Integer.parseInt(stockStr);
        String oldId = dao.getAll().get(index).getProductId();
        dao.updateById(oldId, new model.product(pid, pname, category, brand, size, price, stock));
    }

    public void delete(int index) {
        String id = dao.getAll().get(index).getProductId();
        dao.delete(id);
    }

    public void refresh(javax.swing.table.DefaultTableModel model) {
        model.setRowCount(0);
        for (model.product p : dao.getAll()) {
            model.addRow(p.toRow());
        }
    }

    public String lowStockAlert(int threshold) {
        StringBuilder sb = new StringBuilder();
        for (model.product p : dao.getAll()) {
            if (p.getStock() <= threshold) {
                if (sb.length() == 0) {
                    sb.append("⚠ LOW STOCK ALERT ⚠").append("\n \n");
                }
                sb.append(" Product Name: ").append(p.getProductName()).append(" \n");
                sb.append(" Current Stock: ").append(p.getStock()).append(" \n");
                sb.append(" Status: RESTOCK REQUIRED \n");
                sb.append(" -------------------------- \n \n");
            }
        }
        if (sb.length() == 0) {
            return "No low stock items";
        }
        return sb.toString();
    }
}
