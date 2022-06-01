package com.example.demo.model;

import com.example.demo.HelloApplication;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public enum Voices {
    BACKGROUND("background.wav"),
    CHOMP("chomp.wav"),
    INTRO("intro.wav");
    private String name;
    private Clip clip;
    private static boolean mute = false;

    Voices(String url) {
        this.name = url;
        setMedia();
    }

    public static void mute() {
        mute = !mute;
    }

    private void setMedia() {
        URL url = HelloApplication.class.getResource("/voice/" + name);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            this.clip = clip;
        } catch (Exception ignored) {
        }
    }

    public void play() {
        if (!mute)
            clip.loop(1);
    }

    public void pause() {
        clip.stop();
    }

    public void loop() {
        if (!mute)
            clip.loop(-1);
    }
}
