package com.example.demo.model;

import com.example.demo.HelloApplication;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBoss extends Transition {
    private final ImageView miniBossImageView;
    public static ArrayList<MiniBoss> allMiniBosses = new ArrayList<MiniBoss>();
    double speed = 1;
    int live = 2;
    int choose;

    public MiniBoss(ImageView miniBossImageView, int choose) {
        this.miniBossImageView = miniBossImageView;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.choose = choose;
        allMiniBosses.add(this);
        miniBossImageView.setY(miniBossImageView.getY());
    }

    @Override
    protected void interpolate(double v) {
        int frame = choose;
         frame += (int) Math.floor(v * 4) + 1 ;

        miniBossImageView.setImage(new Image(String.valueOf(HelloApplication.class.getResource("/frames/MiniBossFly/" + frame + ".png"))));
        moveMiniBoss();
    }

    private void moveMiniBoss() {
        miniBossImageView.setX(miniBossImageView.getX()-speed);
    }

    public ImageView getMiniBossImageView() {
        return miniBossImageView;
    }

    public void decreaseLive(int live) {
        this.live -= live;
    }

    public int getLive() {
        return live;
    }

}
