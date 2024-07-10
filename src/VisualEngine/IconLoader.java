package VisualEngine;

import App.Settings;
import CustomEnums.ButtonState;
import CustomEnums.ButtonType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IconLoader {
    private final int buttonSubImageHeight = 26;
    private final int padding = 1;
    private final Map<ButtonType, Integer> buttonWidths = new HashMap<>();
    private final Map<ButtonType, Integer> switchHeights = new HashMap<>();
    private final Map<ButtonType, Integer> switchWidths = new HashMap<>();
    private final Settings settings = new Settings();

    public IconLoader() {

        this.buttonWidths.put(ButtonType.PLAY, 66);
        this.buttonWidths.put(ButtonType.SPOILER, 75);
        this.buttonWidths.put(ButtonType.RESTART, 90);
        this.buttonWidths.put(ButtonType.NEXT, 123);
        this.buttonWidths.put(ButtonType.LOAD, 61);
        this.buttonWidths.put(ButtonType.SAVE, 62);
        this.buttonWidths.put(ButtonType.PULL, 123);

        this.switchHeights.put(ButtonType.AUTO, 47); //height
        this.switchHeights.put(ButtonType.MUTE, 47);

        this.switchWidths.put(ButtonType.AUTO, 34); // width
        this.switchWidths.put(ButtonType.MUTE, 26);
    }

    private BufferedImage getImage(String path) throws IOException {
        try {
            File file = new File(path);

            if (file.exists()) {
                return ImageIO.read(file);
            }

            URL resourceUrl = getClass().getResource(path);

            if (resourceUrl != null) {
                return ImageIO.read(resourceUrl);
            }

            throw new IOException("Resource not found: " + path);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public HashMap<ButtonType, JButton> loadButtonIcons(String themeName) throws RuntimeException {
        try {
            BufferedImage img = this.getImage("Images/" + themeName + ".png");
            HashMap<ButtonType, JButton> buttonIcons = new HashMap<>();

            int xOffset = 0;

            for (ButtonType type : ButtonType.values()) {
                if (this.buttonWidths.containsKey(type)) {
                    int buttonWidth = this.buttonWidths.get(type);


                    JButton button = this.createButton(
                            getButtonImage(img, xOffset, ButtonState.NORMAL.ordinal(), buttonWidth),
                            getButtonImage(img, xOffset, ButtonState.HOVER.ordinal(), buttonWidth),
                            getButtonImage(img, xOffset, ButtonState.PRESSED.ordinal(), buttonWidth),
                            this.buttonWidths.get(type) * this.settings.getButtonScale(),
                            26 * this.settings.getButtonScale()
                    );

                    buttonIcons.put(type, button);
                    xOffset += buttonWidth + this.padding;
                }
            }

            return buttonIcons;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<ButtonType, JToggleButton> loadSwitchIcons(String themeName) throws RuntimeException {
        try {
            BufferedImage img = this.getImage("Images/" + themeName + ".png");
            HashMap<ButtonType, JToggleButton> switchIcons = new HashMap<>();

            int xOffset = 0;
            for (ButtonType type : ButtonType.values()) {
                if (this.switchWidths.containsKey(type)) {
                    int switchWidth = this.switchWidths.get(type);
                    int switchHeight = this.switchHeights.get(type);

                    JToggleButton toggleButton = this.createSwitch(
                            getSwitchImage(img, xOffset, 0, switchWidth, switchHeight),
                            getSwitchImage(img, xOffset, 1, switchWidth, switchHeight),
                            switchWidths.get(type) * this.settings.getButtonScale(),
                            switchHeights.get(type) * this.settings.getButtonScale()
                    );


                    switchIcons.put(type, toggleButton);
                    xOffset += switchWidth + this.padding;
                }
            }

            return switchIcons;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ImageIcon getButtonImage(BufferedImage img, int xOffset, int stateIndex, int buttonWidth) {
        int y = stateIndex * (this.buttonSubImageHeight + this.padding);

        return new ImageIcon(img.getSubimage(
                xOffset,
                y,
                buttonWidth,
                this.buttonSubImageHeight
        ).getScaledInstance(
                buttonWidth * this.settings.getButtonScale(),
                this.buttonSubImageHeight * this.settings.getButtonScale(),
                Image.SCALE_SMOOTH
        ));
    }

    private ImageIcon getSwitchImage(BufferedImage img, int xOffset, int stateIndex, int switchWidth, int switchHeight) {
        int y = stateIndex * (switchHeight + this.padding);

        return new ImageIcon(img.getSubimage(
                xOffset,
                y,
                switchWidth,
                switchHeight
        ).getScaledInstance(
                switchWidth * this.settings.getButtonScale(),
                switchHeight * this.settings.getButtonScale(),
                Image.SCALE_SMOOTH
        ));
    }

    private JButton createButton(ImageIcon icon, ImageIcon rolloverIcon, ImageIcon pressedIcon, int width, int height) {
        JButton button = new JButton();
        button.setIcon(icon);
        button.setRolloverIcon(rolloverIcon);
        button.setPressedIcon(pressedIcon);
        button.setSize(new Dimension(width, height));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private JToggleButton createSwitch(ImageIcon uncheckedIcon, ImageIcon checkedIcon, int width, int height) {
        JToggleButton toggleButton = new JToggleButton();
        toggleButton.setIcon(uncheckedIcon);
        toggleButton.setSelectedIcon(checkedIcon);
        toggleButton.setSize(new Dimension(width, height));
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false);
        return toggleButton;
    }
}
