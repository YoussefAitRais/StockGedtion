package net.javaguides.productmanagement.dao;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO  {
    private static final String URL = "jdbc:mysql://localhost:8083/produit";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();
        Connection conn = null;
        Statement stmt = null;







}


}
