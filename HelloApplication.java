package com.example.demo;

import com.example.demo.model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    private static Scene scene;
    public static Stage stage;
    private static boolean isMute;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = fxmlLoader("Login");
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("cupHead!");
        HelloApplication.stage = stage;
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
        stage.setScene(scene);
//        Database.readUserInfo();
    }

    public static void main(String[] args) {
        isMute = false;
        launch();
    }

    public static FXMLLoader fxmlLoader(String fxml) {
        return new FXMLLoader(HelloApplication.class.getResource("/viewFxml/" + fxml + ".fxml"));
    }

    public static void setRoot(String fxml) {
        scene.setRoot(loadFXML(fxml));
    }

    public static Scene getScene() {
        return scene;
    }

    public static Parent loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/viewFxml/" + fxml + ".fxml"));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isIsMute() {
        return isMute;
    }

    public static void setIsMute(boolean isMute) {
        HelloApplication.isMute = isMute;
    }


}