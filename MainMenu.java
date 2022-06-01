package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.model.Voices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenu {
    @FXML
    public void initialize(){
        if (HelloApplication.isIsMute()) muteSound();
        else playBackGroundMusic();
    }
    public void profile(ActionEvent actionEvent) {
        HelloApplication.setRoot("profile");
    }

    public void scoreboardMenu(ActionEvent actionEvent) {
        HelloApplication.setRoot("scoreboard");
    }

    public void logout(ActionEvent actionEvent) {
        HelloApplication.setRoot("login");
//        Database.writeUserInfo();
    }

    public void play(ActionEvent actionEvent) {
        HelloApplication.setRoot("gamePlay");
    }

    public void setting(ActionEvent actionEvent) {
        HelloApplication.setRoot("setting");
    }
    public static void playBackGroundMusic(){
        Voices.BACKGROUND.loop();
    }
    public static void muteSound() {
        Voices.BACKGROUND.pause();
    }

}
