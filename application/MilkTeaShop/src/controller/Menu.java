package controller;

import api.CategoryApi;
import api.FoodApi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public final class Menu implements Controller, Initializable {
    @FXML
    private VBox container;

    @FXML
    private HBox categoryPane;

    @FXML
    private FlowPane foodPane;

    @NotNull
    private Button createButton(String name, String urlImage) {
        Button button = new Button(name);
        button.getStyleClass().add("menuButton");
        button.setStyle("-fx-background-image: url('" + urlImage + "')");
        return button;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setTopAnchor(container, (double) 0);
        AnchorPane.setRightAnchor(container, (double) 0);
        AnchorPane.setBottomAnchor(container, (double) 0);
        AnchorPane.setLeftAnchor(container, (double) 0);

        CategoryApi.getInstance().getList().forEach(category -> {
            Button categoryButton = createButton(category.getName(), "/asset/food/" + category.getId() + ".png");

            categoryButton.setOnAction(event -> {
                foodPane.getChildren().clear();

                FoodApi.getInstance().getList(category.getId()).forEach(food -> {
                    Button foodButton = createButton(food.getName(), "/asset/food/" + food.getId() + ".png");
                    foodPane.getChildren().add(foodButton);
                });
            });
            categoryPane.getChildren().add(categoryButton);
        });

    }
}
