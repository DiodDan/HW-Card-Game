package SoundEngine;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;



/** Function that creates sound */
public class SoundEngine {
    /** method to create a pullSound **/
    public void pullSound(float volume) {
        playSound("sounds/pullSound.wav", volume);
    }
    /** method to create a winSound **/
    public void winSound(float volume) {
        playSound("sounds/winSound.wav", volume);
    }
    /** method to create a gameSound **/
    public void gameSound(float volume) {
        playSound("sounds/background.wav", volume);
    }
    /** method to create a shakeSound **/
    public void shakeSound(float volume) {
        playSound("sounds/shakeSound.wav", volume);
    }

    /** method to play and control the volume **/
    private void playSound(String soundFilePath, float volume) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}


