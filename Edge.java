package com.example.demo.model;

import com.example.demo.HelloApplication;
import com.example.demo.view.GamePlay;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Edge extends Transition {
    private final ImageView edgeImageView;
    private final ImageView avtarImage;
    public static ArrayList<Edge> allEdges = new ArrayList<>();
    int speed = 2;
    int collision = 1;
    long timeCollision;

    public Edge(ImageView edgeImageView, ImageView avtarImage) {
        this.edgeImageView = edgeImageView;
        this.avtarImage = avtarImage;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        allEdges.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int dx = -speed;
        moveEdge(v, dx);
        hasCollisionWithBoss(v);
        hasCollisionWithEdge();
        hasCollisionWithMiniBoss();
        if (System.currentTimeMillis() - timeCollision > 1000)
            collision = 1;
    }

    private void moveEdge(double v, int dx) {
        int frame = (int) Math.floor(v * 11) + 1;
        edgeImageView.setImage(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Phase 2/Egg/egghead_rotating_eggs_" + frame + ".png"))));
        edgeImageView.setX(edgeImageView.getX() + dx);
    }

    private void hasCollisionWithBoss(double v) {
        if (avtarImage.getBoundsInParent().intersects(Boss.BossImageView.getLayoutBounds()) && collision == 1) {
            timeCollision = System.currentTimeMillis();
            collision = 0;
            FadeTransition fadeTransition =
                    new FadeTransition(Duration.millis(100), avtarImage);
            fadeTransition.setFromValue(1.0f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(10);
            fadeTransition.setAutoReverse(true);
            fadeTransition.play();
            Pane pane = (Pane) HelloApplication.getScene().getRoot();
            Game.decreaseUserLive(1);
            if (Game.getUserLive() <= 0) {
                pane.getChildren().remove(avtarImage);
                Game.lose();
            }
        }
    }

    private void hasCollisionWithEdge() {
        if (avtarImage.getBoundsInParent().intersects(edgeImageView.getLayoutBounds()) && collision == 1) {
            timeCollision = System.currentTimeMillis();
            collision = 0;
            FadeTransition fadeTransition =
                    new FadeTransition(Duration.millis(100), avtarImage);
            fadeTransition.setFromValue(1.0f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(10);
            fadeTransition.setAutoReverse(true);
            fadeTransition.play();
            Pane pane = (Pane) HelloApplication.getScene().getRoot();
            Game.decreaseUserLive(1);
            if (Game.getUserLive() <= 0) {
                pane.getChildren().remove(avtarImage);
                Game.lose();
            }
            System.out.println(Game.getUserLive());
        }
    }

    private void hasCollisionWithMiniBoss() {
        for (MiniBoss miniBoss : MiniBoss.allMiniBosses) {
            if (avtarImage.getBoundsInParent().intersects(miniBoss.getMiniBossImageView().getLayoutBounds()) && collision == 1) {
                timeCollision = System.currentTimeMillis();
                collision = 0;
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(100), avtarImage);
                fadeTransition.setFromValue(1.0f);
                fadeTransition.setToValue(0f);
                fadeTransition.setCycleCount(10);
                fadeTransition.setAutoReverse(true);
                fadeTransition.play();
                Pane pane = (Pane) HelloApplication.getScene().getRoot();
                Game.decreaseUserLive(1);
                if (Game.getUserLive() <= 0) {
                    pane.getChildren().remove(avtarImage);
                    Game.lose();
                }
                System.out.println(Game.getUserLive());
            }
        }
    }
}