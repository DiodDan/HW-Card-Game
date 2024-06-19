package CardHolders;

import Cards.Card;
import CustomEnums.Suit;
import VisualEngine.CardLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards = new ArrayList<Card>();
    private Random rand = new Random();

    public Deck() {
        CardLoader cardLoader = new CardLoader();
        HashMap<Suit, Image[]> cardImages = cardLoader.loadCardImages();
        for (Suit suit : Suit.values()) {
            for (int i = 1; i < 14; i++) {
                this.cards.add(new Card(suit, i, cardImages.get(suit)[i - 1]));
            }
        }
    }

    public Card drawCard() throws Exception {
        try {
            return this.cards.removeLast();
        } catch (java.util.NoSuchElementException e) {
            throw new Exception(e);
        }
    }

    public void putCard(Card card) {
        this.cards.add(card);
    }

    public List<Hand> getRandomHands() {
        List<Hand> hands = new ArrayList<Hand>();
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
