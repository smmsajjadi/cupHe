package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.controller.ScoreBoardController;
import com.example.demo.model.Images;
import com.example.demo.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Scoreboard {
    public Button showScoreboard;

    public void back(ActionEvent actionEvent) {
        HelloApplication.setRoot("mainMenu");
    }

    public void showScoreboard(ActionEvent actionEvent) {
        List<User> users = ScoreBoardController.sortedUsers();
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        for (int i = 0; i < users.size() && i < 10; i++) {
            if (i == 0) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setImage(Images.GOLD.getImage());
                Label l = new Label(users.get(0).getUsername(), imageView);
                l.relocate(180, 0);
                pane.getChildren().add(l);
            } else if (i == 1) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setImage(Images.SILVER.getImage());
                Label l = new Label(users.get(1).getUsername(), imageView);
                l.relocate(180, 40);
                pane.getChildren().add(l);
            } else if (i == 2) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setImage(Images.BRONZE.getImage());
                Label l = new Label(users.get(2).getUsername(), imageView);
                l.relocate(180, 80);
                pane.getChildren().add(l);
            } else {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setImage(Images.GEM.getImage());
                Label l = new Label(users.get(i).getUsername(), imageView);
                l.relocate(180, i * 40);
                pane.getChildren().add(l);
            }
        }
    }
}
