package Customer.Class;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProductCardController {
    
    @FXML
    private ImageView img_product_image;

    @FXML
    private Label lbl_price;

    @FXML
    private Label lbl_product_name;

    @FXML
    private Text txt_desc;

    public void setData(String name, String price, String description, String imagePath) {
        // Set the product name
        lbl_product_name.setText(name);

        // Set the product price
        lbl_price.setText(price);

        // Set the product description
        txt_desc.setText(description);

        // Set the product image
        File imageFile = new File(imagePath);
        Image productImage;

        if (imageFile.exists()) {
            productImage = new Image(imageFile.toURI().toString());
        } else {
            // Fallback image path
            productImage = new Image("file:C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Products/default_product.png");
        }

        // For testing purposes
        productImage = new Image("file:C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Products/default_product.png");

        img_product_image.setImage(productImage);
    }
}