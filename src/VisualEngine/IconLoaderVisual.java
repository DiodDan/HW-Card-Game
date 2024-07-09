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

            JButton playButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.PLAY).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.PLAY),
                    26
            );

            JButton spoilerButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.SPOILER).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.SPOILER).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.SPOILER).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.SPOILER),
                    26
            );

            JButton restartButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.RESTART).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.RESTART).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.RESTART).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.RESTART),
                    26
            );

            JButton nextButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.NEXT).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.NEXT).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.NEXT).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.NEXT),
                    26
            );

            JButton loadButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.LOAD).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.LOAD).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.LOAD).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.LOAD),
                    26
            );

            JButton saveButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.SAVE).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.SAVE).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.SAVE).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.SAVE),
                    26
            );

            JButton pullButton = createButton(
                    new ImageIcon(buttonIcons.get(ButtonType.PULL).get(ButtonState.NORMAL)),
                    new ImageIcon(buttonIcons.get(ButtonType.PULL).get(ButtonState.HOVER)),
                    new ImageIcon(buttonIcons.get(ButtonType.PULL).get(ButtonState.PRESSED)),
                    buttonWidths.get(ButtonType.PULL),
                    26
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
}
