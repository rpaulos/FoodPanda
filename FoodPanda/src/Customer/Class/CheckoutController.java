package Customer.Class;

import Customer.CustomerDatabaseHandler;
import Customer.CustomerSession;
import java.awt.Desktop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import java.io.IOException;
import javafx.scene.control.ToggleGroup;

public class CheckoutController {

    @FXML
    private Button btn_20;

    @FXML
    private Button btn_40;

    @FXML
    private Button btn_5;

    @FXML
    private Button btn_60;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_checkout;

    @FXML
    private Button btn_notNow;

    @FXML
    private Label lbl_deliveryAddress;

    @FXML
    private Label lbl_restaurantName;

    @FXML
    private RadioButton rb_cash;

    @FXML
    private RadioButton rb_pandapay;

    private ToggleGroup paymentGroup;
    private Button previouslyClickedButton = null;
    private Float tipAmount = 0.0f;

    @FXML
    public void initialize() {
        paymentGroup = new ToggleGroup();
        rb_cash.setToggleGroup(paymentGroup);
        rb_pandapay.setToggleGroup(paymentGroup);
        setCheckoutDetails();
        
    }

    private void setCheckoutDetails() {
        // Set the restaurant name and delivery address from the session
        String restaurantName = CustomerDatabaseHandler.getRestaurantName(CustomerSession.getSelectedRestaurantID());
        lbl_restaurantName.setText(restaurantName);
        
        String deliveryAddress = CustomerSession.getAddress() + ", Manila, Philippines";
        lbl_deliveryAddress.setText(deliveryAddress);
    }
    
    @FXML
    void checkout(ActionEvent event) throws IOException {
        System.out.println(CustomerSession.getSelectedProductID());
        System.out.println(CustomerSession.getQuantity());
        System.out.println(tipAmount);

        // get the selected payment method
        // RadioButton selectedRadioButton = (RadioButton) paymentGroup.getSelectedToggle();  
        
        // check if current quantity is less than the quantity in the database
        
        // generate orderID

        // compute amount (product price * quantity + tipAmount)
            // if payment method is pandapay, add 2% to the amount
                // check if pandapay balance is sufficient
                    // if not sufficient, show error message and return
                    // if sufficient, deduct the amount from pandapay balance (excluding the tipAmount)
            
            // if payment method is cash, no additional charges

        // get customerID, restaurantID, and amount

        // insert orderID, customerID, restaurantID, amount to the database

        // switch scene to receipt page
    }

    @FXML
    void paymentMethod(Desktop.ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();
        String paymentMethod = selectedRadioButton.getText();
        System.out.println("Selected payment method: " + paymentMethod);
    }

    @FXML
    void tipYourRider(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        
        if (previouslyClickedButton != null) {
            previouslyClickedButton.setStyle("-fx-background-color: #f2f2f2; -fx-text-fill: #000000; -fx-background-radius: 25px; -fx-border-radius: 25px; -fx-border-color: #757575;");
        }

        clickedButton.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-background-radius: 25px; -fx-border-radius: 25px;");

        previouslyClickedButton = clickedButton;

        String buttonText = clickedButton.getText();

        if (clickedButton == btn_notNow) {
            System.out.println("No tip");
            tipAmount = 0.0f;
        } else {
            tipAmount = Float.parseFloat(buttonText);
            String formattedTip = String.format("%.2f", tipAmount);
            tipAmount = Float.parseFloat(formattedTip);
            System.out.println("Tip amount: " + formattedTip);
        }
        
    }

}
