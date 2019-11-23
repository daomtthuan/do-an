package component.controller.general;

import app.pattern.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class MenuPane implements Controller, Initializable {
	protected Runnable refresh;
	@FXML
	private ScrollPane categoryScrollPane;
	@FXML
	private HBox categoryPane;
	@FXML
	private ScrollPane foodScrollPane;
	@FXML
	private FlowPane foodPane;
	private int categoryScroll = 0;

	public Runnable getRefresh() {
		return refresh;
	}

	protected Button createButton(String name, String image, String style) {
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
		setup();
		final double MIN_CATEGORY_SCROLL = categoryScrollPane.getHmin();
		final double MAX_CATEGORY_SCROLL = categoryScrollPane.getHmax();
		categoryScrollPane.setPannable(true);
		categoryScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		categoryScrollPane.setFitToHeight(true);
		categoryScrollPane.setFitToWidth(false);
		categoryPane.setOnScroll(event -> {
			if (event.getDeltaY() > 0) {
				if (categoryScroll == MIN_CATEGORY_SCROLL) {
					categoryScrollPane.setHvalue(MIN_CATEGORY_SCROLL);
				} else {
					categoryScroll -= 1;
					categoryScrollPane.setHvalue(categoryScroll);
				}
			} else {
				if (categoryScroll == MAX_CATEGORY_SCROLL) {
					categoryScrollPane.setHvalue(MAX_CATEGORY_SCROLL);
				} else {
					categoryScroll += 1;
					categoryScrollPane.setHvalue(categoryScroll);
				}
			}
		});
	}

	protected abstract void setup();

	protected HBox getCategoryPane() {
		return categoryPane;
	}

	protected ScrollPane getCategoryScrollPane() {
		return categoryScrollPane;
	}

	protected ScrollPane getFoodScrollPane() {
		return foodScrollPane;
	}

	protected FlowPane getFoodPane() {
		return foodPane;
	}
}
