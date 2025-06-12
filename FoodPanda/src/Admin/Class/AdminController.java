package Admin.Class;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdminController {

    @FXML
    private Button btnCD_search;

    @FXML
    private Button btnCD_update;

    @FXML
    private Button btnOD_delete;

    @FXML
    private Button btnOD_search;

    @FXML
    private Button btnPD_delete;

    @FXML
    private Button bttOD_search;

    @FXML
    private TableColumn<?, ?> colCD_amount;

    @FXML
    private TableColumn<?, ?> colCD_orderID;

    @FXML
    private TableColumn<?, ?> coldCD_restaurantID;

    @FXML
    private AnchorPane gridView;

    @FXML
    private Label lblCD_customerID;

    @FXML
    private Label lblCD_email;

    @FXML
    private Label lblCD_name;

    @FXML
    private Label lblCD_phoneNumber;

    @FXML
    private Label lblCD_totalSpent;

    @FXML
    private Label lblOD_businessName;

    @FXML
    private Label lblOD_businessOwnerEmail;

    @FXML
    private Label lblOD_businessOwnerID;

    @FXML
    private Label lblOD_businessOwnerName;

    @FXML
    private Label lblOD_totalEarned;

    @FXML
    private TextField tfCD_customerID;

    @FXML
    private TextField tfCD_firstName;

    @FXML
    private TextField tfCD_lastName;

    @FXML
    private TextField tfCD_phoneNumber;

    @FXML
    private TextField tfOD_businessOwnerID;

    @FXML
    private TextField tfOD_firstName;

    @FXML
    private TextField tfOD_lastName;

    @FXML
    private TextField tfOD_restaurantID;

    @FXML
    private TextField tfPD_productID;

    @FXML
    private TextField tfPD_restaurantID;

}
