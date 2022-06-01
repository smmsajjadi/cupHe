package com.example.demo.model;

import com.example.demo.HelloApplication;
import com.example.demo.controller.GamePlayController;
import com.example.demo.controller.RegistrationController;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Boss extends Transition {
    static ImageView BossImageView;
    static ImageView avtarImage;
    static Boss boss;
    int speed = 2;
    int live = 20;
    int flag = 1;
    Text textLive;
    Text textTime;
    Text textScore;

    public Boss(ImageView bossImageView, ImageView avtarImage) {
        boss = this;
        BossImageView = bossImageView;
        Boss.avtarImage = avtarImage;
        this.setCycleDuration(Duration.millis(3000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        showTime();
        int frame = (int) Math.floor(v * 11) + 1;
        BossImageView.setImage(new Image(String.valueOf(HelloApplication.class.getResource("/frames/BossShoot/" + frame + ".png"))));
        moveBoss();
        if (frame == 6 && flag == 1) {
            flag = 0;
            shootEdge();
        } else if (frame != 6) {
            flag = 1;
        }
    }

    private void showTime() {
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().remove(textLive);
        pane.getChildren().remove(textTime);
        pane.getChildren().remove(textScore);
        textLive = new Text();
        textTime = new Text();
        textScore = new Text();
        textLive.setY(100);
        textLive.setX(0);
        textLive.setText("live:::" + Game.getUserLive());
        textTime.setY(150);
        textTime.setText("Time(ms):::" + (System.currentTimeMillis() - Game.startTime));
        textScore.setY(200);
        textScore.setText("score(ms):::" + RegistrationController.getCurrentUser().score);
        pane.getChildren().addAll(textLive, textTime, textScore);
    }

    private void shootEdge() {
        ImageView image_view = new ImageView();
        image_view.setY(BossImageView.getY());
        image_view.setX(BossImageView.getX());
        image_view.setFitHeight(50);
        image_view.setFitWidth(50);
        image_view.setImage(Images.BULLET1.getImage());
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().add(image_view);
        Edge edge = new Edge(image_view, avtarImage);
        edge.play();
    }

    public void moveBoss() {
        if (BossImageView.getY() <= 250 && BossImageView.getY() >= -150)
            BossImageView.setY(BossImageView.getY() + speed);
        else if (BossImageView.getY() < -150) {
            speed = -speed;
            BossImageView.setY(BossImageView.getY() + speed);
            createMiniBoss(320, 0);
        } else if (BossImageView.getY() > 250) {
            speed = -speed;
            BossImageView.setY(BossImageView.getY() + speed);
            createMiniBoss(20, 4);
        }
    }

    private void createMiniBoss(int y, int choose) {
        ImageView miniBossImageView = new ImageView();
        miniBossImageView.setY(y);
        miniBossImageView.setX(400);
        miniBossImageView.setFitHeight(80);
        miniBossImageView.setFitWidth(50);
        miniBossImageView.setImage(Images.MINIBOSS1.getImage());
        GamePlayController.getInstance().moveMiniBoss(miniBossImageView, choose);
        Pane pane = (Pane) HelloApplication.getScene().getRoot();
        pane.getChildren().add(miniBossImageView);
    }

    public int getLive() {
        return live;
    }

    public void increaseLive(int live) {
        this.live += live;
    }

    public void decreaseLive(int live) {
        this.live -= live;
    }

    public static ImageView getBossImageView() {
        return BossImageView;
    }

    public static Boss getBoss() {
        return boss;
    }

    public static void setBoss(Boss newboss) {
        boss = newboss;
    }

}
