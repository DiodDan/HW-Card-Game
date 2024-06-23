package CustomComponents;

import App.Settings;
import VisualEngine.CardLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CardCanvas extends Canvas {
    private final Settings settings = new Settings();
    private final List<Image> cardImages = new ArrayList<>();
    private final CardLoader cardLoader = new CardLoader();
    private BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);


    @Override
    public void paint(Graphics g) {
        g.drawImage(this.bufferedImage, 0, 0, this);
    }

    public void addCardImage(Image cardImage) {
        this.cardImages.add(cardImage);
        this.drawBufferedImage();
    }

    public void addCardBacks(Integer amount) {
        for (int i = 0; i < amount; i++) {
            this.cardImages.add(cardLoader.loadCardBack());
        }
        this.drawBufferedImage();
    }

    public void clearAndSetBack() {
        this.cardImages.clear();
        this.cardImages.add(cardLoader.loadCardBack());
        this.drawBufferedImage();
    }

    private void drawBufferedImage() {
        this.bufferedImage = new BufferedImage(
                this.cardImages.get(0).getWidth(null),
                this.cardImages.get(0).getHeight(null) + (cardImages.size() - 1) * settings.getCardDistance(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics bg = this.bufferedImage.getGraphics();

        if (!this.cardImages.isEmpty()) {
            for (int i = 0; i < this.cardImages.size(); i++) {
                bg.drawImage(this.cardImages.get(i), 0, i * settings.getCardDistance(), this);
            }
        }
        this.repaint();
        bg.dispose();
    }
}