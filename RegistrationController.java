package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.view.Login;

public class RegistrationController {
    private static User currentUser;
    private static Login loginView;

    public static void register(String username, String password) {
        if (User.doesUsernameExist(username)) {
            loginView.error("username", "this username is already taken");
        } else {
            new User(username, password);
            loginView.success("registered successfully");
        }
    }

    public static void login(String username, String password) {
        if (username.equals("guest") && password.equals("password")){
            currentUser = new User();
            loginView.logIn();
        }
        else if (!User.doesUsernameExist(username)) {
            loginView.error("username", "no such username exist");
        } else {
            if (!User.getUserByUsername(username).isPasswordCorrect(password)) {
                loginView.error("password", "password is wrong");
            } else {
                currentUser = User.getUserByUsername(username);
                loginView.success("login successfully");
                loginView.logIn();
            }

        }
    }

    public static void setLoginView(Login loginLayout) {
        loginView = loginLayout;

    }
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }
}
