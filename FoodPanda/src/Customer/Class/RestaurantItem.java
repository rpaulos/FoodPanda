package Customer.Class;

public class RestaurantItem {
    private String restaurant_name;
    private String restaurant_address;

    public RestaurantItem(String restaurant_name, String restaurant_address) {
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
    }

    public String getName() {
        return restaurant_name;
    }

    public String getAddress() {
        return restaurant_address;
    }
    
}
