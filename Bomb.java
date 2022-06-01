package com.example.demo.model;

import com.example.demo.HelloApplication;
import com.example.demo.controller.GamePlayController;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Bomb extends Transition {
    private final ImageView bombImageView;
    public static ArrayList<Bomb> allBombs = new ArrayList<Bomb>();
    int speed = 1;

    public Bomb(ImageView bombImageView) {
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(-1);
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.bombImageView = bombImageView;
        if (GamePlayController.getInstance().isBlackWhite())
            bombImageView.setEffect(color);
        allBombs.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int dy = speed;
        moveBomb(dy);
        MiniBoss miniBoss;
        if ((miniBoss = this.hasCollisionWithMiniBoss()) != null) {
            removeBombAndMiniBoss(miniBoss);
        }
        if ((this.hasCollisionWithBoss()) != null) {
            if (Boss.getBoss().getLive() > 0) {
                removeBombAndBoss();
            }
        }
    }

    private void removeBombAndBoss() {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().remove(bombImageView);
        Boss.getBoss().decreaseLive(2);
        GamePlayController.getInstance().setBossLive();
        GamePlayController.getInstance().setLabel(Boss.getBoss().getLive());
        Bomb.allBombs.remove(this);
        this.stop();
        if (Boss.getBoss().getLive() == 0) {
            pane.getChildren().remove(Boss.BossImageView);
            Boss.BossImageView.setImage(null);
            Boss.getBoss().stop();
            Game.Winner();
        }
    }

    private ImageView hasCollisionWithBoss() {
        if (bombImageView.getBoundsInParent().intersects(Boss.BossImageView.getLayoutBounds())) {
            return Boss.BossImageView;
        }
        return null;
    }

    private void removeBombAndMiniBoss(MiniBoss miniBoss) {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        miniBoss.decreaseLive(2);
        pane.getChildren().remove(bombImageView);
        Bomb.allBombs.remove(this);
        this.stop();
        if (miniBoss.getLive() <= 0) {
            pane.getChildren().remove(miniBoss.getMiniBossImageView());
            MiniBoss.allMiniBosses.remove(miniBoss);
        }
    }

    private MiniBoss hasCollisionWithMiniBoss() {
        for (MiniBoss miniBoss : MiniBoss.allMiniBosses) {
            if (bombImageView.getBoundsInParent().intersects(miniBoss.getMiniBossImageView().getLayoutBounds())) {
                return miniBoss;
            }
        }
        return null;
    }

    private void moveBomb(int dy) {
        bombImageView.setY(bombImageView.getY() + dy);
    }
}
