package net.javaguides.productmanagement.model;

public class Product {
    private int id;
    private String productName;
    private String description;
    private int quantity;
    private double prixUnitaire;
    private String category;

    // Default Constructor
    public Product() {
    }

    // Constructor for adding new products (without ID)
    public Product(String productName, String description, int quantity, double prixUnitaire, String category) {
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prixUnitaire;
        this.category = category;
    }

    // Constructor for existing products (with ID)
    public Product(int id, String productName, String description, int quantity, double prixUnitaire, String category) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prixUnitaire;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
