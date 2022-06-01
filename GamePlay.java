package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.controller.GamePlayController;
import com.example.demo.controller.RegistrationController;
import com.example.demo.model.Boss;
import com.example.demo.model.Game;
import com.example.demo.model.Images;
import com.example.demo.model.Voices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Timer;
import java.io.IOException;
import java.util.TimerTask;

public class GamePlay {

    public ProgressBar bossLive;
    public Button Restart;
    public Button Exit;
    public Button pause;
    public Button mute;
    @FXML
    private ImageView sky4;
    @FXML
    private ImageView land;
    @FXML
    private ImageView sky2;
    @FXML
    private ImageView sky3;
    @FXML
    private Button ReadyButton;
    @FXML
    private ImageView avtarImage;
    @FXML
    private GamePlayController gamePlayController;

    @FXML
    protected void initialize() {
        Voices.INTRO.play();
    }

    public void ReadyButton(ActionEvent actionEvent) {
        Game current = new Game();
        current.initGame();
        Image image = RegistrationController.getCurrentUser().getImage();
        if (image == null)
            image = Images.AVATAR_RED.getImage();
        RegistrationController.getCurrentUser().setImage(image);
        avtarImage.setImage(image);
        avtarImage.requestFocus();
        avtarImage.setFocusTraversable( true);
        Game.setStartTime(System.currentTimeMillis());
        setBackground();
    }

    private void setBackground() {
        ReadyButton.setVisible(false);
        sky4.setImage(Images.BACK8.getImage());
        sky2.setImage(Images.BACK3.getImage());
        sky3.setImage(Images.BACK2.getImage());
        land.setImage(Images.BACK4.getImage());
        GamePlayController.getInstance().moveBoss(avtarImage);
    }

    public void moveAvatar(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().getName().equals("N")){
            Restart.setVisible(false);
            Exit.setVisible(false);
            pause.setVisible(false);
            mute.setVisible(false);
        }if (keyEvent.getCode().getName().equals("V")){
            Restart.setVisible(true);
            Exit.setVisible(true);
            pause.setVisible(true);
            mute.setVisible(true);
        }
        if (keyEvent.getCode().getName().equals("Tab")) {
            changeGun();
        }
        if (keyEvent.getCode().getName().equals("W")) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-1);
            land.setEffect(colorAdjust);
            sky2.setEffect(colorAdjust);
            sky3.setEffect(colorAdjust);
            sky4.setEffect(colorAdjust);
            avtarImage.setEffect(colorAdjust);
            avtarImage.requestFocus();
            GamePlayController.getInstance().blackWhite();
        }
        GamePlayController.getInstance().moveAvatar(keyEvent, avtarImage);
    }

    private void changeGun() {
        if (Game.getGun().equals("Bomb"))
            Game.setGun("Bullet");
        else
            Game.setGun("Bomb");
    }

    public ImageView getAvtarImage() {
        return avtarImage;
    }

    public void Restart(ActionEvent actionEvent) {
        Boss.getBoss().stop();
        HelloApplication.setRoot("MainMenu");

    }

    public void Pause(ActionEvent actionEvent) {
        Boss.getBoss().stop();
    }

    public void Exit(ActionEvent actionEvent) {
            Boss.getBoss().stop();
            HelloApplication.setRoot("gamePlay");
    }

    public void Mute(ActionEvent actionEvent) {
            HelloApplication.setIsMute(true);
            MainMenu.muteSound();

        Voices.mute();
    }
}
