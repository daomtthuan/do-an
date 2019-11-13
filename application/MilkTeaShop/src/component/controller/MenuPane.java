package component.controller;

import app.pattern.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.Category;
import model.Food;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class MenuPane implements Controller, Initializable {
	private final int MIN_CATEGORY_SCROLL = 0;
	private final int MAX_CATEGORY_SCROLL = 830;
	@FXML
	private ScrollPane categoryScrollPane;
	@FXML
	private HBox categoryPane;
	@FXML
	private ScrollPane foodScrollPane;
	@FXML
	private FlowPane foodPane;
	private int categoryScroll = 0;

	private Button createButton(String name, String image, String style) {
		ImageView imageView = new ImageView();
		try {
			imageView.setImage(new Image(image));
		} catch (Exception e) {
			imageView.setImage(new Image("/asset/null.png"));
		}
		imageView.setFitHeight(120);
		imageView.setFitWidth(120);
		Button button = new Button(name, imageView);
		button.getStyleClass().addAll("customButton", style);
		return button;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		categoryScrollPane.setHmin(MIN_CATEGORY_SCROLL);
		categoryScrollPane.setHmax(MAX_CATEGORY_SCROLL);
		categoryScrollPane.setPannable(true);
		categoryScrollPane.setFitToHeight(true);
		categoryPane.setOnScroll(event -> {
			if (event.getDeltaY() > 0) {
				if (categoryScroll == MIN_CATEGORY_SCROLL) {
					categoryScrollPane.setHvalue(MIN_CATEGORY_SCROLL);
				} else {
					categoryScroll -= 100;
					categoryScrollPane.setHvalue(categoryScroll);
				}
			} else {
				if (categoryScroll == MAX_CATEGORY_SCROLL) {
					categoryScrollPane.setHvalue(MAX_CATEGORY_SCROLL);
				} else {
					categoryScroll += 100;
					categoryScrollPane.setHvalue(categoryScroll);
				}
			}
		});

		ArrayList<Category> categories = api.Category.getInstance().getEnabledCategories();
		categories.forEach(category -> {
			Button categoryButton = createButton(category.getName(), "/asset/category/" + category.getId() + ".png", "categoryButton");
			categoryButton.setOnAction(categoryActionEvent -> {
				foodPane.getChildren().clear();
				ArrayList<Food> foods = api.Food.getInstance().getEnabledFoods(category.getId());
				foods.forEach(food -> {
					Button foodButton = createButton(food.getName() + "\n$" + food.getPrice(), "/asset/food/" + food.getId() + ".png", "foodButton");
					foodButton.setOnAction(foodActionEvent -> selectFood(category, food));
					foodPane.getChildren().add(foodButton);
				});
			});
			categoryPane.getChildren().add(categoryButton);
		});
	}

	public abstract void selectFood(model.Category category, Food food);
}
