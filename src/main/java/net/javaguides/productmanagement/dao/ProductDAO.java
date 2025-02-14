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
    private static final String URL = "jdbc:mysql://localhost:3306/produit"; // Ensure correct MySQL port
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // SQL Queries
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (productName, description, quantity, prixUnitaire, category) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT id, productName, description, quantity, prixUnitaire, category FROM Product WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT id, productName, description, quantity, prixUnitaire, category FROM Product";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM Product WHERE id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET productName = ?, description = ?, quantity = ?, prixUnitaire = ?, category = ? WHERE id = ?;";

    // Database connection method
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use correct MySQL driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Add a new product
    public void ajoutProduct(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity()); // Ensure int type
            preparedStatement.setDouble(4, product.getPrixUnitaire()); // Ensure double type
            preparedStatement.setString(5, product.getCategory());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Fetch all products
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("productName");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                double prixUnitaire = resultSet.getDouble("prixUnitaire");
                String category = resultSet.getString("category");

                products.add(new Product(id, name, description, quantity, prixUnitaire, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    // Delete a product by ID
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    // Update product details
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setDouble(4, product.getPrixUnitaire());
            statement.setString(5, product.getCategory());
            statement.setInt(6, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Helper method to print SQL exceptions
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
