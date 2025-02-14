package net.javaguides.productmanagement.model;



public class Product {
    protected int id;
    protected String productNAme;
    protected String description;
    protected String quantity;
    protected String prixUnitaire;
    protected String category;

    public Product() {
    }

    public Product(String productNAme, String description, String quantity, String prix_unitaire, String category) {
        super();
        this.productNAme = productNAme;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prix_unitaire;
        this.category = category;

    }

    public Product(int id, String productNAme, String description, String quantity, String prix_unitaire, String category) {
        super();
        this.productNAme = productNAme;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prix_unitaire;
        this.category = category;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return productNAme;
    }

    public void setName(String name) {
        this.productNAme = productNAme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getQuantity(String quantity) {
        return quantity;
    }

    public void setQuantiy(String quantity) {
        this.quantity = quantity;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(String prix_unitaire) {
        this.prixUnitaire = prix_unitaire;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}



