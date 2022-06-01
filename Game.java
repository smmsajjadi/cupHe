package com.example.demo.model;

import com.example.demo.HelloApplication;
import com.example.demo.controller.RegistrationController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Game   {
    private static Game currentGame;
    private boolean isStarted;
    static double userLive;
    static double VulnerabilityCoefficient;
    static double InjuryCoefficient;
    static String  gameCase;
    static String gun;
    static long startTime;
    static Label l;

    {
        userLive = 5;
        VulnerabilityCoefficient = 1;
        InjuryCoefficient = 1;
        gun = "Bullet";
        gameCase = "degree2";
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public void initGame() {
        if (l != null)
        l.setVisible(false);
        isStarted = false;
        currentGame = this;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void start() {
        isStarted = true;
    }

    public static void setCurrentGame(Game currentGame) {
        Game.currentGame = currentGame;
    }

    public static void setUserLive(int userLive) {
        Game.userLive = userLive;
    }

    public static void setVulnerabilityCoefficient(double vulnerabilityCoefficient) {
        VulnerabilityCoefficient = vulnerabilityCoefficient;
    }

    public static void setInjuryCoefficient(double injuryCoefficient) {
        InjuryCoefficient = injuryCoefficient;
    }

    public static String getGun() {
        return gun;
    }

    public static void setGun(String gun) {
        Game.gun = gun;
    }

    public static double getVulnerabilityCoefficient() {
        return VulnerabilityCoefficient;
    }

    public static double getInjuryCoefficient() {
        return InjuryCoefficient;
    }

    public static String getGameCase() {
        return gameCase;
    }

    public static void setGameCase(String gameCase) {
        Game.gameCase = gameCase;
    }
    public static void decreaseUserLive(int amount) {
        Game.userLive -= amount;
    }

    public static double getUserLive() {
        return userLive;
    }

    public static long getStartTime() {
        return startTime;
    }

    public static void setStartTime(long startTime) {
        Game.startTime = startTime;
    }

    public static void end() {
        Boss boss = Boss.getBoss();
        boss.stop();
        l = new Label("Score:::      " +  RegistrationController.getCurrentUser().score + "\n" +
                "Time(ms):::       " + (System.currentTimeMillis() - Game.startTime));
        l.setTextFill(Paint.valueOf("gold"));
        l.setFont(Font.font(20));
        l.relocate(250, 250);
        HelloApplication.setRoot("gamePlay");
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().addAll(l);
    }

    public static void Winner() {
        RegistrationController.getCurrentUser().score+=1;
        end();
    }

    public static void lose() {
        RegistrationController.getCurrentUser().score-=1;
        end();
    }


}
