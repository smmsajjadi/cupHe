package com.example.demo.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String username;
    String password;
    int score = 0;

    Image image;

    static HashMap<String, User> users;

    static {
        users = new HashMap<>();
    }

    public User(String username, String password) {
        this.password = password;
        this.username = username;
        users.put(username, this);
//        Database.saveUser(this);
    }

    public User() {
        this.username = "guest";
        this.password = "password";
    }

    public static boolean doesUsernameExist(String username) {
        return users.containsKey(username);
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }

    public static ArrayList<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public void remove() {
//        Database.removeAccount(this);
        users.remove(this.getUsername(), this);
    }

    public void changePassword(String newPassword) {
        password = newPassword;
//        Database.saveUser(this);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLastScore(int score) {
        if (this.username.equals("guest") && this.password.equals("password")) return;
        if (this.score < score) this.score = score;
//        Database.saveUser(this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
