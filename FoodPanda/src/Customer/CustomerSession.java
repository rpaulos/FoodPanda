package Customer;

public class CustomerSession {
    private static String email;
    private static String address;

    public static void setEmail(String email) {
        CustomerSession.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static void clearSession() {
        email = null;
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
}
