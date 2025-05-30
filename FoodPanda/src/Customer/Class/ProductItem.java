package Customer.Class;

public class ProductItem {
    private String product_name;
    private String product_price;
    private String product_description;
    private String product_image_path;

    public ProductItem(String product_name, String product_price, String product_description, String product_image_path) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_image_path = product_image_path;
    }

    public String getName() {
        return product_name;
    }

    public String getPrice() {
        return product_price;
    }

    public String getDescription() {
        return product_description;
    }

    public String getImagePath() {
        return product_image_path;
    }

}