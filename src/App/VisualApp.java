package App;

import SoundEngine.SoundEngine;

public class VisualApp {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setupUI();
        SoundEngine playMusic = new SoundEngine();
        playMusic.playMusic("sounds/shakeSound.wav", 0.5f);
        playMusic.playMusic("sounds/background.wav", 0.01f);
    }
}
