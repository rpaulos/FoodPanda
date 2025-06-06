package Customer;

public class CustomerSession {
    private static String email;
    private static String address;
    private static String selectedRestaurantID;

    public static void setEmail(String email) {
        CustomerSession.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static void clearSession() {
        email = null;
        selectedRestaurantID = null;
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
}
