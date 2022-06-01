package com.example.demo.model;

import com.example.demo.HelloApplication;
import javafx.scene.image.Image;

public enum Images {
    AVATAR_RED(new Image(String.valueOf(HelloApplication.class.getResource("/frames/images/" + "red" + ".png")))),
    AVATAR_BLUE(new Image(String.valueOf(HelloApplication.class.getResource("/frames/images/" + "blue" + ".png")))),
    BACK1(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0001" + ".png")))),
    BACK2(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0002" + ".png")))),
    BACK3(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0003" + ".png")))),
    BACK4(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0004" + ".png")))),
    BACK5(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0005" + ".png")))),
    BACK6(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0006" + ".png")))),
    BACK7(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0007" + ".png")))),
    BACK8(new Image(String.valueOf(HelloApplication.class.getResource("/frames/Background/" + "birdhouse_bg_0008" + ".png")))),
    BULLET1(new Image(String.valueOf(HelloApplication.class.getResource("/frames/images/" + "bullet" + ".png")))),
    BOMB(new Image(String.valueOf(HelloApplication.class.getResource("/frames/CupheadShoot/" + "bomb" + ".jpg")))),
    Rocket(new Image(String.valueOf(HelloApplication.class.getResource("/frames/CupheadShoot/" + "moshak" + ".jpg")))),
    BOSS1(new Image(String.valueOf(HelloApplication.class.getResource("/frames/BossFly/" + "1" + ".png")))),
    MINIBOSS1(new Image(String.valueOf(HelloApplication.class.getResource("/frames/MiniBossFly/" + "1" + ".png")))),
    GOLD(new Image(String.valueOf(HelloApplication.class.getResource("/frames/medal/" + "gold" + ".jpg")))),
    SILVER(new Image(String.valueOf(HelloApplication.class.getResource("/frames/medal/" + "silver" + ".jpg")))),
    BRONZE(new Image(String.valueOf(HelloApplication.class.getResource("/frames/medal/" + "bronze" + ".jpg")))),
    GEM(new Image(String.valueOf(HelloApplication.class.getResource("/frames/medal/" + "gem" + ".jpg"))));

    final Image image;

    Images(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }
}
