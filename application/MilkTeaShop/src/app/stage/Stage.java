package app.stage;

import app.alert.AlertError;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

abstract class Stage {
    private javafx.stage.Stage stage;

    public javafx.stage.Stage getStage() {
        return stage;
    }

    public void setStage(javafx.stage.Stage stage) {
        this.stage = stage;
    }

    public void setScene(String view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            Scene scene = new Scene(loader.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public void setScene(String view, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public void setScene(String view, String style) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            Scene scene = new Scene(loader.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            scene.getStylesheets().add(style);
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public void setScene(String view, String style, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/Style.css");
            scene.getStylesheets().add(style);
            stage.setScene(scene);
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public Node loadComponent(String component) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(component));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Node loadComponent(String component, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(component));
            loader.setController(controller);
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
