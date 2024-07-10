package VisualEngine;

import CustomEnums.ButtonType;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class IconLoaderVisual {
    public static void main(String[] args) {
        IconLoader iconLoader = new IconLoader();

        try {
            HashMap<ButtonType, JButton> buttonIcons = iconLoader.loadButtonIcons("buttons");
            HashMap<ButtonType, JToggleButton> switchIcons = iconLoader.loadSwitchIcons("switches");

            JButton playButton = buttonIcons.get(ButtonType.PLAY);
            JButton spoilerButton = buttonIcons.get(ButtonType.SPOILER);
            JButton restartButton = buttonIcons.get(ButtonType.RESTART);
            JButton nextButton = buttonIcons.get(ButtonType.NEXT);
            JButton loadButton = buttonIcons.get(ButtonType.LOAD);
            JButton saveButton = buttonIcons.get(ButtonType.SAVE);
            JButton pullButton = buttonIcons.get(ButtonType.PULL);


            JToggleButton autoSwitch = switchIcons.get(ButtonType.AUTO);
            JToggleButton muteSwitch = switchIcons.get(ButtonType.MUTE);

            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());

            frame.add(playButton);
            frame.add(spoilerButton);
            frame.add(restartButton);
            frame.add(loadButton);
            frame.add(saveButton);
            frame.add(nextButton);
            frame.add(pullButton);
            frame.add(autoSwitch);
            frame.add(muteSwitch);

            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
