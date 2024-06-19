package CardHolders;

import java.util.ArrayList;
import java.util.List;
import Cards.Card;

public class Hand {
    private final List<Card> cards;

    public Hand(){
        this.cards = new ArrayList<Card>();
    }
    public Hand(List<Card> cards){
        this.cards = cards;
    }

    public Card drawCard() throws Exception{
        try{
            return this.cards.remove(this.cards.size() - 1);
        }
        catch (java.util.NoSuchElementException e){
            throw new Exception(e);
        }
    }

    public void putCard(Card card) {
        this.cards.add(card);
    }

    public int getCardAmount(){
        return this.cards.size();
    }

    public Hand copy(){
        return new Hand(new ArrayList<Card>(this.cards));
    }
}
