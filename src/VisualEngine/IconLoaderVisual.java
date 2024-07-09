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
        buttonWidths.put(ButtonType.NEXT, 123);
        buttonWidths.put(ButtonType.LOAD, 61);
        buttonWidths.put(ButtonType.SAVE, 62);
        buttonWidths.put(ButtonType.PULL, 123);

        IconLoader iconLoader = new IconLoader(buttonWidths);

        try {
            HashMap<ButtonType, HashMap<ButtonState, Image>> buttonIcons = iconLoader.loadButtonIcons("buttons");

            JButton playButton = new JButton();
            playButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.NORMAL)));
            playButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.HOVER)));
            playButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.PRESSED)));

            JButton spoilerButton = new JButton();
            spoilerButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.SPOILER).get(ButtonState.NORMAL)));
            spoilerButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.SPOILER).get(ButtonState.HOVER)));
            spoilerButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.SPOILER).get(ButtonState.PRESSED)));

            JButton restartButton = new JButton();
            restartButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.RESTART).get(ButtonState.NORMAL)));
            restartButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.RESTART).get(ButtonState.HOVER)));
            restartButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.RESTART).get(ButtonState.PRESSED)));
            
            JButton nextButton = new JButton();
            nextButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.NEXT).get(ButtonState.NORMAL)));
            nextButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.NEXT).get(ButtonState.HOVER)));
            nextButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.NEXT).get(ButtonState.PRESSED)));

            JButton loadButton = new JButton();
            loadButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.LOAD).get(ButtonState.NORMAL)));
            loadButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.LOAD).get(ButtonState.HOVER)));
            loadButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.LOAD).get(ButtonState.PRESSED)));

            JButton saveButton = new JButton();
            saveButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.SAVE).get(ButtonState.NORMAL)));
            saveButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.SAVE).get(ButtonState.HOVER)));
            saveButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.SAVE).get(ButtonState.PRESSED)));

            JButton pullButton = new JButton();
            pullButton.setIcon(new ImageIcon(buttonIcons.get(ButtonType.PULL).get(ButtonState.NORMAL)));
            pullButton.setRolloverIcon(new ImageIcon(buttonIcons.get(ButtonType.PULL).get(ButtonState.HOVER)));
            pullButton.setPressedIcon(new ImageIcon(buttonIcons.get(ButtonType.PULL).get(ButtonState.PRESSED)));



            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());

            frame.add(playButton);
            frame.add(spoilerButton);
            frame.add(restartButton);
            frame.add(loadButton);
            frame.add(saveButton);
            frame.add(nextButton);
            frame.add(pullButton);

            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}