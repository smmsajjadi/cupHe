package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.view.Profile;


public class ProfileController {
    private static Profile view;

    public static void setView(Profile view) {
        ProfileController.view = view;
    }

    public static void deleteAccount() {
        System.out.println("successful delete!");
        RegistrationController.getCurrentUser().remove();
        RegistrationController.logout();
    }

    public static void logout() {
        RegistrationController.logout();
    }

    public static void changePassword(String oldPassword, String newPassword)
    {
        User currentUser = RegistrationController.getCurrentUser();
        if (currentUser.isPasswordCorrect(oldPassword)) {
            currentUser.changePassword(newPassword);
            view.success("password changed successfully");
        } else {
            view.error("incorrect password");
        }
        view.oldPassword.setText(null);
        view.newPassword.setText(null);
    }

    public static void changeUsername(String newUsername) {
        User currentUser = RegistrationController.getCurrentUser();
        if ((newUsername.equals(currentUser.getUsername())))
            view.error("enter new username");
        else{
            currentUser.setUsername(newUsername);
            view.success("username changed!");
        }
        view.newUsername.setText(null);
    }
}
