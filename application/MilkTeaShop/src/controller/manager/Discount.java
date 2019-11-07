package controller.manager;

import app.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * The type Discount.
 */
public class Discount implements Controller {

    @FXML
    private TextField name;

    @FXML
    private TextField sale;

    @FXML
    private void submit(ActionEvent event){
//        String name = fixString(getName());
//        StringBuilder discountName = new StringBuilder();
//        for(String s : name.split("")){
//            discountName.append(s.charAt(0));
//        }
//        Discount discount = AccessDiscount.getInstance().insert(name, sale);
    }
}
