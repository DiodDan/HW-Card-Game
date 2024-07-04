package Cards;

import App.Settings;
import CustomEnums.Suit;
import VisualEngine.CardLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final Random rand = new Random();
    private final Settings settings = new Settings();

    public Deck() {
        CardLoader cardLoader = new CardLoader();
        HashMap<Suit, Image[]> cardImages = cardLoader.loadCardImages(this.settings.getThemeName());
        for (Suit suit : Suit.values()) {
            for (int i = 2; i < 14; i++) {
                this.cards.add(new Card(suit, i, cardImages.get(suit)[i - 1]));
            }
            this.cards.add(new Card(suit, 14, cardImages.get(suit)[0]));
        }
    }

    public Card drawCard() throws Exception {
        try {
            return this.cards.remove(this.cards.size() - 1);
        } catch (java.util.NoSuchElementException e) {
            throw new Exception(e);
        }
    }

    public void putCard(Card card) {
        this.cards.add(card);
    }

    public List<Hand> getRandomHands() {
        List<Hand> hands = new ArrayList<>();
        hands.add(new Hand());
        hands.add(new Hand());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26; j++) {
//                hands.get(i).putCard(this.cards.removeFirst());
                hands.get(i).putCard(this.cards.remove(this.rand.nextInt(this.cards.size())));
            }
        }
        return hands;
    }
}
