package Customer;

public class CustomerSession {
    private static String email;

    public static void  setEmail(String email) {
        CustomerSession.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static void clearSession() {
        email = null;
    }

}
