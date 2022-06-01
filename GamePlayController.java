package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.model.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GamePlayController {
    private static GamePlayController gameplayController = null;
    private Game current;
    ProgressBar progressBar;
    Label label;
    boolean isBlackWhite = false;

    private GamePlayController() {
    }

    public static GamePlayController getInstance() {
        if (gameplayController == null) {
            gameplayController = new GamePlayController();
        }
        return gameplayController;
    }

    public void shootBullet(ImageView bulletImageView) {
        Bullet bullet = new Bullet(bulletImageView);
        bullet.play();
    }

    public void shoot(double x, double y) {
        ImageView image_view = new ImageView();
        image_view.setY(y + 190);
        image_view.setX(x + 80);
        image_view.setImage(Images.BULLET1.getImage());
        GamePlayController.getInstance().shootBullet(image_view);
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().add(image_view);
    }
    private void shootBomb(double x, double y) {
        ImageView image_view = new ImageView();
        image_view.setY(y + 190);
        image_view.setX(x + 80);
        image_view.setImage(Images.BOMB.getImage());
        image_view.setFitWidth(50);
        image_view.setFitHeight(50);
        GamePlayController.getInstance().shootBomb(image_view);
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().add(image_view);
    }

    private void shootBomb(ImageView image_view) {
        Bomb bomb = new Bomb(image_view);
        bomb.play();
    }


    public void moveBoss(ImageView avtarImage) {
        ImageView bossImage = new ImageView();
        bossImage.setY(100);
        bossImage.setX(500);
        bossImage.setFitHeight(180);
        bossImage.setFitWidth(120);
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().add(bossImage);
        Boss boss = new Boss(bossImage, avtarImage);
        progressBar = new ProgressBar();
        progressBar.setProgress(1);
        progressBar.setLayoutX(400);
        pane.getChildren().add(progressBar);
        label = new Label();
        label.setLayoutX(300);
        label.setLayoutY(0);
        label.setText("BossLive" + Boss.getBoss().getLive());
        pane.getChildren().add(label);
        boss.play();
    }


    public void moveAvatar(KeyEvent keyEvent, ImageView avtarImage) {
        if (keyEvent.getCode().getName().equals("Up") && !inHitYUp(avtarImage))
            avtarImage.setY(avtarImage.getY() - 10);
        if (keyEvent.getCode().getName().equals("Down") && !inHitYDown(avtarImage))
            avtarImage.setY(avtarImage.getY() + 10);
        if (keyEvent.getCode().getName().equals("Right") && !inHitXRight(avtarImage))
            avtarImage.setX(avtarImage.getX() + 10);
        if (keyEvent.getCode().getName().equals("Left") && !inHitXLeft(avtarImage))
            avtarImage.setX(avtarImage.getX() - 10);
        if (keyEvent.getCode().getName().equals("Space") && Game.getGun().equals("Bullet")) {
            shoot(avtarImage.getX(), avtarImage.getY());
        }if (keyEvent.getCode().getName().equals("Space") && Game.getGun().equals("Bomb")) {
            shootBomb(avtarImage.getX(), avtarImage.getY());
        }if (keyEvent.getCode().getName().equals("R")) {
            convertToRocket(avtarImage);
        }
    }

    private void convertToRocket(ImageView avtarImage) {
        avtarImage.setImage(Images.Rocket.getImage());
        Rocket rocket = new Rocket(avtarImage);
        rocket.play();
    }


    public boolean inHitYUp(ImageView imageView) {
        return imageView.getY() < -150;
    }

    public boolean inHitYDown(ImageView imageView) {
        return imageView.getY() > 150;
    }

    public boolean inHitXRight(ImageView imageView) {
        return imageView.getX() > 460;
    }

    public boolean inHitXLeft(ImageView imageView) {
        return imageView.getX() < -70;
    }

    public void moveMiniBoss(ImageView miniBossImageView, int choose) {
        MiniBoss miniBoss = new MiniBoss(miniBossImageView, choose);
        miniBoss.play();
    }


    public void setBossLive() {
        progressBar.setProgress(Boss.getBoss().getLive()/20.0);
    }

    public void setLabel(int liveNum) {
        this.label.setText("BossLive" + (liveNum));
    }

    public void blackWhite() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-1);
        Boss.getBossImageView().setEffect(colorAdjust);
        isBlackWhite = true;
    }

    public boolean isBlackWhite() {
        return isBlackWhite;
    }
}
