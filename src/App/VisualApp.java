package App;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Cards.Deck;
import Cards.Hand;
import Cards.Card;
import CustomComponents.CardCanvas;
import Predictor.Predictor;
import ProgressEngine.ProgressEngine;

import javax.imageio.ImageIO;
import javax.swing.*;

public class VisualApp {
    // ############################### Game Logical Vars ###################################
    private Deck deck = new Deck();

    private List<Hand> hands = deck.getRandomHands();

    private Hand hand1 = hands.get(0);
    private Hand hand2 = hands.get(hands.size() - 1);

    private Settings settings = new Settings();

    private ProgressEngine progressEngine = new ProgressEngine();

    private Predictor predictor = new Predictor();

    private List<Card> cardsOnTable1 = new ArrayList<>();
    private List<Card> cardsOnTable2 = new ArrayList<>();

    private Timer autoplayTimer = new Timer(1000 / settings.getAutoPlayStepsPerSecond(), new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawCard();
        }
    });

    // ############################### GUI Vars ###################################
    private Frame frame = new Frame(settings.getTitle());

    private CardCanvas cardCanvas1 = new CardCanvas();
    private CardCanvas cardCanvas2 = new CardCanvas();

    private Label handCount1 = new Label("Player 1: " + this.hand1.getCardAmount() + " cards");
    private Label handCount2 = new Label("Player 2: " + this.hand2.getCardAmount() + " cards");
    private Label statusLabel = new Label("Game in progress...");
    private Label spoilerLabel = new Label("");


    private Button turnButton = new Button("Pull Cards");
    private Button spoilerButton = new Button("Spoiler");
    private Button resturtButton = new Button("Restart Game");
    private Button saveButton = new Button("Save Game");
    private Button loadButton = new Button("Load Game");

    private Checkbox switchButton = new Checkbox("Auto Play", false);


    private int getLastCardValue(List<Card> cards) {
        return cards.get(cards.size() - 1).getValue();
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
            this.progressEngine.saveState(this.hand1, this.hand2, userInput);
        } catch (Exception e) {
            System.out.println("Error saving the game");
        }
    }

    private void loadGame() {

        Object[] options = this.progressEngine.getAvailableSaves();
        Object selectedValue = JOptionPane.showInputDialog(null, "Choose load name", "Input", JOptionPane.INFORMATION_MESSAGE, null, options, null);

        try {
            this.hands = this.progressEngine.loadState(selectedValue.toString());
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
            if (Objects.equals(this.statusLabel.getText(), "Draw! Each player gets one more card.")) {
                this.cardsOnTable1.add(this.hand1.drawCard());
                this.cardsOnTable1.add(this.hand1.drawCard());
                this.cardsOnTable2.add(this.hand2.drawCard());
                this.cardsOnTable2.add(this.hand2.drawCard());
                this.cardCanvas1.addCardBacks(2);
                this.cardCanvas2.addCardBacks(2);
                this.statusLabel.setText("Game in progress...");
                return;
            }
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
                this.cardCanvas1.clearCards();
                this.turnButton.setEnabled(false);
                return;
            }
            if (this.hand2.getCardAmount() == 0) {
                this.statusLabel.setText("Player 1 wins the game!");
                this.cardCanvas2.clearCards();
                this.turnButton.setEnabled(false);
                return;
            }
            // draw new cards
            this.cardsOnTable1.add(this.hand1.drawCard());
            this.cardsOnTable2.add(this.hand2.drawCard());

            this.cardCanvas1.addCardImage(cardsOnTable1.get(this.cardsOnTable1.size() - 1).getImage());
            this.cardCanvas2.addCardImage(cardsOnTable2.get(this.cardsOnTable2.size() - 1).getImage());


            if (this.getLastCardValue(this.cardsOnTable1) != this.getLastCardValue(this.cardsOnTable2)) {
                if (this.getLastCardValue(this.cardsOnTable1) > this.getLastCardValue(this.cardsOnTable2)) {
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
                if (this.hand1.getCardAmount() < 3) {
                    this.statusLabel.setText("Player 2 wins the game!");
                    this.turnButton.setEnabled(false);
                } else if (this.hand2.getCardAmount() < 3) {
                    this.statusLabel.setText("Player 1 wins the game!");
                    this.turnButton.setEnabled(false);
                } else {
                    this.statusLabel.setText("Draw! Each player gets one more card.");
                }
            }


        } catch (Exception e) {
            System.out.println("No more cards in hand");
            this.autoplayTimer.stop();
            this.switchButton.setState(false);
        }
    }

    private void autoplayAction(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.turnButton.setEnabled(false);
            this.autoplayTimer.start();
        } else {
            this.turnButton.setEnabled(true);
            this.autoplayTimer.stop();
        }
    }

    public void runGame() {
        // ############################### Main GUI setup ###################################
        try {
            this.setupFrame();
        } catch (IOException e) {
            System.out.println("Error setting up the frame");
        }


        this.setupCanvas(this.cardCanvas1, 50, 150);
        this.setupCanvas(this.cardCanvas2, 550, 150);

        this.setupLabel(handCount1, 50, 100, 200, 50, 20);
        this.setupLabel(handCount2, 550, 100, 200, 50, 20);
        this.setupLabel(statusLabel, 260, 670, 400, 50, 20);
        this.setupLabel(spoilerLabel, 300, 300, 200, 30, 19);


        this.setupButton(this.spoilerButton, 50, 40, 125, 30, 20, e -> this.showSpoiler());
        this.setupButton(this.resturtButton, 200, 40, 125, 30, 20, e -> this.restartGame());
        this.setupButton(this.loadButton, 350, 40, 125, 30, 20, e -> this.loadGame());
        this.setupButton(this.saveButton, 500, 40, 125, 30, 20, e -> this.saveGame());


        this.setupButton(this.turnButton, 170, 730, 400, 50, 30, e -> this.drawCard());

        this.setupSwitchButton(this.switchButton, 50, 730, 100, 50, 20, e -> this.autoplayAction(e));
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

    private void setupFrame() throws IOException {
        // set icon for the frame
        try {
            Image icon = ImageIO.read(new File("Images/gameIcon.png")); // Replace with your icon file path
            this.frame.setIconImage(icon);
        } catch (IOException e) {
            throw new IOException("Error finding the icon file");
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
        label.setFont(new Font("Yu Gothic UI", Font.PLAIN, size));
        label.setBackground(this.settings.getBgColor());
        label.setForeground(this.settings.getTextColor());
        this.frame.add(label);
    }

    private void setupCanvas(CardCanvas cardCanvas, int x, int y) {
        cardCanvas.setBackground(this.settings.getBgColor());
        cardCanvas.setLocation(x, y);
        cardCanvas.clearAndSetBack();
        cardCanvas.setSize(
                (int) (this.settings.getSubImageWidth() * this.settings.getCardScale()),
                (int) (this.settings.getSubImageHeight() * this.settings.getCardScale()) +
                        this.settings.getMaxCardsOnTable() * this.settings.getCardDistance());
        this.frame.add(cardCanvas);
    }

    private void setupSwitchButton(Checkbox switchButton, int x, int y, int width, int height, int textSize, ItemListener itemListener) {
        switchButton.addItemListener(itemListener);

        switchButton.setSize(width, height);
        switchButton.setLocation(x, y);
        switchButton.setFont(new Font("Arial", Font.PLAIN, textSize));
        switchButton.setBackground(this.settings.getButtonBgColor());
        switchButton.setForeground(this.settings.getButtonFgColor());

        this.frame.add(switchButton);
    }

    public static void main(String[] args) {
        VisualApp app = new VisualApp();
        app.runGame();
    }
}
