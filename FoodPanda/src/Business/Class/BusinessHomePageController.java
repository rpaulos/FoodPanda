package Business.Class;

import java.io.IOException;

import Business.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BusinessHomePageController {

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

    @FXML
    public void toAddAProductHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Business/FXML/BusinessAddProduct.fxml");
    }
    
}