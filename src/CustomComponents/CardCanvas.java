package CustomComponents;

import App.Settings;
import VisualEngine.CardLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardCanvas extends Canvas {
    Settings settings = new Settings();
    List<Image> cardImages = new ArrayList<Image>();
    CardLoader cardLoader = new CardLoader();

    @Override
    public void paint(Graphics g) {
        if (!this.cardImages.isEmpty()) {
            for (int i = 0; i < this.cardImages.size(); i++) {
                g.drawImage(this.cardImages.get(i), 0, i * settings.getCardDistance(), this);
            }
        }
    }

    public void addCardImage(Image cardImage) {
        this.cardImages.add(cardImage);
        this.repaint();
        this.setSize(
                this.cardImages.getFirst().getWidth(null),
                this.cardImages.getFirst().getHeight(null) + (cardImages.size() - 1) * settings.getCardDistance()
        );
    }
    public void clearAndSetBack() {
        this.cardImages.clear();
        this.cardImages.add(cardLoader.loadCardBack());
        this.repaint();
        this.setSize(
                this.cardImages.getFirst().getWidth(null),
                this.cardImages.getFirst().getHeight(null) + cardImages.size() * settings.getCardDistance()
        );
    }
}