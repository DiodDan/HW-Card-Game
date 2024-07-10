package VisualEngine;

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

        Map<ButtonType, Integer> switchHeights = new HashMap<>();
        switchHeights.put(ButtonType.AUTO, 47); //height
        switchHeights.put(ButtonType.MUTE, 47);

        Map<ButtonType, Integer> switchWidths = new HashMap<>();
        switchWidths.put(ButtonType.AUTO, 34); // width
        switchWidths.put(ButtonType.MUTE, 26); 

        IconLoader iconLoader = new IconLoader(buttonWidths, switchHeights, switchWidths);

        try {
            HashMap<ButtonType, HashMap<ButtonState, ImageIcon>> buttonIcons = iconLoader.loadButtonIcons("buttons");
            HashMap<ButtonType, HashMap<ButtonState, ImageIcon>> switchIcons = iconLoader.loadSwitchIcons("switches");

            JButton playButton = createButton(
                    buttonIcons.get(ButtonType.PLAY).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.PLAY).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.PLAY).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.PLAY),
                    26
            );

            JButton spoilerButton = createButton(
                    buttonIcons.get(ButtonType.SPOILER).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.SPOILER).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.SPOILER).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.SPOILER),
                    26
            );

            JButton restartButton = createButton(
                    buttonIcons.get(ButtonType.RESTART).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.RESTART).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.RESTART).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.RESTART),
                    26
            );

            JButton nextButton = createButton(
                    buttonIcons.get(ButtonType.NEXT).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.NEXT).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.NEXT).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.NEXT),
                    26
            );

            JButton loadButton = createButton(
                    buttonIcons.get(ButtonType.LOAD).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.LOAD).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.LOAD).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.LOAD),
                    26
            );

            JButton saveButton = createButton(
                    buttonIcons.get(ButtonType.SAVE).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.SAVE).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.SAVE).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.SAVE),
                    26
            );

            JButton pullButton = createButton(
                    buttonIcons.get(ButtonType.PULL).get(ButtonState.NORMAL),
                    buttonIcons.get(ButtonType.PULL).get(ButtonState.HOVER),
                    buttonIcons.get(ButtonType.PULL).get(ButtonState.PRESSED),
                    buttonWidths.get(ButtonType.PULL),
                    26
            );

            JToggleButton autoSwitch = createSwitch(
                    switchIcons.get(ButtonType.AUTO).get(ButtonState.UNCHECKED),
                    switchIcons.get(ButtonType.AUTO).get(ButtonState.CHECKED),
                    switchWidths.get(ButtonType.AUTO),
                    switchHeights.get(ButtonType.AUTO)
            );

            JToggleButton muteSwitch = createSwitch(
                    switchIcons.get(ButtonType.MUTE).get(ButtonState.UNCHECKED),
                    switchIcons.get(ButtonType.MUTE).get(ButtonState.CHECKED),
                    switchWidths.get(ButtonType.MUTE),
                    switchHeights.get(ButtonType.MUTE)
            );

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

    private static JButton createButton(ImageIcon icon, ImageIcon rolloverIcon, ImageIcon pressedIcon, int width, int height) {
        JButton button = new JButton();
        button.setIcon(icon);
        button.setRolloverIcon(rolloverIcon);
        button.setPressedIcon(pressedIcon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private static JToggleButton createSwitch(ImageIcon uncheckedIcon, ImageIcon checkedIcon, int width, int height) {
        JToggleButton toggleButton = new JToggleButton();
        toggleButton.setIcon(uncheckedIcon);
        toggleButton.setSelectedIcon(checkedIcon);
        toggleButton.setPreferredSize(new Dimension(width, height));
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false);
        return toggleButton;
    }
}
