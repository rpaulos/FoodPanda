package Business.Class;

import java.io.IOException;

import Business.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BusinessProfileController {

    @FXML
    private Button btn_businessHome;

    @FXML
    private Button btn_businessLogout;

    @FXML
    private Button btn_businessPay;

    @FXML
    private Button btn_businessPrices;

    @FXML
    private Button btn_businessProducts;

    @FXML
    private Button btn_businessStocks;

    @FXML
    private Hyperlink hplnk_helpCenter;

    @FXML
    private Hyperlink hplnk_termPolicies;

    @FXML
    private Label lbl_ownerName;

    private Stage stage;
    private Scene scene; 
    private Parent root;

    @FXML
    public void toMoveToHomeHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Business/FXML/BusinessHomePage.fxml");
    }
    
    @FXML
    public void toMoveToLandingPageHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/LandingPage.fxml");
    }
}
