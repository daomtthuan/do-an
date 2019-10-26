package controller.manager;

import access.AccessDiscount;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import library.Tool;

import static library.Tool.*;


/**
 * The type Discount.
 */
public final class Discount implements Controller {

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
