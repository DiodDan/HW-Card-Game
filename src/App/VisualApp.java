package App;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CardHolders.Deck;
import CardHolders.Hand;
import Cards.Card;
import CustomComponents.CardCanvas;
import Predictor.Predictor;

import javax.imageio.ImageIO;

public class VisualApp {
    Deck deck = new Deck();
    List<Hand> hands = deck.getRandomHands();
    Hand hand1 = hands.get(0);
    Hand hand2 = hands.get(hands.size() - 1);
    Settings settings = new Settings();
    Frame frame = new Frame(settings.getTitle());

    CardCanvas cardCanvas1 = new CardCanvas();
    CardCanvas cardCanvas2 = new CardCanvas();

    Label handCount1 = new Label("Player 1: " + this.hand1.getCardAmount() + " cards");
    Label handCount2 = new Label("Player 2: " + this.hand2.getCardAmount() + " cards");

    Label statusLabel = new Label("Player 1's turn");
    Label spoilerLabel = new Label("");

    List<Card> cardsOnTable1 = new ArrayList<>();
    List<Card> cardsOnTable2 = new ArrayList<>();

    Button turnButton = new Button("Pull Cards");
    Button spoilerButton = new Button("Spoiler");

    Predictor predictor = new Predictor();

    private int getCardsSum(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public void showSpoiler() {
        if (this.spoilerLabel.getText().isEmpty()) {
            this.spoilerLabel.setText(predictor.predictWinner(this.hand1, this.hand2).toString());
        } else {
            this.spoilerLabel.setText("");
        }
    }

    public void drawCard() {
        try {
            if (this.turnButton.getLabel().equals("Next round")) {
                this.turnButton.setLabel("Pull Cards");
                this.statusLabel.setText("Game in progress...");
                this.cardCanvas1.clearAndSetBack();
                this.cardCanvas2.clearAndSetBack();
                handCount1.setText("Player 1: " + this.hand1.getCardAmount() + " cards");
                handCount2.setText("Player 2: " + this.hand2.getCardAmount() + " cards");
                return;
            }
            if (this.hand1.getCardAmount() == 0) {
                this.statusLabel.setText("Player 2 wins the game!");
                this.turnButton.setEnabled(false);
                return;
            }
            if (this.hand2.getCardAmount() == 0) {
                this.statusLabel.setText("Player 1 wins the game!");
                this.turnButton.setEnabled(false);
                return;
            }
            // draw new cards
            this.cardsOnTable1.add(this.hand1.drawCard());
            this.cardsOnTable2.add(this.hand2.drawCard());

            this.cardCanvas1.addCardImage(cardsOnTable1.get(this.cardsOnTable1.size() - 1).getImage());
            this.cardCanvas2.addCardImage(cardsOnTable2.get(this.cardsOnTable2.size() - 1).getImage());


            if (this.getCardsSum(this.cardsOnTable1) != this.getCardsSum(this.cardsOnTable2)) {
                if (this.getCardsSum(this.cardsOnTable1) > this.getCardsSum(this.cardsOnTable2)) {
                    this.statusLabel.setText("Player 1 wins the round!");
                    for (Card card : this.cardsOnTable1)
                        this.hand1.putCard(card);
                    this.cardsOnTable1.clear();
                    for (Card card : this.cardsOnTable2)
                        this.hand1.putCard(card);
                    this.cardsOnTable2.clear();
                } else {
                    this.statusLabel.setText("Player 2 wins the round!");
                    for (Card card : this.cardsOnTable1)
                        this.hand2.putCard(card);
                    this.cardsOnTable1.clear();
                    for (Card card : this.cardsOnTable2)
                        this.hand2.putCard(card);
                    this.cardsOnTable2.clear();
                }
                this.turnButton.setLabel("Next round");
            } else {
                this.statusLabel.setText("Draw! Each player gets one more card.");
            }


        } catch (Exception e) {
            System.out.println("No more cards in hand");
        }
    }

    public void runGame() {
        // ############################### Main GUI setup ###################################
        try {
            Image icon = ImageIO.read(new File("Images/gameIcon.png")); // Replace with your icon file path
            this.frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.cardCanvas1.setBackground(settings.getBgColor());
        this.cardCanvas1.setLocation(50, 150);
        this.cardCanvas1.clearAndSetBack();
        this.frame.add(this.cardCanvas1);

        this.cardCanvas2.setBackground(settings.getBgColor());
        this.cardCanvas2.setLocation(550, 150);
        this.cardCanvas2.clearAndSetBack();
        this.frame.add(this.cardCanvas2);

        this.handCount1.setSize(200, 50);
        this.handCount1.setLocation(50, 100);
        this.handCount1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        this.handCount1.setBackground(settings.getBgColor());
        this.handCount1.setForeground(settings.getTextColor());
        this.frame.add(this.handCount1);

        this.handCount2.setSize(200, 50);
        this.handCount2.setLocation(550, 100);
        this.handCount2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        this.handCount2.setBackground(settings.getBgColor());
        this.handCount2.setForeground(settings.getTextColor());
        this.frame.add(this.handCount2);

        this.statusLabel.setSize(400, 50);
        this.statusLabel.setLocation(200, 670);
        this.statusLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        this.statusLabel.setBackground(settings.getBgColor());
        this.statusLabel.setForeground(settings.getTextColor());
        this.frame.add(this.statusLabel);

        this.spoilerLabel.setSize(200, 30);
        this.spoilerLabel.setLocation(200, 50);
        this.spoilerLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        this.spoilerLabel.setBackground(settings.getBgColor());
        this.spoilerLabel.setForeground(settings.getTextColor());
        this.frame.add(this.spoilerLabel);

        this.frame.setSize(this.settings.getWidth(), this.settings.getHeight());
        this.frame.setLayout(null);
        this.frame.setVisible(true);

        this.frame.setBackground(settings.getBgColor());


        this.turnButton.setSize(400, 50);
        this.turnButton.setLocation(170, 730);
        this.turnButton.setFont(new Font("Arial", Font.PLAIN, 30));
        this.turnButton.setBackground(Color.RED);
        this.turnButton.setForeground(Color.BLACK);
        this.turnButton.addActionListener(e -> this.drawCard());
        this.frame.add(this.turnButton);

        this.spoilerButton.setSize(150, 30);
        this.spoilerButton.setLocation(30, 40);
        this.spoilerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        this.spoilerButton.setBackground(Color.RED);
        this.spoilerButton.setForeground(Color.BLACK);
        this.spoilerButton.addActionListener(e -> this.showSpoiler());
        this.frame.add(this.spoilerButton);

        // add window listener to handle window closing event
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        VisualApp app = new VisualApp();
        app.runGame();
    }
}
