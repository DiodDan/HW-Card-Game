package Cards;



import CustomEnums.Suit;


public class Card {
    private final Suit suit;
    private final int value;

    public Card(Suit name, int value) {
        this.suit = name;
        this.value = value;
    }

    public Suit getName() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    public String getCardInfo() {
        return this.getName() + " " + this.getValue();
    }
}
