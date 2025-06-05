package Business.Class;

import java.io.IOException;

import Business.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.util.ResourceBundle;
import Business.BusinessDatabaseHandler;
import Business.BusinessSession;

public class BusinessHomePageController implements Initializable{

    @FXML
    private Button btn_addProduct;

    @FXML
    private Button btn_homePage;

    @FXML
    private Button btn_homePageAccount;

    @FXML
    private Label lbl_restaurantName;

    private Stage stage;
    private Scene scene; 
    private Parent root;

    public static String restaurantID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        restaurantID = BusinessSession.getRestaurantID();
        setRestaurantName();
    }

    private void setRestaurantName() {
        String restaurantName = BusinessDatabaseHandler.getRestaurantName(restaurantID);
        lbl_restaurantName.setText(restaurantName);
        System.out.println("Restaurant Name: " + restaurantName);
        System.out.println("Restaurant ID: " + restaurantID);
    }

    @FXML
    public void toAddAProductHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Business/FXML/BusinessAddProduct.fxml");
    }
    
}