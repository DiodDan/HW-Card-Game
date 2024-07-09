package Cards;


import CustomEnums.Suit;

import java.awt.*;


public class Card {
    private final Suit suit;
    private final int value;
    private Image image;

    public Card(Suit name, int value, Image image) {
        this.suit = name;
        this.value = value;
        this.image = image;
    }

    public Suit getName() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    public Image getImage() {
        return this.image;
    }

    public String getCardInfo() {
        return this.getName() + " " + this.getValue();
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
