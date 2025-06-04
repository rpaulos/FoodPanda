package Customer.Class;

import Customer.CustomerDatabaseHandler;
import java.io.IOException;
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
