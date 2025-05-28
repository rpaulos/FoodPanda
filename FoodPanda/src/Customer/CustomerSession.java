package Customer;

public class CustomerSession {
    private static String email;

    public static void  SetEmail(String email) {
        CustomerSession.email = email;
    }

    public static String GetEmail() {
        return email;
    }

    public static void ClearSession() {
        email = null;
    }

}
