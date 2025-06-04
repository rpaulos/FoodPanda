package Business;

import Database.DatabaseCredentials;
import java.sql.*;

public class BusinessDatabaseHandler {
    private static BusinessDatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static String dburl = DatabaseCredentials.ignoreDburl;
    public static String userName = DatabaseCredentials.ignoreUserName;
    public static String password = DatabaseCredentials.ignorePassword;

    public static BusinessDatabaseHandler getInstance() {
        if (handler == null) {
            handler = new BusinessDatabaseHandler();
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

        // Login validation of email and password of the business owner
    public static boolean validateBusinessOwnerLogin(String oemail, String opassword) {
        getInstance();

        String query = "SELECT * FROM business_owner WHERE owner_email = ? AND owner_password = ?";
    
        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, oemail);
            pstmt.setString(2, opassword);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
             return true;
            }

        } catch (SQLException e) {
            System.out.println("Error validating business owner credentials: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Checks if a business owner email exists in the database
    public static boolean emailExists(String email) {
        getInstance();

        String query = "SELECT * FROM business_owner WHERE owner_email = ?";
    
        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
             return true;
            }

        } catch (SQLException e) {
            System.out.println("Error checking business owner email: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Checks if company name exists in the database
    public static boolean companyNameExists(String companyName) {
        getInstance();

        String query = "SELECT * FROM restaurant WHERE restaurant_name = ?";

        try (Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, companyName);

            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
             return true;
            }

        } catch (SQLException e) {
            System.out.println("Error checking business owner company name: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Restaurant Location ID generator
    public static String generateRestaurantLocationID(String address, String city, String zip) {
        getInstance();

        String cityCode = city.trim().toUpperCase().replaceAll("\\s+", "").substring(0, 3);
        String prefix = "RL" + cityCode + "_";

        String query = "SELECT restaurant_location_ID FROM restaurant_location WHERE restaurant_location_ID LIKE ? ORDER BY restaurant_location_ID DESC LIMIT 1";

        try(Connection conn = getDBConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prefix + "%");
            ResultSet result = pstmt.executeQuery();

            int nextNumber = 1;

            if (result.next()) {
                String lastID = result.getString("restaurant_location_ID");
                String[] parts = lastID.split("_");
                if (parts.length == 2) {
                    nextNumber = Integer.parseInt(parts[1]) + 1;
                }
            }

            return String.format("%s%05d", prefix, nextNumber);

        } catch (SQLException e) {
            System.out.println("Error getting restaurant location ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Insert restaurant location into the database
    public static void insertRestaurantLocation(String restaurantLocationID, String city, String address, String zip) {
        getInstance();

        String query = "INSERT INTO restaurant_location (restaurant_location_ID, city, address, zip_code) VALUES (?, ?, ?, ?)";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, restaurantLocationID);
            pstmt.setString(2, city);
            pstmt.setString(3, address);
            pstmt.setString(4, zip);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting restaurant location: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Generate restaurant ID
    public static String generateRestaurantID(String restaurantLocationID) {
        getInstance();

        String prefix = "R_" + restaurantLocationID + "_";

        String query = "SELECT restaurant_ID FROM restaurant WHERE restaurant_ID LIKE ? ORDER BY restaurant_ID DESC LIMIT 1";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prefix + "%");
            ResultSet result = pstmt.executeQuery();

            int nextNumber = 1;

            if (result.next()) {
                String lastID = result.getString("restaurant_ID");
                String[] parts = lastID.split("_");
                if (parts.length == 3) {
                    nextNumber = Integer.parseInt(parts[2]) + 1;
                }
            }

            return String.format("%s%03d", prefix, nextNumber);

        } catch (SQLException e) {
            System.out.println("Error generating restaurant ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Insert restaurant into the database
    public static void insertRestaurant(String restaurantID, String restaurantLocationID, String companyName, String headerPath) {
        getInstance();

        String query = "INSERT INTO restaurant (restaurant_ID, restaurant_location_ID, restaurant_name, restaurant_header_path) VALUES (?, ?, ?, ?)";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, restaurantID);
            pstmt.setString(2, restaurantLocationID);
            pstmt.setString(3, companyName);
            pstmt.setString(4, headerPath);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting restaurant: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Generate business owner ID
    public static String generateBusinessOwnerID(String restaurantID) {
        getInstance();

        String prefix = "BO_" + restaurantID + "_";

        String query = "SELECT business_owner_ID FROM business_owner WHERE business_owner_ID LIKE ? ORDER BY business_owner_ID DESC LIMIT 1";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prefix + "%");
            ResultSet result = pstmt.executeQuery();

            int nextNumber = 1;

            if (result.next()) {
                String lastID = result.getString("owner_ID");
                String[] parts = lastID.split("_");
                if (parts.length == 3) {
                    nextNumber = Integer.parseInt(parts[2]) + 1;
                }
            }

            return String.format("%s%03d", prefix, nextNumber);

        } catch (SQLException e) {
            System.out.println("Error generating business owner ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Insert business owner into the database
    public static void insertBusinessOwner(String ownerID, String restaurantID, String firstName, String lastName, String email, String password) {
        getInstance();

        String query = "INSERT INTO business_owner (business_owner_ID, restaurant_ID, owner_first_name, owner_last_name, owner_email, owner_password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, ownerID);
            pstmt.setString(2, restaurantID);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, email);
            pstmt.setString(6, password);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting business owner: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}