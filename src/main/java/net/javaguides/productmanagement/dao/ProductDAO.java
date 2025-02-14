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


public class ProductDAO  {
    private static final String URL = "jdbc:mysql://localhost:8083/produit";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (productName, description, quantity, prixUniaire, category) VALUES " +
            " (?, ?, ?);";


    private static final String SELECT_USER_BY_ID = "select productName,description,quantity,prixUniaire,category from Product where id =?";
    private static final String SELECT_ALL_USERS = "select * from Product";
    private static final String DELETE_USERS_SQL = "delete from Product where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set productNAme = ?,description= ?, quantity =?, proxUnitaire =?, category =? where id = ?;";

    public ProductDAO() {}

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();
        Connection conn = null;
        Statement stmt = null;







}


}
