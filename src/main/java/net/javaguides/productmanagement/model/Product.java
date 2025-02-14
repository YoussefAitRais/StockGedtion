package net.javaguides.productmanagement.model;



public class Product {
    protected int id;
    protected String productNAme;
    protected String description;
    protected String quantity;
    protected String prixUnitaire;
    protected String category;

    public Product() {}

    public Product(String productNAme, String description, String quantity, String prix_unitaire, String category ) {
        super();
        this.productNAme  = productNAme;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prix_unitaire;
        this.category = category;

    }