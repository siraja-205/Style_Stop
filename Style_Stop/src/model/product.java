/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


// MVC: Model — product entity used by the app
// OOP: Encapsulation — product details are private.
// Other code must use methods below to read/change them safely.
public class product {
    private String productId;   // Encapsulation: private field
    private String productName; // Encapsulation: private field
    private String category;    // Encapsulation: private field
    private String brand;       // Encapsulation: private field
    private String size;        // Encapsulation: private field
    private int price;          // Encapsulation: private field
    private int stock;          // Encapsulation: private field

    public product(String productId, String productName, String category, String brand, String size, int price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.brand = brand;
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    // Getter/Setter pairs: safe access to private details
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Object[] toRow() {
        // Simple abstraction: convert this product to a table row for the UI
        return new Object[]{productId, productName, category, brand, size, String.valueOf(price), String.valueOf(stock)};
    }
}
