package net.javaguides.productmanagement.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.productmanagement.model.Product;

public class ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/produit"; // Fixed MySQL port
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Corrected SQL Queries
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (productName, description, quantity, prixUnitaire, category) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT id, productName, description, quantity, prixUnitaire, category FROM Product WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT id, productName, description, quantity, prixUnitaire, category FROM Product";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM Product WHERE id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET productName = ?, description = ?, quantity = ?, prixUnitaire = ?, category = ? WHERE id = ?;";

    // Database connection method
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Corrected MySQL driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to fetch all products
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("productName");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("prixUnitaire");
                String category = rs.getString("category");

                products.add(new Product(id, name, description, quantity, prixUnitaire, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    // Helper method to handle SQL exceptions
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    // Constructor
    public ProductDAO() {}

    // Main method for testing
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Connection conn = dao.getConnection();

        if (conn != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
