package Predictor;

import Cards.Hand;
import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Predictor {
    private int getLastCardValue(List<Card> cards) {
        return cards.get(cards.size() - 1).getValue();
    }

    public Prediction predictWinner(Hand hand1, Hand hand2) {
        int steps = 0;
        Hand hand1_c = hand1.copy();
        Hand hand2_c = hand2.copy();

        List<Card> cardsOnTable1 = new ArrayList<Card>();
        List<Card> cardsOnTable2 = new ArrayList<Card>();

        while (hand1_c.getCardAmount() > 0 && hand2_c.getCardAmount() > 0) {
            try {
                cardsOnTable1.add(hand1_c.drawCard());
                cardsOnTable2.add(hand2_c.drawCard());
                if (this.getLastCardValue(cardsOnTable1) > this.getLastCardValue(cardsOnTable2)) {
                    for (Card card : cardsOnTable1)
                        hand1_c.putCard(card);
                    cardsOnTable1.clear();
                    for (Card card : cardsOnTable2)
                        hand1_c.putCard(card);
                    cardsOnTable2.clear();
                } else if (this.getLastCardValue(cardsOnTable1) == this.getLastCardValue(cardsOnTable2)) {
                    if (hand1_c.getCardAmount() < 3) {
                        return new Prediction(2, steps);
                    } else if (hand2_c.getCardAmount() < 3) {
                        return new Prediction(1, steps);
                    } else {
                        cardsOnTable1.add(hand1_c.drawCard());
                        cardsOnTable1.add(hand1_c.drawCard());
                        cardsOnTable2.add(hand2_c.drawCard());
                        cardsOnTable2.add(hand2_c.drawCard());
                    }
                    steps++;
                    continue;
                } else {
                    for (Card card : cardsOnTable1)
                        hand2_c.putCard(card);
                    cardsOnTable1.clear();
                    for (Card card : cardsOnTable2)
                        hand2_c.putCard(card);
                    cardsOnTable2.clear();
                }
                steps++;
                if (steps > 100000) {
                    return new Prediction(0, 99999999);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return new Prediction(hand1_c.getCardAmount() > 0 ? 1 : 2, steps);
    }
}
