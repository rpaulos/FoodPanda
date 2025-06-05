package Business.Class;

import java.io.File;
import java.io.IOException;

import Business.BusinessDatabaseHandler;
import Business.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Business.BusinessSession;

public class BusinessAddProductController {

    @FXML
    private Button btn_return;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_upload;

    @FXML
    private ImageView img_productImage;

    @FXML
    private TextField tf_productName;

    @FXML
    private TextField tf_productPrice;

    @FXML
    private TextField tf_productQuantity;

    @FXML
    private TextArea tfa_productDescription;

    private Stage stage;
    private Scene scene; 
    private Parent root;

    @FXML
    public void toReturntoHomePageAddHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Business/FXML/BusinessHomePage.fxml");
    }

    private File selectedImageFile;

    @FXML
    private void initialize() {
        btn_upload.setOnAction(e -> uploadImage());
        btn_save.setOnAction(e -> saveProduct());
    }

    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Product Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            selectedImageFile = file;
            Image image = new Image(file.toURI().toString());
            img_productImage.setImage(image);
        }
    }

    private void saveProduct() {
    String name = tf_productName.getText().trim();
    String desc = tfa_productDescription.getText().trim();
    String priceText = tf_productPrice.getText().trim();
    String qtyText = tf_productQuantity.getText().trim();
    String imagePath = (selectedImageFile != null) ? selectedImageFile.getAbsolutePath() : null;

    if (name.isEmpty() || priceText.isEmpty() || qtyText.isEmpty() || imagePath == null) {
        System.out.println("All fields and image must be filled.");
        return;
    }

    try {
        double price = Double.parseDouble(priceText);
        int quantity = Integer.parseInt(qtyText);
        String productID = BusinessDatabaseHandler.generateProductID();
        String restaurantID = BusinessSession.getRestaurantID();  // <-- replace with actual logged-in restaurant ID
        //String priceRangeID = "PR1";   // <-- you may want to select this dynamically in your UI

        boolean success = BusinessDatabaseHandler.insertProduct(
            productID,
            restaurantID,
            name,
            desc,
            quantity,
            price,
            imagePath
        );


        if (success) {
            System.out.println("Product saved successfully!");
        } else {
            System.out.println("Failed to save product.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Price and Quantity must be valid numbers.");
    }
    } 
}