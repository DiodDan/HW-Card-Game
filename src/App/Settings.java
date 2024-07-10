package App;

import java.awt.*;

public class Settings {
    private final int widthAvatarFrame = 500;
    private final int heightAvatarFrame = 500;

    private final int widthMainFrame = 800;
    private final int heightMainFrame = 800;
    private final String title = "Card Game";
    private final int cardDistance = 40;
    private final Color bgColor = new Color(30, 7, 0);
    private final Color textColor = new Color(255, 144, 22);
    private final Color buttonBgColor = new Color(188, 13, 0);
    private final Color buttonFgColor = new Color(0, 0, 0);
    private final String savePrefix = "saves/";
    private final double cardScale = 4.4;
    private final int subImageWidth = 40;
    private final int subImageHeight = 66;
    private final int maxCardsOnTable = 7;
    private final int autoPlayStepsPerSecond = 10;
    private final String themeName = "cards1";

    public int getWidthAvatarFrame() {
        return this.widthAvatarFrame;
    }

    public int getHeightAvatarFrame() {
        return this.heightAvatarFrame;
    }

    public int getWidthMainFrame() {
        return this.widthMainFrame;
    }

    public int getHeightMainFrame() {
        return this.heightMainFrame;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCardDistance() {
        return this.cardDistance;
    }

    public Color getBgColor() {
        return this.bgColor;
    }

    public Color getTextColor() {
        return this.textColor;
    }

    public Color getButtonBgColor() {
        return this.buttonBgColor;
    }

    public Color getButtonFgColor() {
        return this.buttonFgColor;
    }

    public String getSavePrefix() {
        return this.savePrefix;
    }

    public double getCardScale() {
        return this.cardScale;
    }

    public int getSubImageWidth() {
        return this.subImageWidth;
    }

    public int getSubImageHeight() {
        return this.subImageHeight;
    }

    public int getMaxCardsOnTable() {
        return this.maxCardsOnTable;
    }
    public int getAutoPlayStepsPerSecond() {
        return this.autoPlayStepsPerSecond;
    }

    public String getThemeName() {
        return themeName;
    }
}
