package Customer.Class;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class CheckoutController {

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_checkout;

    @FXML
    private Label lbl_deliveryAddress;

    @FXML
    private Label lbl_price;

    @FXML
    private Label lbl_restaurantName;

    @FXML
    private RadioButton rb_cash;

    @FXML
    private RadioButton rb_pandapay;

}
