package Customer.Class;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardController {

    @FXML
    private Label lbl_restaurantName;

    @FXML
    private Label lbl_restaurantLoc;

    @FXML
    private Label lbl_price;

    @FXML
    private ImageView img_header;

    // public void setData(String name, String price, String restaurant) {
    //     lbl_foodName.setText(name);
    //     lbl_price.setText("₱" + price);
    //     lbl_restaurantPlaceholder.setText(restaurant);
    // }

    public void setData(String name, String address, String headerPath) {
        // Set the restaurant name
        lbl_restaurantName.setText(name);

        // Set the restaurant address
        lbl_restaurantLoc.setText(address);
        
        // Set the restaurant header image
        File imageFile = new File(headerPath);
        Image headerImage;

        if (imageFile.exists()) {
            headerImage = new Image(imageFile.toURI().toString());
        } else {
            // Fallback image path (e.g., "default.png" in your project resources)
            headerImage = new Image("file:C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Header/default.png");
        }
        
        img_header.setImage(headerImage);

        // Set the price label to a placeholder value
        lbl_price.setText("PPP");
    }
}
