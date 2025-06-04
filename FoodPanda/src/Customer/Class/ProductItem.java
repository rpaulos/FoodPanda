package Customer.Class;

public class ProductItem {
    private String name;
    private String price;
    private String description;
    private String imagePath;

    public ProductItem(String name, String price, String description, String imagePath) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
