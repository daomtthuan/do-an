package controller;

import access.AccessCategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Category;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Controller food.
 */
public final class ControllerFood implements Initializable {
    private ArrayList<Category> categories;

    @FXML
    private VBox container;

    @FXML
    private HBox categoryPane;

    /**
     * Gets categories.
     *
     * @return the categories
     */
    @Contract(pure = true)
    public ArrayList<Category> getCategories() {
        return categories;
    }

    @NotNull
    private Button createButton(@NotNull Category category, String urlImage) {
        ImageView imageView = new ImageView(urlImage);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Button button = new Button(category.getName(), imageView);
        button.setFont(new Font(14));
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefSize(100, 150);
        button.setContentDisplay(ContentDisplay.TOP);
        return button;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setTopAnchor(container, (double) 0);
        AnchorPane.setRightAnchor(container, (double) 0);
        AnchorPane.setBottomAnchor(container, (double) 0);
        AnchorPane.setLeftAnchor(container, (double) 0);

        categories = AccessCategory.getInstance().load();
        categories.forEach(category -> categoryPane.getChildren().add(createButton(category, "/asset/food/1.png")));
    }
}
