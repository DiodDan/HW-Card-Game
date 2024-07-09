package Cards;

import java.util.ArrayList;
import java.util.List;

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
            return this.cards.remove(0);
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

    public String serialize(){
        StringBuilder serialized = new StringBuilder();
        for (Card card : this.cards){
            serialized.append(card.getCardInfo()).append(";");
        }
        return serialized.toString();
    }

//    public void repaintCards(){
//        for (Card card : this.cards){
//
//        }
//    }
}
