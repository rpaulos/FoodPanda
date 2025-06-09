package Customer.Class;

import Customer.CustomerDatabaseHandler;
import Customer.CustomerSession;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Customer.SwitchScene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CartCardController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_deduct;

    @FXML
    private ImageView img_product_image;

    @FXML
    private Text lbl_productDescription;

    @FXML
    private Label lbl_productName;

    @FXML
    private Label lbl_quantity;

    private String productID;
    private int quantity;
    private String customerID = CustomerSession.getCustomerID();

    public void setData(String productID, String productName, String productPrice, String productDesc, String productQuantity) {
        this.productID = productID;
        this.quantity = Integer.parseInt(productQuantity);
        this.customerID = CustomerSession.getCustomerID();

        lbl_productName.setText(productName);
        lbl_productDescription.setText(productDesc);
        lbl_quantity.setText(productQuantity + "×");

        String imagePath = "C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Products/" + productID + ".png";
        File imageFile = new File(imagePath);

        Image productImage;
        if (imageFile.exists()) {
            productImage = new Image(imageFile.toURI().toString());
        } else {
            // Fallback image path
            productImage = new Image("file:C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Products/default_product.png");
        }

        img_product_image.setImage(productImage);
    }

    @FXML
    void addQuantity(ActionEvent event) throws IOException {
        System.out.println("Add button clicked");

        String availableStock = CustomerDatabaseHandler.getProductStock(productID);

        if (quantity < Integer.parseInt(availableStock)) {
            quantity++;
            lbl_quantity.setText(quantity + "×");
        } else { 
            System.out.println("Maximum quantity reached");
        }

        CustomerDatabaseHandler.updateCartItemQuantity(customerID, productID, quantity);
        System.out.println("Quantity updated to: " + quantity);

        SwitchScene switchScene = new SwitchScene();
        switchScene.switchScene(event, "/Customer/FXML/Cart.fxml");
    }

    @FXML
    void deductQuantity(ActionEvent event) throws IOException {
        System.out.println("Deduct button clicked");

        quantity = Integer.parseInt(lbl_quantity.getText().replace("×", "").trim());

        if (quantity <= 1) {
            CustomerDatabaseHandler.removeCartItem(customerID, productID);
            System.out.println("Item removed from cart");
        } else {
            quantity--;
            lbl_quantity.setText(quantity + "×");
        }

        CustomerDatabaseHandler.updateCartItemQuantity(customerID, productID, quantity);
        System.out.println("Quantity updated to: " + quantity);

        SwitchScene switchScene = new SwitchScene();
        switchScene.switchScene(event, "/Customer/FXML/Cart.fxml");
    }

}
