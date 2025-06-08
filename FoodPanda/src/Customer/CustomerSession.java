package Customer;

public class CustomerSession {
    private static String email;
    private static String customerID;
    private static String address;
    private static String selectedRestaurantID;
    private static String selectedProductID;

    public static void setEmail(String email) {
        CustomerSession.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static void setCustomerID(String customerID) {
        CustomerSession.customerID = customerID;
    }

    public static String getCustomerID() {
        return customerID;
    }

    public static void setAddress(String address) {
        CustomerSession.address = address;
    }

    public static String getAddress() {
        return address;
    }

    public static void clearAddress() {
        address = null;
    }

    public static void setSelectedRestaurantID(String id) {
        CustomerSession.selectedRestaurantID = id;
    }

    public static String getSelectedRestaurantID() {
        return selectedRestaurantID;
    }

    public static void clearSelectedRestaurantID() {
        selectedRestaurantID = null;
    }

    public static void setSelectedProductID(String id) {
        CustomerSession.selectedProductID = id;
    }

    public static String getSelectedProductID() {
        return selectedProductID;
    }

    public static void clearSelectedProductID() {
        selectedProductID = null;
    }

    public static void clearSession() {
        email = null;
        selectedRestaurantID = null;
        selectedProductID = null;
    }
}
