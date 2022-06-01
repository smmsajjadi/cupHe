package com.example.demo.model;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Database {
    public static void saveUser(User user) {
        write(new Gson().toJson(user));
    }

    private static void write( String data){
        try {
            FileWriter fileWriter = new FileWriter("Mahdi.json");
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
