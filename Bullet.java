package com.example.demo.model;

import com.example.demo.HelloApplication;
import com.example.demo.controller.GamePlayController;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;


public class Bullet extends Transition {
    private final ImageView bulletImageView;
    private final ImageView bossImage;
    public static ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
    int speed = 2;

    public Bullet(ImageView bulletImageView) {
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(-1);
        this.bulletImageView = bulletImageView;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.bossImage = Boss.BossImageView;
        if (GamePlayController.getInstance().isBlackWhite())
            bulletImageView.setEffect(color);
        allBullets.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int dx = speed;
        moveBullet(dx);
        MiniBoss miniBoss;
        if ((miniBoss = this.hasCollisionWithMiniBoss()) != null) {
            removeBulletAndMiniBoss(miniBoss);
        }
        if ((this.hasCollisionWithBoss()) != null) {
            if (Boss.getBoss().getLive() > 0) {
                removeBulletAndBoss();
            }
        }
    }

    public void moveBullet(int dx) {
        bulletImageView.setX(bulletImageView.getX() + dx);
    }

    private ImageView hasCollisionWithBoss() {
        if (bulletImageView.getBoundsInParent().intersects(Boss.BossImageView.getLayoutBounds())) {
            return Boss.BossImageView;
        }
        return null;

    }

    private void removeBulletAndBoss() {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().remove(bulletImageView);
        Boss.getBoss().decreaseLive(1);
        GamePlayController.getInstance().setBossLive();
        GamePlayController.getInstance().setLabel(Boss.getBoss().getLive());
        Bullet.allBullets.remove(this);
        this.stop();
        if (Boss.getBoss().getLive() == 0) {
            pane.getChildren().remove(Boss.BossImageView);
            Boss.BossImageView.setImage(null);
            Boss.getBoss().stop();
            Game.Winner();
        }
    }

    private void removeBulletAndMiniBoss(MiniBoss miniBoss) {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        miniBoss.decreaseLive(1);
        pane.getChildren().remove(bulletImageView);
        Bullet.allBullets.remove(this);
        if (miniBoss.getLive() <= 0) {
            pane.getChildren().remove(miniBoss.getMiniBossImageView());
            MiniBoss.allMiniBosses.remove(miniBoss);
        }
        this.stop();
    }

    public MiniBoss hasCollisionWithMiniBoss() {
        for (MiniBoss miniBoss : MiniBoss.allMiniBosses) {
            if (bulletImageView.getBoundsInParent().intersects(miniBoss.getMiniBossImageView().getLayoutBounds())) {
                return miniBoss;
            }
        }
        return null;
    }

    public ImageView getBulletImageView() {
        return bulletImageView;
    }
}
