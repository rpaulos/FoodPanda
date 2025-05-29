package Customer.Class;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardController {

    @FXML
    private Label lbl_restaurantName;

    @FXML
    private Label lbl_restaurantLoc;

    @FXML
    private Label lbl_price;

    // public void setData(String name, String price, String restaurant) {
    //     lbl_foodName.setText(name);
    //     lbl_price.setText("₱" + price);
    //     lbl_restaurantPlaceholder.setText(restaurant);
    // }

    public void setData(String name, String address) {
        lbl_restaurantName.setText(name);
        lbl_restaurantLoc.setText(address);
        lbl_price.setText("PPP");
    }
}
