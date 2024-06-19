package App;

import java.awt.*;

public class Settings {
    private final int width = 800;
    private final int height = 800;
    private final String title = "Card Game";
    private final int cardDistance = 50;
    private final Color bgColor = new Color(30, 7, 0);
    private final Color textColor = new Color(255, 144, 22);
    private final Color buttonBgColor = new Color(188, 13, 0);
    private final Color buttonFgColor = new Color(0, 0, 0);
    private final String savePrefix = "saves/";

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
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
}
