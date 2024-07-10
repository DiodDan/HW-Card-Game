package App;

import SoundEngine.SoundEngine;

public class VisualApp {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setupUI();
        SoundEngine playMusic = new SoundEngine();
        playMusic.shakeSound(0.3f);
        playMusic.gameSound(0.05f);
    }
}
