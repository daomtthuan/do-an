package main;

import access.DataProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        // Load view and set controller
        FXMLLoader viewEmployeeLogin = new FXMLLoader(getClass().getResource("/view/global/Login.fxml"));
        viewEmployeeLogin.setController(new controller.employee.Login());

        try {
            // Set scene for primaryStage
            primaryStage.setTitle("Milk Tea Shop");
            primaryStage.setScene(new Scene(viewEmployeeLogin.load()));
            primaryStage.setResizable(false);

            // Set event OnCloseRequest for primaryStage
            primaryStage.setOnCloseRequest(event -> {
                DataProvider.getInstance().close();
                Platform.exit();
                System.exit(0);
            });
            primaryStage.show();
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }

        try (ResultSet resultSet = DataProvider.getInstance().execute("select * from information")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getBoolean("gender"));
                System.out.println(resultSet.getDate("birthday"));
                System.out.println(resultSet.getString("phone"));
                System.out.println(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
