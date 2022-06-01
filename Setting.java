package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.model.Game;
import com.example.demo.model.Voices;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class Setting {
    public ToggleButton soundMuter;
    public Button degree1;
    public Button degree2;
    public Button degree3;
    public Button devilMode;

    public void mute(ActionEvent actionEvent) {
        if (!soundMuter.isSelected()) {
            HelloApplication.setIsMute(false);
            MainMenu.playBackGroundMusic();
        } else {
            HelloApplication.setIsMute(true);
            MainMenu.muteSound();
        }
        Voices.mute();
    }


    public void back(ActionEvent actionEvent) {
        HelloApplication.setRoot("mainMenu");
    }

    public void degree1(ActionEvent actionEvent) {
        Game.setUserLive(10);
        Game.setInjuryCoefficient(0.5);
        Game.setVulnerabilityCoefficient(1.5);
        Game.setGameCase("degree1");

    }
    public void degree2(ActionEvent actionEvent) {
        Game.setUserLive(5);
        Game.setInjuryCoefficient(1);
        Game.setVulnerabilityCoefficient(1);
        Game.setGameCase("degree2");

    }
    public void degree3(ActionEvent actionEvent) {
        Game.setUserLive(2);
        Game.setInjuryCoefficient(1.5);
        Game.setVulnerabilityCoefficient(0.5);
        Game.setGameCase("degree3");
    }
    public void devilMode(ActionEvent actionEvent) {
        Game.setUserLive(2);
        Game.setInjuryCoefficient(1.5);
        Game.setVulnerabilityCoefficient(0.5);
        Game.setGameCase("devilMode");
        degree1.cancelButtonProperty();
        degree1.setVisible(false);
        degree2.cancelButtonProperty();
        degree2.setVisible(false);
        degree3.cancelButtonProperty();
        degree3.setVisible(false);
    }

}