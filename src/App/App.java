package App;

import Cards.Card;
import java.util.List;
import CardHolders.Deck;

import CardHolders.Hand;

public class App {
    public static void main(String[] args) {

        Deck deck = new Deck();
        List<Hand> hands = deck.getRandomHands();

        Hand hand1 = hands.getFirst();
        Hand hand2 = hands.getLast();

        for (int i = 0; i < 26; i++) {
            try {
                Card c = hand1.drawCard();
                System.out.println(i + 1 + ") " + c.getCardInfo());
            } catch (Exception e) {
                System.out.println("No more cards in hand");
            }
        }
        System.out.println("%%%%%%%%%%%%%%%%% Hand 2 %%%%%%%%%%%%%%%%%");
        for (int i = 0; i < 26; i++) {
            try {
                Card c = hand2.drawCard();
                System.out.println(i + 1 + ") " + c.getCardInfo());
            } catch (Exception e) {
                System.out.println("No more cards in hand");
            }
        }
    }
}
