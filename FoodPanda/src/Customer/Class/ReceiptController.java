package Customer.Class;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

import Business.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import Customer.Class.CartItem;
import java.io.IOException;
import java.util.List;
import Customer.Class.CartCardController;
import Customer.CustomerDatabaseHandler;
import Customer.CustomerSession;

public class ReceiptController {

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_done;

    @FXML
    private GridPane cardGrid;

    @FXML
    private Label lbl_ETA;

    @FXML
    private Label lbl_deliveryFee;

    @FXML
    private Label lbl_orderAmount;

    @FXML
    private Label lbl_riderTip;

    @FXML
    private Label lbl_totalAmount;

    private String customerID = Customer.CustomerSession.getCustomerID();

    @FXML
    void initialize() {
        loadCartData();
        loadEstimatedTimeOfDelivery();
        setPaymentSummary();
    }

    private void loadCartData() {
        String totalPrice = Customer.CustomerDatabaseHandler.getTotalPrice(customerID);
        //lbl_price.setText(totalPrice);

        System.out.println("Total Price: " +totalPrice);

        String customerID = Customer.CustomerSession.getCustomerID();
        
        cardGrid.getChildren().clear();
        cardGrid.getRowConstraints().clear();
        cardGrid.getColumnConstraints().clear();

        int columns = 1;
        int col = 0;
        int rows = 0;

        try {
            List<CartItem> cartItems = Customer.CustomerDatabaseHandler.getCartItems(customerID);
            for (CartItem item : cartItems) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customer/FXML/CheckoutCard.fxml"));
                AnchorPane card = loader.load();

                CheckoutCardController controller = loader.getController();
                controller.setData(item.getProductID(), item.getProductName(), item.getProductPrice(), item.getProductDesc(), item.getProductQuantity());

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

    private void setPaymentSummary() {
        // get order amount from session
        // get the delivery fee based on the distance of restaurant to customer
        // get the rider tip from the session
        // add all to get the total amount

        String orderAmount = Customer.CustomerSession.getOrderAmount();
        String tip = Customer.CustomerSession.getTip();
        String totalAmount = Customer.CustomerSession.getTotalPrice();
        float deliveryFee = Customer.CustomerSession.getDeliveryFee();

        lbl_orderAmount.setText(orderAmount);
        lbl_riderTip.setText(tip);
        lbl_totalAmount.setText(totalAmount);
        lbl_deliveryFee.setText(String.format("%.2f", deliveryFee));

        //make a request to the google maps API to get the distance between the restaurant and customer
        // delivery fee is 20 per kilometer

    }

    @FXML
    void backHome(ActionEvent event) throws IOException {
        CustomerDatabaseHandler.clearCart(customerID);
        SwitchScene.switchScene(event, "/Customer/FXML/Home.fxml");
    }

    @FXML
    void loadEstimatedTimeOfDelivery() {
        lbl_ETA.setText(String.valueOf(CustomerSession.getDuration()));
    }

}
