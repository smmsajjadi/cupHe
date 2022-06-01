package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.controller.RegistrationController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ExplodeAnimation extends Transition {
    ImageView avtarImage;
    public ExplodeAnimation(ImageView avtarImage) {
        this.avtarImage = avtarImage;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 4) + 1;
        avtarImage.setImage(new Image(String.valueOf(HelloApplication.class.getResource("/frames/CupheadShoot/" + frame + ".png"))));
        if(frame == 5){
            avtarImage.setImage(RegistrationController.getCurrentUser().getImage());
        }
    }
}
