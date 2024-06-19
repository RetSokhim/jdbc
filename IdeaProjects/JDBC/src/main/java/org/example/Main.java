package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "092367169";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Main main = new Main();
        main.displayProducts();
    }
    //Dear teacher ,Please create table and add static data in resources/sql/schema.sql
    public void displayProducts() {
        String sqlQuery = "SELECT * FROM product";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            System.out.println("Product List:");
            System.out.println("ID | Name       | Price per Unit | Active for Sell");
            System.out.println("-----------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerUnit = rs.getDouble("price_per_unit");
                boolean activeForSell = rs.getBoolean("active_for_sell");

                System.out.printf("%2d | %-10s | %-14.2f | %-14b\n", id, name, pricePerUnit, activeForSell);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing SQL query", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
