package ProgressEngine;

import App.Settings;
import CardHolders.Hand;
import Cards.Card;
import CustomEnums.Suit;
import VisualEngine.CardLoader;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StateEngine {
    Settings settings = new Settings();
    CardLoader cardLoader = new CardLoader();
    HashMap<Suit, Image[]> cardImages = cardLoader.loadCardImages();


    public void saveState(Hand hand1, Hand hand2, String saveName) throws IOException {
        // Save the state of the game
        PrintWriter writer = new PrintWriter(new FileWriter(new File(this.settings.getSavePrefix() + saveName + ".txt")));
        writer.println(hand1.serialize() + "\n" + hand2.serialize());
        writer.close();
    }

    private List<Card> deserializeHand(String serializedHand) {
        List<Card> cards = new ArrayList<>();
        String[] cardStrings = serializedHand.split(";");

        for (String cardString : cardStrings) {
            String[] cardInfo = cardString.split(" ");
            Suit suit = Suit.valueOf(cardInfo[0]);
            int number = Integer.parseInt(cardInfo[1]);

            Image[] suitImages = this.cardImages.get(suit);
            int index = (number == 14) ? 0 : number - 1;

            cards.add(new Card(suit, number, suitImages[index]));
        }

        return cards;
    }


    public List<Hand> loadState(String loadName) throws IOException {
        // Load the state of the game
        BufferedReader reader = new BufferedReader(new FileReader(new File(this.settings.getSavePrefix() + loadName + ".txt")));
        String hand1Serialized = reader.readLine();
        String hand2Serialized = reader.readLine();
        reader.close();


        List<Hand> hands = new ArrayList<Hand>();

        hands.add(new Hand(this.deserializeHand(hand1Serialized)));
        hands.add(new Hand(this.deserializeHand(hand2Serialized)));

        return hands;
    }
    public String[] getAvailableSaves(){
        File folder = new File(this.settings.getSavePrefix());
        File[] listOfFiles = folder.listFiles();
        List<String> saves = new ArrayList<String>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")){
                saves.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
            }
        }
        return saves.toArray(new String[0]);
    }
}
