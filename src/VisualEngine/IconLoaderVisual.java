package VisualEngine;

import VisualEngine.IconLoader;
import CustomEnums.ButtonType;
import CustomEnums.ButtonState;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class IconLoaderVisual {
    public static void main(String[] args) {

        Map<ButtonType, Integer> buttonWidths = new HashMap<>();
        buttonWidths.put(ButtonType.PLAY, 66);
        buttonWidths.put(ButtonType.SPOILER, 75);
        buttonWidths.put(ButtonType.RESTART, 90);
        buttonWidths.put(ButtonType.LOAD, 61);
        buttonWidths.put(ButtonType.SAVE, 62);
        buttonWidths.put(ButtonType.PULL, 123);
        buttonWidths.put(ButtonType.NEXT, 123);
        buttonWidths.put(ButtonType.MUTE, 26);
        buttonWidths.put(ButtonType.AUTO, 34);

        IconLoader iconLoader = new IconLoader(buttonWidths);

        try {
            HashMap<ButtonType, HashMap<ButtonState, Image>> buttonIcons = iconLoader.loadButtonIcons("icons");

            JButton playButton = new JButton();
            playButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.NORMAL)));
            playButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.HOVER)));
            playButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.PRESSED)));

            JButton muteButton = new JButton();
            muteButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.MUTE).get(ButtonState.UNCHECKED)));
            muteButton.setSelectedIcon(new ImageIcon(buttonIcons.get(ButtonType.MUTE).get(ButtonState.CHECKED)));
            
            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.add(playButton);
            frame.add(muteButton);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}