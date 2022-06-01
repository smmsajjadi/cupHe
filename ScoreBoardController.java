package com.example.demo.controller;

import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoardController {
    public static List<User> sortedUsers() {
        ArrayList<User> users = User.getUsers();
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getScore() == o2.getScore()) {
                    return o1.getUsername().compareTo(o2.getUsername());
                }
                return o1.getScore() > o2.getScore() ? -1 : 1;
            }
        });
        if (users.size() > 9) return users.subList(0, 9);
        return users.subList(0, users.size());
    }

}
