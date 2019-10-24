package controller;

import access.AccessCategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Category;
import org.jetbrains.annotations.Contract;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public final class ControllerFood implements Initializable {
    private ArrayList<Category> categories;

    @FXML
    private VBox container;

    @FXML
    private HBox categoryPane;

    @Contract(pure = true)
    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setTopAnchor(container, (double) 0);
        AnchorPane.setRightAnchor(container, (double) 0);
        AnchorPane.setBottomAnchor(container, (double) 0);
        AnchorPane.setLeftAnchor(container, (double) 0);

        categories = AccessCategory.getInstance().load();
        categories.forEach(category -> {
            ImageView imageView = new ImageView("/asset/food/1.png");

            Button button = new Button(category.getName(), );
            button.setFont(new Font(14));
            button.setTextAlignment(TextAlignment.CENTER);
            button.setPrefSize(100, 150);
            categoryPane.getChildren().add(button);
        });


    }
}
