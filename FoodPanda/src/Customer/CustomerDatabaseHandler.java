package Customer;

import Database.DatabaseCredentials;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import Customer.Class.ProductCardController;
import Customer.Class.ProductItem;
import Customer.Class.RestaurantItem;
import Customer.Class.RestaurantsItem;

public class CustomerDatabaseHandler {
    private static CustomerDatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static String dburl = DatabaseCredentials.ignoreDburl;
    public static String userName = DatabaseCredentials.ignoreUserName;
    public static String password = DatabaseCredentials.ignorePassword;

    public static CustomerDatabaseHandler getInstance() {
        if (handler == null) {
            handler = new CustomerDatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection()
    {
        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection(dburl, userName, password);

        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }

    public int execUpdateQuery(String query) {
        int affectedRows = 0;
        try {
            stmt = getDBConnection().createStatement();
            affectedRows = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Not working");
        }
        return affectedRows;
    }

    // Login validation of email and password of the customer
    public static boolean validateLoginCredentials(String email, String password) {
        getInstance();

        String query = "SELECT * FROM customer WHERE customer_email = ? AND customer_password = ?";
        
        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error validating credentials: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Checks if email exists in the database
    public static boolean emailExists(String email) {
        getInstance();

        String query = "SELECT * FROM customer WHERE customer_email = ?";
        
        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error validating credentials: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Checks if phone number exists in the database
    public static boolean phoneNumberExists(String phoneNumber) {
        getInstance();

        String query = "SELECT * FROM customer WHERE customer_phone_number = ?";
        
        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, phoneNumber);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error validating credentials: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Customer ID generator
    public static String generateCustomerID() {
        getInstance();

        String currentYear = String.valueOf(Year.now().getValue());
        String prefix = currentYear + "-";
        String query = "SELECT customer_id FROM customer WHERE customer_id LIKE ? ORDER BY customer_id DESC LIMIT 1";

        try(Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prefix + "%");
            ResultSet result = pstmt.executeQuery();

            int nextNumber = 1;

            if (result.next()) {
                String lastID = result.getString("customer_id");
                String numberPart = lastID.split("-")[1];
                nextNumber = Integer.parseInt(numberPart) + 1;
            }

            return String.format("%s%05d", prefix, nextNumber);

        } catch (SQLException e) {
            System.out.println("Error getting customer ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Customer Location ID generator
    public static String generateCustomerLocationID(String address, String city, String zip) {
        getInstance();

        String cityCode = city.trim().toUpperCase().replaceAll("\\s+", "").substring(0, 3);
        String prefix = cityCode + "_";

        String query = "SELECT customer_location_ID FROM customer_location WHERE customer_location_ID LIKE ? ORDER BY customer_location_ID DESC LIMIT 1";

        try(Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prefix + "%");
            ResultSet result = pstmt.executeQuery();

            int nextNumber = 1;

            if (result.next()) {
                String lastID = result.getString("customer_location_ID");
                String[] parts = lastID.split("_");
                if (parts.length == 2) {
                    nextNumber = Integer.parseInt(parts[1]) + 1;
                }
            }

            return String.format("%s%05d", prefix, nextNumber);

        } catch (SQLException e) {
            System.out.println("Error getting customer ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Insert customer location ID
    public static boolean insertCustomerLocation(String customerLocationID, String city, String address, String ZIP) {
        getInstance();

        String query = "INSERT INTO customer_location (customer_location_ID, city, address, zip_code) VALUES (?, ?, ?, ?)";

        try(Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customerLocationID);
            pstmt.setString(2, city);
            pstmt.setString(3, address);
            pstmt.setString(4, ZIP);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting customer location: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Generate the customer ID based on the school the customer is enrolled
    public static String getUniversityID(String selectedSchool) {
        return switch (selectedSchool) {
            case "University of Santo Tomas" -> "UST-0001";
            case "National University - Manila" -> "NU-0002";
            case "Far Eastern University" -> "FEU-0003";
            case "Centro Escolar University" -> "CEU-0004";
            default -> "UNKNOWN";
        };
    }

    // Create the account
    public static boolean insertCustomer(String email, String password, String firstName, String lastName, String phoneNumber, String selectedCity) {
        getInstance();

        String customerID = generateCustomerID();
        //String universityID = getUniversityID(selectedSchool);

        String query = "INSERT INTO customer (customer_id, customer_email, customer_password, customer_first_name, customer_last_name, customer_phone_number, customer_location_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customerID);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, firstName);
            pstmt.setString(5, lastName);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, selectedCity);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static String getFirstName(String email) {
        getInstance();

        String query = "SELECT customer_first_name FROM customer WHERE customer_email = ?";
        
        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("customer_first_name");
            }

        } catch (SQLException e) {
            System.out.println("Error getting first name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getLastName(String email) {
        getInstance();

        String query = "SELECT customer_last_name FROM customer WHERE customer_email = ?";
        
        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("customer_last_name");
            }

        } catch (SQLException e) {
            System.out.println("Error getting last name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static List<RestaurantItem> getRestaurantItems() {
        List<RestaurantItem> restaurantItems = new ArrayList<>();

        String query = "SELECT r.restaurant_ID, r.restaurant_name, r.restaurant_header_path, l.address, r.price_range " +
                       "FROM Restaurant r " +
                       "JOIN restaurant_location l ON r.restaurant_location_ID = l.restaurant_location_ID";

        try (Connection conn = DriverManager.getConnection(dburl, userName, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("restaurant_ID");
                String name = rs.getString("restaurant_name");
                String headerPath = rs.getString("restaurant_header_path");
                String address = rs.getString("address");
                String priceRange = rs.getString("price_range");
                restaurantItems.add(new RestaurantItem(name, address, headerPath, id, priceRange));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantItems;
    }

    public static List<RestaurantItem> getFilteredRestaurants(String priceRange) {
        List<RestaurantItem> restaurantItems = new ArrayList<>();

        String query = "SELECT r.restaurant_ID, r.restaurant_name, r.restaurant_header_path, l.address, r.price_range " +
               "FROM Restaurant r " +
               "JOIN restaurant_location l ON r.restaurant_location_ID = l.restaurant_location_ID " + 
               "WHERE r.price_range = ?";

        try (Connection conn = DriverManager.getConnection(dburl, userName, password);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, priceRange); 

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("restaurant_ID");
                    String name = rs.getString("restaurant_name");
                    String headerPath = rs.getString("restaurant_header_path");
                    String address = rs.getString("address");
                    String range = rs.getString("price_range"); 
                    restaurantItems.add(new RestaurantItem(name, address, headerPath, id, range));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantItems;
    }

    public static List<ProductItem> getProductItems(String restaurant_ID) {
        List<ProductItem> productItems = new ArrayList<>();

        String query = "SELECT * FROM product where restaurant_ID = ?";

        try (Connection conn = DriverManager.getConnection(dburl, userName, password);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, restaurant_ID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("product_ID");
                    String name = rs.getString("product_name");
                    String price = String.format("%.2f", rs.getDouble("product_price"));
                    String description = rs.getString("product_desc");
                    String imagePath = rs.getString("product_image_path");
                    productItems.add(new ProductItem(id, name, price, description, imagePath));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productItems;
    }
    

    public static String getCustomerLocationID(String email) {
        getInstance();

        String query = "SELECT customer_location_ID FROM customer WHERE customer_email = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("customer_location_ID");
            }

        } catch (SQLException e) {
            System.out.println("Error getting customer location ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    

    public static String getAddress(String customerLocationID) {
        getInstance();

        String query = "SELECT address FROM customer_location WHERE customer_location_ID = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customerLocationID);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("address");
            }

        } catch (SQLException e) {
            System.out.println("Error getting address: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getRestaurantName(String restaurantID) {
        getInstance();

        String query = "SELECT restaurant_name FROM Restaurant WHERE restaurant_ID = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, restaurantID);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("restaurant_name");
            }

        } catch (SQLException e) {
            System.out.println("Error getting restaurant name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getProductName(String productID) {
        getInstance();

        String query = "SELECT product_name FROM Product WHERE product_ID = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productID);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("product_name");
            }

        } catch (SQLException e) {
            System.out.println("Error getting product name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getProductPrice(String productID) {
        getInstance();

        String query = "SELECT product_price FROM Product WHERE product_ID = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productID);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return String.format("%.2f", result.getDouble("product_price"));
            }

        } catch (SQLException e) {
            System.out.println("Error getting product price: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getProductDescription(String productID) {
        getInstance();

        String query = "SELECT product_desc FROM Product WHERE product_ID = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productID);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return result.getString("product_desc");
            }

        } catch (SQLException e) {
            System.out.println("Error getting product description: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getProductQuantity(String productID) {
        getInstance();

        String query = "SELECT product_quantity FROM Product WHERE product_ID = ?";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productID);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return String.valueOf(result.getInt("product_quantity"));
            }

        } catch (SQLException e) {
            System.out.println("Error getting product quantity: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
