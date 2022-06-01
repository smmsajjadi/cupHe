package com.example.demo.model;

import com.example.demo.HelloApplication;
import com.example.demo.controller.GamePlayController;
import com.example.demo.controller.RegistrationController;
import com.example.demo.view.ExplodeAnimation;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Rocket extends Transition {
    private final ImageView avtarImage;
    int speed = 1;

    public Rocket(ImageView avtarImage) {
        this.avtarImage = avtarImage;
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(-1);
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        if (GamePlayController.getInstance().isBlackWhite())
            avtarImage.setEffect(color);
    }

    @Override
    protected void interpolate(double v) {
        int dx = speed;
        moveRocket(dx);
        MiniBoss miniBoss;
        if ((miniBoss = this.hasCollisionWithMiniBoss()) != null) {
            removeMiniBoss(miniBoss);
        }
        if ((this.hasCollisionWithBoss()) != null) {
            if (Boss.getBoss().getLive() > 0) {
                removeBoss();
            }
        }
    }

    private void moveRocket(int dx) {
        avtarImage.setX(avtarImage.getX() + dx);
    }


    private ImageView hasCollisionWithBoss() {
        if (avtarImage.getBoundsInParent().intersects(Boss.BossImageView.getLayoutBounds())) {
            return Boss.BossImageView;
        }
        return null;
    }

    private void removeBoss() {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        Boss.getBoss().decreaseLive(4);
        GamePlayController.getInstance().setBossLive();
        GamePlayController.getInstance().setLabel(Boss.getBoss().getLive());
        ExplodeAnimation explodeAnimation = new ExplodeAnimation(avtarImage);
        explodeAnimation.play();
        this.stop();
        avtarImage.setImage(RegistrationController.getCurrentUser().getImage());
        convertRockToAvatar();
        if (Boss.getBoss().getLive() <= 0) {
            pane.getChildren().remove(Boss.BossImageView);
            Boss.BossImageView.setImage(null);
            Boss.getBoss().stop();
            Game.Winner();
        }
    }

    private void removeMiniBoss(MiniBoss miniBoss) {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        miniBoss.decreaseLive(2);
        ExplodeAnimation explodeAnimation = new ExplodeAnimation(avtarImage);
        explodeAnimation.play();
        this.stop();
        convertRockToAvatar();
        if (miniBoss.getLive() <= 0) {
            pane.getChildren().remove(miniBoss.getMiniBossImageView());
            MiniBoss.allMiniBosses.remove(miniBoss);
        }
    }

    public MiniBoss hasCollisionWithMiniBoss() {
        for (MiniBoss miniBoss : MiniBoss.allMiniBosses) {
            if (avtarImage.getBoundsInParent().intersects(miniBoss.getMiniBossImageView().getLayoutBounds())) {
                return miniBoss;
            }
        }
        return null;
    }

    public void convertRockToAvatar() {
        avtarImage.setImage(RegistrationController.getCurrentUser().getImage());
    }

}
