package com.example.demo.view;

import com.example.demo.controller.RegistrationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import static com.example.demo.HelloApplication.*;

public class Login {
    private static Login loginView;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text usernameText;
    @FXML
    private Text passwordText;
    public Login() {
    }
    public void initialize() {
        RegistrationController.setLoginView(this);

    }

    private boolean isFilled() {
        if (username.getText().equals("")) {
            username.setPromptText("enter username");
            return false;
        }
        if (password.getText().equals("")) {
            password.setPromptText("enter a password");
            return false;
        }
        return true;
    }
    public void deactivate(KeyEvent keyEvent) {
        usernameText.setVisible(false);
        passwordText.setVisible(false);
    }
    public void deactivate() {
        usernameText.setVisible(false);
        passwordText.setVisible(false);
    }

    public void register(ActionEvent actionEvent) {
        deactivate();
        if (isFilled())
            RegistrationController.register(username.getText(), password.getText());
    }

    public void login(ActionEvent actionEvent) {
        deactivate();
        if (isFilled())
            RegistrationController.login(username.getText(), password.getText());

    }
    public void error(String field, String message) {
        if (field.equals("username")) {
            usernameText.setFill(Color.RED);
            usernameText.setText(message);
            usernameText.setVisible(true);
        } else {
            passwordText.setFill(Color.RED);
            passwordText.setText(message);
            passwordText.setVisible(true);
        }
    }

    public void success(String message) {
        passwordText.setFill(Color.GREEN);
        passwordText.setText(message);
        passwordText.setVisible(true);
    }

    public void logIn() {
        setRoot("mainMenu");
        }

    public void playAsGuest(ActionEvent actionEvent) {
        RegistrationController.login("guest", "password");
    }
}