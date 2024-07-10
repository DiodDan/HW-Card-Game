package VisualEngine;

import CustomEnums.ButtonState;
import CustomEnums.ButtonType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IconLoader {
    private final int buttonSubImageHeight = 26;
    private final int switchSubImageHeight = 47;
    private final int autoSwitchHeight = 47;
    private final int padding = 1;

    private final Map<ButtonType, Integer> buttonWidths;
    private final Map<ButtonType, Integer> switchHeights;
    private final Map<ButtonType, Integer> switchWidths;

    public IconLoader(Map<ButtonType, Integer> buttonWidths, Map<ButtonType, Integer> switchHeights, Map<ButtonType, Integer> switchWidths) {
        this.buttonWidths = buttonWidths;
        this.switchHeights = switchHeights;
        this.switchWidths = switchWidths;
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

    public HashMap<ButtonType, HashMap<ButtonState, Image>> loadButtonIcons(String themeName) throws RuntimeException {
        try {
            BufferedImage img = this.getImage("Images/" + themeName + ".png");
            HashMap<ButtonType, HashMap<ButtonState, Image>> buttonIcons = new HashMap<>();

            int xOffset = 0;

            for (ButtonType type : ButtonType.values()) {
                if (buttonWidths.containsKey(type)) {
                    HashMap<ButtonState, Image> stateIcons = new HashMap<>();
                    int buttonWidth = buttonWidths.get(type);

                    stateIcons.put(ButtonState.NORMAL, getButtonImage(img, xOffset, ButtonState.NORMAL.ordinal(), buttonWidth));
                    stateIcons.put(ButtonState.HOVER, getButtonImage(img, xOffset, ButtonState.HOVER.ordinal(), buttonWidth));
                    stateIcons.put(ButtonState.PRESSED, getButtonImage(img, xOffset, ButtonState.PRESSED.ordinal(), buttonWidth));

                    buttonIcons.put(type, stateIcons);
                    xOffset += buttonWidth + padding;
                }
            }

            return buttonIcons;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<ButtonType, HashMap<ButtonState, ImageIcon>> loadSwitchIcons(String themeName) throws RuntimeException {
        try {
            BufferedImage img = this.getImage("Images/" + themeName + ".png");
            HashMap<ButtonType, HashMap<ButtonState, ImageIcon>> switchIcons = new HashMap<>();

            int xOffset = 0;
            for (ButtonType type : ButtonType.values()) {
                if (this.switchWidths.containsKey(type)) {
                    HashMap<ButtonState, ImageIcon> stateIcons = new HashMap<>();
                    int switchWidth = this.switchWidths.get(type);
                    int switchHeight = this.switchHeights.get(type);
                    System.out.println("type" + type.toString());
                    System.out.println(xOffset);
                    System.out.println(switchHeight);
                    stateIcons.put(ButtonState.UNCHECKED, getSwitchImage(img, xOffset, 0, switchWidth, switchHeight));
                    stateIcons.put(ButtonState.CHECKED, getSwitchImage(img, xOffset, 1, switchWidth, switchHeight));

                    switchIcons.put(type, stateIcons);
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

        return img.getSubimage(
                xOffset,
                y,
                buttonWidth,
                this.buttonSubImageHeight
        ));
    }

    private ImageIcon getSwitchImage(BufferedImage img, int xOffset, int stateIndex, int switchWidth, int switchHeight) {
        int y = stateIndex * (switchHeight + this.padding);
        System.out.println(y);

        return new ImageIcon(img.getSubimage(
                xOffset,
                y,
                switchWidth,
                switchHeight
        ));
    }
}
