package Customer.Class;

import Customer.CustomerDatabaseHandler;
import java.io.IOException;
import Customer.CustomerSession;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.util.Collections;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import Customer.SwitchScene;

public class RestaurantProductsController {

    @FXML
    private Button btn_Search;

    @FXML
    private Button btn_account;

    @FXML
    private Button btn_backToFood;

    @FXML
    private Button btn_food;

    @FXML
    private ImageView img_restaurantLogo;

    @FXML
    private Label lbl_restaurantName;

    @FXML
    private GridPane cardGrid;

    private Stage stage;
    private Scene scene; 
    private Parent root;

    @FXML
    public void toProfilePageHandler(ActionEvent event) throws IOException {
        SwitchScene.switchScene(event, "/Customer/FXML/Profile.fxml");
    }

    @FXML
    public void toHomeHandler(ActionEvent event) throws IOException {
        SwitchScene.switchScene(event, "/Customer/FXML/Home.fxml");
    }

    public void initialize() {
        setRestaurantDetails();
        cardGrid.getChildren().clear();
        cardGrid.getRowConstraints().clear();
        cardGrid.getColumnConstraints().clear();

        int columns = 1;
        int col = 0;
        int rows = 0;

        try {
            List<ProductItem> productItems = CustomerDatabaseHandler.getProductItems("R_RLMAN_00001_001");
            System.out.println("Loaded products:" + productItems);
            for (ProductItem product : productItems) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Customer/FXML/ProductCard.fxml"));
                AnchorPane card = fxmlLoader.load();

                ProductCardController controller = fxmlLoader.getController();
                controller.setData(product.getProductName(), product.getProductPrice(), product.getProductDescription(), product.getProductImagePath());

                cardGrid.add(card, col, rows);
                col++;
                if (col == columns) {
                    col = 0;
                    rows++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRestaurantDetails() {
        //String restaurantID = CustomerSession.getInstance().getRestaurantID();
        //String restaurantName = CustomerSession.getInstance().getRestaurantName();
        String restaurantName = CustomerDatabaseHandler.getRestaurantName("R_RLMAN_00001_001");
        //String restaurantLogoPath = CustomerSession.getInstance().getRestaurantLogoPath();

        lbl_restaurantName.setText(restaurantName);

        // Set the restaurant logo image
        // File imageFile = new File(restaurantLogoPath);
        // Image restaurantLogo;

        // if (imageFile.exists()) {
        //     restaurantLogo = new Image(imageFile.toURI().toString());
        // } else {
        //     // Fallback image path
        //     restaurantLogo = new Image("file:C:/Users/Rae/Desktop/FoodPanda/FoodPanda/src/User Interface/Restaurant Header/default.png");
        // }

        //img_restaurantLogo.setImage(restaurantLogo);
    }

    // public void initialize() {
    //     cardGrid.getChildren().clear();
    //     cardGrid.getRowConstraints().clear();
    //     cardGrid.getColumnConstraints().clear();

    //     int columns = 1;
    //     int col = 0;
    //     int rows = 0;

    //     try {
    //         List<ProductItem> productList = CustomerDatabaseHandler.getInstance().getProductsByRestaurant("Jollibee Legarda Bustillos"); // Replace with actual restaurant name
    //         for (ProductItem item : productList) {
    //             FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customer/FXML/ProductCard.fxml"));
    //             AnchorPane card = loader.load();

    //             ProductCardController productCardController = loader.getController();
    //             productCardController.setData(item.getName(), item.getPrice(), item.getDescription(), item.getImagePath());

    //             cardGrid.add(card, col, rows);
    //             col++;

    //         if (col == columns) {
    //             col = 0;
    //             rows++;
    //         }
        
    //     }

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
