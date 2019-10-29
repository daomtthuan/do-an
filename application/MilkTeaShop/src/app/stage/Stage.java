package app.stage;

import app.alert.AlertError;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

class Stage {
    private javafx.stage.Stage stage;

    public javafx.stage.Stage getStage() {
        return stage;
    }

    public void setStage(javafx.stage.Stage stage) {
        this.stage = stage;
    }

    public void setScene(String viewPath) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource(viewPath));
            Scene scene = new Scene(view.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public void setScene(String viewPath, Controller controller) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource(viewPath));
            view.setController(controller);
            Scene scene = new Scene(view.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public void setScene(String viewPath, String stylePath) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource(viewPath));
            Scene scene = new Scene(view.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            scene.getStylesheets().add(stylePath);
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public void setScene(String viewPath, String stylePath, Controller controller) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource(viewPath));
            view.setController(controller);
            Scene scene = new Scene(view.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            scene.getStylesheets().add(stylePath);
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }
}
