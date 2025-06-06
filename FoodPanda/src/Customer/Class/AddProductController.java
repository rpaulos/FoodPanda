package Customer.Class;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import Customer.SwitchScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.lang.classfile.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import Customer.CustomerSession;
import Customer.CustomerDatabaseHandler;

public class AddProductController {

    @FXML
    private Button btn_BuyNow;

    @FXML
    private Button btn_account;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_addToBasket;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_food;

    @FXML
    private Button btn_grocery;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_subtract;

    @FXML
    private Label lbl_price;

    @FXML
    private Label lbl_product_name;

    @FXML
    private TextField tf_quantity;

    @FXML
    private Text txt_desc;

    @FXML
    private ImageView img_product_header;

    public static String myProductID;

    @FXML
    void backToRestaurant(ActionEvent event) throws IOException {
        SwitchScene.switchScene(event, "/Customer/FXML/RestaurantProducts.fxml");
    }

    public void initialize() {
        setAddProductData();
    }

    public void setAddProductData() {
        String productID = myProductID;
        
        String productName = CustomerDatabaseHandler.getProductName(productID);
        String productPrice = CustomerDatabaseHandler.getProductPrice(productID);
        String productDescription = CustomerDatabaseHandler.getProductDescription(productID);
        String productQuantity = CustomerDatabaseHandler.getProductQuantity(productID);

        // Set the product header image
        String headerPath = "C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Product Header/" + productID + ".png";
        
        File imageFile = new File(headerPath);
        Image headerImage;

        if (imageFile.exists()) {
            headerImage = new Image(imageFile.toURI().toString());
        } else {
            // Fallback image path
            headerImage = new Image("file:C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Product Header/default_product_header.png");
        }
        
        img_product_header.setImage(headerImage);

        // Set the product details in the UI
        lbl_product_name.setText(productName);
        lbl_price.setText(productPrice);
        txt_desc.setText(productDescription);
        //tf_quantity.setText(productQuantity);
    }

}
