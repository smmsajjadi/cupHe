package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.controller.ProfileController;
import com.example.demo.controller.RegistrationController;
import com.example.demo.model.Images;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Profile {

    public Text changePasswordEnterPass;
    public Button changeBtn;
    public Button changeUsernameBtn;
    @FXML
    public Text passwordText;
    @FXML
    public PasswordField oldPassword;
    @FXML
    public PasswordField newPassword;
    @FXML
    public TextField newUsername;
    @FXML
    public ImageView avatar;

    public void initialize() {
        ProfileController.setView(this);
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        deactivate();
        ProfileController.deleteAccount();
        HelloApplication.setRoot("login");
    }

    public void changePassword(MouseEvent mouseEvent) {
        deactivate();
        changePasswordEnterPass.setText("changePassword");
        oldPassword.setVisible(true);
        newPassword.setVisible(true);
        changePasswordEnterPass.setVisible(true);
        changeBtn.setVisible(true);
    }

    public void changePasswordNow(MouseEvent mouseEvent) {
        if (oldPassword.getText() == null || oldPassword.getText().equals("")) {
            oldPassword.setPromptText("enter old password");
        }
        if (newPassword.getText() == null || newPassword.getText().equals(""))
            newPassword.setPromptText("enter new password");
        else if (!newPassword.getText().isEmpty() && !oldPassword.getText().isEmpty()) {
            ProfileController.changePassword(oldPassword.getText(), newPassword.getText());
        }
    }

    public void logout(MouseEvent mouseEvent) {
        deactivate();
        deactivate();
        ProfileController.logout();
        HelloApplication.setRoot("login");
    }

    public void setAvatar(MouseEvent mouseEvent) {
        deactivate();
        int randomNumber = (int) Math.round(Math.random());
        Image image;
        if (randomNumber == 0)
            image = Images.AVATAR_RED.getImage();
        else
            image = Images.AVATAR_BLUE.getImage();
        RegistrationController.getCurrentUser().setImage(image);
        avatar.setVisible(true);
        avatar.setImage(image);
    }


    public void error(String message) {
        passwordText.setText(message);
        passwordText.setFill(Color.RED);
        passwordText.setVisible(true);
    }

    public void success(String message) {
        passwordText.setText(message);
        passwordText.setFill(Color.GREEN);
        passwordText.setVisible(true);
    }

    public void changeUsername(MouseEvent mouseEvent) {
        deactivate();
        changePasswordEnterPass.setText("changeUsername");
        newUsername.setVisible(true);
        changePasswordEnterPass.setVisible(true);
        changeUsernameBtn.setVisible(true);
    }

    public void changeUsernameBtn(MouseEvent mouseEvent) {
        if (newUsername.getText() == null || newUsername.getText().equals("")) {
            newUsername.setPromptText("enter new username");
        } else if (!newUsername.getText().isEmpty()) {
            ProfileController.changeUsername(newUsername.getText());
        }
    }

    public void deactivate(KeyEvent keyEvent) {
        passwordText.setVisible(false);
        oldPassword.setVisible(false);
        newPassword.setVisible(false);
        changePasswordEnterPass.setVisible(false);
        newUsername.setVisible(false);
        changeUsernameBtn.setVisible(false);
        changeBtn.setVisible(false);
    }

    public void deactivate() {
        passwordText.setVisible(false);
        oldPassword.setVisible(false);
        newPassword.setVisible(false);
        changePasswordEnterPass.setVisible(false);
        newUsername.setVisible(false);
        changeUsernameBtn.setVisible(false);
        changeBtn.setVisible(false);
        avatar.setVisible(false);
    }
}
