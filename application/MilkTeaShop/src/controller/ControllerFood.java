package controller;

import access.AccessCategory;
import access.AccessFood;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.SecondaryStage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller food.
 */
public final class ControllerFood implements Initializable {
    @FXML
    private VBox container;

    @FXML
    private HBox categoryPane;

    @FXML
    private FlowPane foodPane;

    @NotNull
    private Button createButton(String name, String urlImage) {
        ImageView imageView = new ImageView(urlImage);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Button button = new Button(name, imageView);
        button.setFont(new Font(14));
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefHeight(150);
        button.setContentDisplay(ContentDisplay.TOP);
        if (button.getWidth() < 150) button.setPrefWidth(150);
        return button;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setTopAnchor(container, (double) 0);
        AnchorPane.setRightAnchor(container, (double) 0);
        AnchorPane.setBottomAnchor(container, (double) 0);
        AnchorPane.setLeftAnchor(container, (double) 0);

        AccessCategory.getInstance().getList().forEach(category -> {
            Button categoryButton = createButton(category.getName(), "/asset/food/1.png");
            categoryButton.setOnAction(event -> {
                SecondaryStage.getInstance().getStage().getScene().setCursor(Cursor.WAIT);
                foodPane.getChildren().clear();
                AccessFood.getInstance().getList(category.getId()).forEach(food -> {
                    Button foodButton = createButton(food.getName(), "/asset/food/" + food.getId() + ".png");
                    foodPane.getChildren().add(foodButton);
                });
                SecondaryStage.getInstance().getStage().getScene().setCursor(Cursor.DEFAULT);
            });
            categoryPane.getChildren().add(categoryButton);
        });

    }
}
