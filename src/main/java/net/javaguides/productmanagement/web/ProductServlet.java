package net.javaguides.productmanagement.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

package net.javaguides.productmanagement.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Model class for Product
class Product {
    private int id;
    private String productName;
    private String description;
    private int quantity;
    private double prixUnitaire;
    private String category;

    public Product() {}

    public Product(String productName, String description, int quantity, double prixUnitaire, String category) {
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prixUnitaire;
        this.category = category;
    }

    public Product(int id, String productName, String description, int quantity, double prixUnitaire, String category) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.prixUnitaire = prixUnitaire;
        this.category = category;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}

        // DAO class for Product
        class ProductDAO {
            private static final String URL = "jdbc:mysql://localhost:3306/produit";
            private static final String USER = "root";
            private static final String PASSWORD = "admin";

            private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (productName, description, quantity, prixUnitaire, category) VALUES (?, ?, ?, ?, ?);";
            private static final String SELECT_ALL_PRODUCTS = "SELECT id, productName, description, quantity, prixUnitaire, category FROM Product";

            // Database connection method
            protected Connection getConnection() {
                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return connection;
            }

            // Method to add a product
            public void ajoutProduct(Product product) throws SQLException {
                try (Connection connection = getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {

                    preparedStatement.setString(1, product.getProductName());
                    preparedStatement.setString(2, product.getDescription());
                    preparedStatement.setInt(3, product.getQuantity());
                    preparedStatement.setDouble(4, product.getPrixUnitaire());
                    preparedStatement.setString(5, product.getCategory());

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    printSQLException(e);
                }
            }

            // Method to fetch all products
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
        }

        // Servlet to Handle Product Requests
        @WebServlet("/productServlet")
        public class ProductServlet extends HttpServlet {
            private static final long serialVersionUID = 1L;
            private ProductDAO productDAO;

            @Override
            public void init() {
                productDAO = new ProductDAO(); // Initialize DAO
            }

            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
                List<Product> productList = productDAO.selectAllProducts();
                request.setAttribute("products", productList);
                RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
                rd.forward(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double prixUnitaire = Double.parseDouble(request.getParameter("prixUnitaire"));
                String category = request.getParameter("category");

                Product newProduct = new Product(name, description, quantity, prixUnitaire, category);

                try {
                    productDAO.ajoutProduct(newProduct);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("productServlet"); // Redirect to refresh product list
            }
        }


public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
        rd.forward(request, response);
    }
}

public void ajoutProduct(Product product) throws SQLException {
    System.out.println(INSERT_PRODUCT_SQL);
    // try-with-resources statement to auto-close the connection
    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {

        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setInt(3, product.getQuantity()); // Quantity is int
        preparedStatement.setDouble(4, product.getPrixUnitaire()); // Price is double
        preparedStatement.setString(5, product.getCategory());

        System.out.println(preparedStatement); // Debugging output
        preparedStatement.executeUpdate(); // Execute the query

    } catch (SQLException e) {
        printSQLException(e);
    }
}

