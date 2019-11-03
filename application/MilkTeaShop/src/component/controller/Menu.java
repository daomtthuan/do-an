package component.controller;

import api.CategoryApi;
import api.FoodApi;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.Food;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Menu implements Controller, Initializable {
    @FXML
    private ScrollPane categoryScrollPane;
    @FXML
    private HBox categoryPane;
    @FXML
    private ScrollPane foodScrollPane;
    @FXML
    private FlowPane foodPane;

    @NotNull
    private Button createButton(String name, String image, String style) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        Button button = new Button(name, imageView);
        button.getStyleClass().addAll("customButton", style);
        return button;
    }

    private int categoryScroll = 0;
    private final int MIN_CATEGORY_SCROLL = 0;
    private final int MAX_CATEGORY_SCROLL = 830;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryScrollPane.setHmin(MIN_CATEGORY_SCROLL);
        categoryScrollPane.setHmax(MAX_CATEGORY_SCROLL);
        categoryScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        categoryScrollPane.setPannable(true);
        categoryScrollPane.setFitToHeight(true);
        categoryPane.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
                if (categoryScroll == MIN_CATEGORY_SCROLL) {
                    categoryScrollPane.setHvalue(MIN_CATEGORY_SCROLL);
                }
                else {
                    categoryScroll -= 100;
                    categoryScrollPane.setHvalue(categoryScroll);
                }
            }
            else {
                if (categoryScroll == MAX_CATEGORY_SCROLL) {
                    categoryScrollPane.setHvalue(MAX_CATEGORY_SCROLL);
                }
                else {
                    categoryScroll += 100;
                    categoryScrollPane.setHvalue(categoryScroll);
                }
            }
        });

        foodScrollPane.setVmin(0);
        foodScrollPane.setVmax(foodScrollPane.getHeight());
        foodScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        foodScrollPane.setPannable(true);
        foodScrollPane.setFitToWidth(true);

        CategoryApi.getInstance().getList().forEach(category -> {
            Button categoryButton = createButton(category.getName(), "/asset/category/" + category.getId() + ".png", "categoryButton");
            categoryButton.setOnAction(categoryActionEvent -> {
                foodPane.getChildren().clear();
                FoodApi.getInstance().getList(category.getId()).forEach(food -> {
                    Button foodButton = createButton(food.getName() + "\n$" + food.getPrice(), "/asset/food/" + food.getId() + ".png", "foodButton");
                    foodButton.setOnAction(foodActionEvent -> selectFood(food));
                    foodPane.getChildren().add(foodButton);
                });
            });
            categoryPane.getChildren().add(categoryButton);
        });
    }

    public abstract void selectFood(Food food);
}
