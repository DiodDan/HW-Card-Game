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
    private final int subImageHeight = 26;
    private final int padding = 1;

    private final Map<ButtonType, Integer> buttonWidths;

    public IconLoader(Map<ButtonType, Integer> buttonWidths) {
        this.buttonWidths = buttonWidths;
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

    private Image getButtonImage(BufferedImage img, int xOffset, int stateIndex, int buttonWidth) {
        int y = stateIndex * (subImageHeight + padding);

        return img.getSubimage(
                xOffset,
                y,
                buttonWidth,
                subImageHeight
        );
    }
}