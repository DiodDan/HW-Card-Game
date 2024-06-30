package VisualEngine;

import App.Settings;
import CustomEnums.Suit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CardLoader {
    private final Settings settings = new Settings();
    private final int cardsInSuitAmount = 13;
    private final int cardsAmount = 52;
    private final int subImageWidth = this.settings.getSubImageWidth();
    private final int subImageHeight = this.settings.getSubImageHeight();
    private final double scale = this.settings.getCardScale();
    private final int cardDistanceX = 24;
    private final int cardDistanceY = 30;

    private BufferedImage getImage(String path) throws IOException {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            try {
                if (CardLoader.class.getResource(path) != null) {
                    return ImageIO.read(CardLoader.class.getResource(path));
                }
                throw new IOException("Resource not found: " + path);

            } catch (IOException e) {
                throw new IOException(e);
            }
        }

    }

    public Image loadCardBack() {
        try {
            BufferedImage img = this.getImage("Images/cards1.png");
            return img.getSubimage(
                    12,
                    495,
                    this.subImageWidth,
                    this.subImageHeight).getScaledInstance(
                    (int) (this.subImageWidth * this.scale),
                    (int) (this.subImageHeight * this.scale),
                    Image.SCALE_SMOOTH
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Suit, Image[]> loadCardImages() throws RuntimeException {
        try {
            BufferedImage img = this.getImage("Images/cards1.png");

            Image[] scaledImages = new Image[this.cardsAmount];
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < this.cardsInSuitAmount; i++) {
                    scaledImages[i + j * this.cardsInSuitAmount] = img.getSubimage(
                            i * (this.cardDistanceX + this.subImageWidth) + 12,
                            15 + j * (this.cardDistanceY + this.subImageHeight),
                            this.subImageWidth,
                            this.subImageHeight).getScaledInstance(
                            (int) (this.subImageWidth * this.scale),
                            (int) (this.subImageHeight * this.scale),
                            Image.SCALE_AREA_AVERAGING
                    );
                }
            }
            HashMap<Suit, Image[]> cardImages = new HashMap<>();
            Image[] clubs = new Image[13];
            Image[] diamonds = new Image[13];
            Image[] hearts = new Image[13];
            Image[] spades = new Image[13];
            for (int i = 0; i < 13; i++) {
                hearts[i] = scaledImages[i];
                spades[i] = scaledImages[i + 13];
                clubs[i] = scaledImages[i + 26];
                diamonds[i] = scaledImages[i + 39];
            }
            cardImages.put(Suit.HEARTS, hearts);
            cardImages.put(Suit.CLUBS, clubs);
            cardImages.put(Suit.DIAMONDS, diamonds);
            cardImages.put(Suit.SPADES, spades);
            return cardImages;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}