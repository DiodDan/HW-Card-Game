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
    Settings settings = new Settings();
    int cardsInSuitAmount = 13;
    int cardsAmount = 52;
    int subImageWidth = this.settings.getSubImageWidth();
    int subImageHeight = this.settings.getSubImageHeight();
    double scale = this.settings.getCardScale();
    int cardDistanceX = 24;
    int cardDistanceY = 30;

    public Image loadCardBack() {
        try {
            BufferedImage img = ImageIO.read(new File("Images/cards.png"));
            Image[] scaledBacks = new Image[3];
            scaledBacks[0] = img.getSubimage(
                    12,
                    495,
                    this.subImageWidth,
                    this.subImageHeight).getScaledInstance(
                    (int) (this.subImageWidth * this.scale),
                    (int) (this.subImageHeight * this.scale),
                    Image.SCALE_SMOOTH
            );
            scaledBacks[1] = img.getSubimage(
                    76,
                    495,
                    this.subImageWidth,
                    this.subImageHeight).getScaledInstance(
                    (int) (this.subImageWidth * this.scale),
                    (int) (this.subImageHeight * this.scale),
                    Image.SCALE_SMOOTH
            );
            scaledBacks[2] = img.getSubimage(
                    140,
                    495,
                    this.subImageWidth,
                    this.subImageHeight).getScaledInstance(
                    (int) (this.subImageWidth * this.scale),
                    (int) (this.subImageHeight * this.scale),
                    Image.SCALE_SMOOTH
            );
            return scaledBacks[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Suit, Image[]> loadCardImages() throws RuntimeException {
        try {
            BufferedImage img = ImageIO.read(new File("Images/cards.png"));

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