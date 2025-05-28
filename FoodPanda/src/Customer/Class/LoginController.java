package Customer.Class;

import Customer.CustomerDatabaseHandler;
import Customer.CustomerSession;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;

import Customer.SwitchScene;

public class LoginController {

    @FXML
    private Button btn_create;

    @FXML
    private Button btn_submit;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_email;

    @FXML
    private Button btn_close;

    private Stage stage;
    private Scene scene; 
    private Parent root;

    @FXML
    public void toSignUpPageHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Customer/FXML/SignUp.fxml");

    }

    @FXML
    void toStartUpPageHandler(ActionEvent event) throws IOException{
        SwitchScene.switchScene(event, "/Customer/FXML/StartUp.fxml");

    }

    @FXML
    void toValidateLogin(ActionEvent event) throws IOException{

        String email = tf_email.getText();
        String password = pf_password.getText();

        if (CustomerDatabaseHandler.validateLoginCredentials(email, password)) {
            // Pop up message for successful login
            System.out.println("Succesful");

            // Store email in CustomerSession
            CustomerSession.SetEmail(email);

            SwitchScene.switchScene(event, "/Customer/FXML/Home.fxml");
        } else {
            System.out.println("Unsuccesful");
        }
    }

}
