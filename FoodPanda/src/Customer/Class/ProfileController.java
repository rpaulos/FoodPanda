package Customer.Class;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import Customer.SwitchScene;

public class ProfileController {

    @FXML
    private Button btn_addresses;

    @FXML
    private Button btn_favourites;

    @FXML
    private Button btn_fpbusiness;

    @FXML
    private Button btn_helpcenter;

    @FXML
    private Button btn_orders;

    @FXML
    private Button btn_pandapay;

    @FXML
    private Button btn_grocery;

    @FXML
    private Button btn_food;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_termspolicies;

    private Stage stage;
    private Scene scene; 
    private Parent root;

    @FXML
    public void toHomeHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Customer/FXML/Home.fxml");
    }
}
