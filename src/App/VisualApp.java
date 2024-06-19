package App;

import java.awt.*;
import java.awt.event.ActionListener;
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
import ProgressEngine.StateEngine;

import javax.imageio.ImageIO;
import javax.swing.*;

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

    Label statusLabel = new Label("Game in progress...");
    Label spoilerLabel = new Label("");

    List<Card> cardsOnTable1 = new ArrayList<>();
    List<Card> cardsOnTable2 = new ArrayList<>();

    Button turnButton = new Button("Pull Cards");
    Button spoilerButton = new Button("Spoiler");
    Button resturtButton = new Button("Restart Game");
    Button saveButton = new Button("Save Game");
    Button loadButton = new Button("Load Game");

    Predictor predictor = new Predictor();

    StateEngine stateSaver = new StateEngine();

    private int getCardsSum(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    private void restartGame() {
        this.deck = new Deck();
        this.hands = deck.getRandomHands();
        this.hand1 = hands.get(0);
        this.hand2 = hands.get(hands.size() - 1);
        this.cardCanvas1.clearAndSetBack();
        this.cardCanvas2.clearAndSetBack();
        this.handCount1.setText("Player 1: " + this.hand1.getCardAmount() + " cards");
        this.handCount2.setText("Player 2: " + this.hand2.getCardAmount() + " cards");
        this.statusLabel.setText("Game in progress...");
        this.turnButton.setLabel("Pull Cards");
        this.turnButton.setEnabled(true);
    }

    private void saveGame() {
        String userInput = JOptionPane.showInputDialog("Enter name for this save:");
        try {
            this.stateSaver.saveState(this.hand1, this.hand2, userInput);
        } catch (Exception e) {
            System.out.println("Error saving the game");
        }
    }

    private void loadGame() {

        Object[] options = this.stateSaver.getAvailableSaves();
        Object selectedValue = JOptionPane.showInputDialog(null, "Choose load name", "Input", JOptionPane.INFORMATION_MESSAGE, null, options, null);

        try {
            this.hands = this.stateSaver.loadState(selectedValue.toString());
            this.hand1 = hands.get(0);
            this.hand2 = hands.get(hands.size() - 1);
            this.cardsOnTable1.clear();
            this.cardsOnTable2.clear();
            this.cardCanvas1.clearAndSetBack();
            this.cardCanvas2.clearAndSetBack();
            handCount1.setText("Player 1: " + this.hand1.getCardAmount() + " cards");
            handCount2.setText("Player 2: " + this.hand2.getCardAmount() + " cards");
        } catch (Exception e) {
//            System.out.println("Error loading the game");
            throw new RuntimeException("Error loading the game");
        }


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
        this.setupFrame();

        this.setupCanvas(this.cardCanvas1, 50, 150);
        this.setupCanvas(this.cardCanvas2, 550, 150);

        this.setupLabel(handCount1, 50, 100, 200, 50, 20);
        this.setupLabel(handCount2, 550, 100, 200, 50, 20);
        this.setupLabel(statusLabel, 200, 670, 400, 50, 20);
        this.setupLabel(spoilerLabel, 300, 300, 200, 30, 20);


        this.setupButton(this.spoilerButton, 50, 40, 125, 30, 20, e -> this.showSpoiler());
        this.setupButton(this.resturtButton, 200, 40, 125, 30, 20, e -> this.restartGame());
        this.setupButton(this.loadButton, 350, 40, 125, 30, 20, e -> this.loadGame());
        this.setupButton(this.saveButton, 500, 40, 125, 30, 20, e -> this.saveGame());


        this.setupButton(this.turnButton, 170, 730, 400, 50, 30, e -> this.drawCard());
    }

    private void setupButton(Button button, int x, int y, int width, int height, int textSize, ActionListener actionListener) {
        button.setSize(width, height);
        button.setLocation(x, y);
        button.setFont(new Font("Arial", Font.PLAIN, textSize));
        button.setBackground(this.settings.getButtonBgColor());
        button.setForeground(this.settings.getButtonFgColor());
        button.addActionListener(actionListener);
        this.frame.add(button);
    }

    private void setupFrame() {
        // set icon for the frame
        try {
            Image icon = ImageIO.read(new File("Images/gameIcon.png")); // Replace with your icon file path
            this.frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set frame settings
        this.frame.setSize(this.settings.getWidth(), this.settings.getHeight());
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        this.frame.setBackground(settings.getBgColor());

        // add window listener to handle window closing event
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void setupLabel(Label label, int x, int y, int width, int height, int size) {
        label.setSize(width, height);
        label.setLocation(x, y);
        label.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        label.setBackground(this.settings.getBgColor());
        label.setForeground(this.settings.getTextColor());
        this.frame.add(label);
    }

    private void setupCanvas(CardCanvas cardCanvas, int x, int y) {
        cardCanvas.setBackground(this.settings.getBgColor());
        cardCanvas.setLocation(x, y);
        cardCanvas.clearAndSetBack();
        this.frame.add(cardCanvas);
    }

    public static void main(String[] args) {
        VisualApp app = new VisualApp();
        app.runGame();
    }
}
