package App;

import Cards.Card;
import Cards.Deck;
import Cards.Hand;
import CustomComponents.CardCanvas;
import CustomEnums.GameState;
import Predictor.Predictor;
import ProgressEngine.ProgressEngine;
import javax.swing.JButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This is main GUI class for the game. It contains all the game logic and GUI components.
 *
 * @author Danila
 * @version 1.0
 */
public class GameWindow {
    // ############################### Game Logical Vars ###################################
    /** Deck instance used for getting random hands and create cards */
    private Deck deck = new Deck();

    /** here we are getting random hands from the deck */
    private List<Hand> hands = deck.getRandomHands();

    /** hand1 is used to store cards that first player have on table */
    private Hand hand1 = hands.get(0);
    /** hand2 is used to store cards that second user have on table */
    private Hand hand2 = hands.get(hands.size() - 1);

    /** gameState is used to store the current state of the game */
    private GameState gameState = GameState.IN_PROGRESS;

    /** Settings instance is used to store game settings information */
    private final Settings settings = new Settings();

    /** progress engine instance to save and load the game */
    private final ProgressEngine progressEngine = new ProgressEngine();

    /** predictor instance to predict the winner and steps */
    private final Predictor predictor = new Predictor();

    /** cardsOnTable1 is used to store cards that first player have on table */
    private final List<Card> cardsOnTable1 = new ArrayList<>();
    /** cardsOnTable2 is used to store cards that second player have on table */
    private final List<Card> cardsOnTable2 = new ArrayList<>();

    /** Timer instance to autoplay the game */
    private final Timer autoplayTimer = new Timer(1000 / this.settings.getAutoPlayStepsPerSecond(), this::drawCard);

    // ############################### GUI Vars ###################################
    /** frame to hold all the components */
    private final Frame frame = new Frame(this.settings.getTitle());

    /** cardCanvas1 is used to show the cards that first player have on table */
    private final CardCanvas cardCanvas1 = new CardCanvas();
    /** cardCanvas2 is used to show the cards that second player have on table */
    private final CardCanvas cardCanvas2 = new CardCanvas();

    /** label to show the amount of cards that Player1 have */
    private final Label handCount1 = new Label("Player 1: " + this.hand1.getCardAmount() + " cards");
    /** label to show the amount of cards that Player2 have */
    private final Label handCount2 = new Label("Player 2: " + this.hand2.getCardAmount() + " cards");
    /** label to show the status of the game */
    private final Label statusLabel = new Label("Game in progress...");
    /** label to show the prediction of the game */
    private final Label spoilerLabel = new Label("");


    /** button to pull the cards */
    private final JButton turnButton = new JButton("Pull Cards");
    /** button to show the prediction */
    private final JButton spoilerButton = new JButton("Spoiler");
    /** button to restart the game */
    private final JButton resturtButton = new JButton("Restart Game");
    /** button to save the game */
    private final JButton saveButton = new JButton("Save Game");
    /** button to load the game */
    private final JButton loadButton = new JButton("Load Game");

    /** switchButton to enable/disable autoplay */
    private final JCheckBox switchButton = new JCheckBox("Auto Play", false);


    /**
     * Function used to get the last card value from the list of cards.
     * It was created to avoid code duplication and make code cleaner.
     *
     * @param cards List of cards from which we want to get the last card value.
     * @return value of last card in the list.
     */
    private int getLastCardValue(List<Card> cards) {
        return cards.get(cards.size() - 1).getValue();
    }

    /**
     * Function used to restart the game.
     *
     * @param event ActionEvent instance that is not used now, but it exists to match the ActionListener interface.
     */
    public void restartGame(ActionEvent event) {
        // getting new hands
        this.deck = new Deck();
        this.hands = deck.getRandomHands();
        this.hand1 = hands.get(0);
        this.hand2 = hands.get(hands.size() - 1);

        // clearing the cards on table. It only redraws the cards on screen
        this.cardCanvas1.clearAndSetBack();
        this.cardCanvas2.clearAndSetBack();

        // clearing the cards on table
        this.cardsOnTable1.clear();
        this.cardsOnTable2.clear();

        // resetting the labels to show real amount of cards in players hands
        this.handCount1.setText("Player 1: " + this.hand1.getCardAmount() + " cards");
        this.handCount2.setText("Player 2: " + this.hand2.getCardAmount() + " cards");

        // setting the status label to show that game is in progress and action button to pull cards
        this.statusLabel.setText("Game in progress...");
        this.turnButton.setLabel("Pull Cards");

        // if the game was played till the end button will be disabled, so we need to enable it
        this.turnButton.setEnabled(true);
    }

    /**
     * Function used to save the game. It will ask the user to enter the name of the save.
     *
     * @param event ActionEvent instance that is not used now, but it exists to match the ActionListener interface.
     */
    public void saveGame(ActionEvent event) {
        // getting the user input
        String userInput = JOptionPane.showInputDialog("Enter name for this save:");

        try {
            // saving the game
            if (userInput == null) {
                return;
            }
            this.progressEngine.saveState(this.hand1, this.hand2, userInput);
        } catch (Exception e) {
            System.out.println("Error saving the game");
        }
    }


    /**
     * Function used to load the game. It will show the user the list of available saves and ask to choose one.
     *
     * @param event ActionEvent instance that is not used now, but it exists to match the ActionListener interface.
     */
    public void loadGame(ActionEvent event) {
        // getting save name from user input
        Object[] options = this.progressEngine.getAvailableSaves();
        Object selectedValue = JOptionPane.showInputDialog(
                null,
                "Choose load name",
                "Input",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                null);

        try {
            if (selectedValue == null) {
                return;
            }
            // loading the game state(We are getting hands from the save and setting them to the current hands)
            this.hands = this.progressEngine.loadState(selectedValue.toString());
            this.hand1 = hands.get(0);
            this.hand2 = hands.get(hands.size() - 1);

            // clearing the cards on table
            this.cardsOnTable1.clear();
            this.cardsOnTable2.clear();

            // redrawing the cards on screen
            this.cardCanvas1.clearAndSetBack();
            this.cardCanvas2.clearAndSetBack();

            // resetting labels on screen to show the real amount of cards in players hands
            this.handCount1.setText("Player 1: " + this.hand1.getCardAmount() + " cards");
            this.handCount2.setText("Player 2: " + this.hand2.getCardAmount() + " cards");
        } catch (Exception e) {
            throw new RuntimeException("Error loading the game");
        }


    }

    /**
     * Function used to show the prediction of the game. It is assigned to the {@link #spoilerButton}.
     *
     * @param event ActionEvent instance that is not used now, but it exists to match the {@link ActionListener} interface.
     */
    public void showSpoiler(ActionEvent event) {

        if (this.spoilerLabel.getText().isEmpty()) {
            this.spoilerLabel.setText(predictor.predictWinner(this.hand1, this.hand2).toString());
        } else {
            this.spoilerLabel.setText("");
        }
    }


    /**
     * Function used to draw the card. Here is all logic of the game located. It is assigned to the {@link #turnButton}.
     *
     * @param event ActionEvent instance that is not used now, but it exists to match the ActionListener interface.
     */
    public void drawCard(ActionEvent event) {
        try {
            // handles the case when on previous round we had a draw
            if (this.gameState == GameState.DRAW) {
                // draw 2 cards for each player
                this.cardsOnTable1.add(this.hand1.drawCard());
                this.cardsOnTable1.add(this.hand1.drawCard());
                this.cardsOnTable2.add(this.hand2.drawCard());
                this.cardsOnTable2.add(this.hand2.drawCard());

                // add card backs to the canvas to show that we have 2 cards on table
                this.cardCanvas1.addCardBacks(2);
                this.cardCanvas2.addCardBacks(2);

                // update the status label to show that game is in progress
                this.statusLabel.setText("Game in progress...");
                this.gameState = GameState.IN_PROGRESS;

                // here we are returning since we do not need to do anything else on this round
                return;
            }


            // handles the case when we need to start the next round
            if (this.gameState == GameState.PLAYER_WIN) {
                // resetting the button label and status label
                this.turnButton.setLabel("Pull Cards");
                this.statusLabel.setText("Game in progress...");
                this.gameState = GameState.IN_PROGRESS;

                // redraw the cards on screen
                this.cardCanvas1.clearAndSetBack();
                this.cardCanvas2.clearAndSetBack();

                // reset labels text to show the real amount of cards in players hands
                this.handCount1.setText("Player 1: " + this.hand1.getCardAmount() + " cards");
                this.handCount2.setText("Player 2: " + this.hand2.getCardAmount() + " cards");

                // here we are returning since we do not need to do anything else on this round
                return;
            }

            // handles the case when we need to finish the game
            if (this.hand1.getCardAmount() == 0) {
                this.statusLabel.setText("Player 2 wins the game!");
                this.cardCanvas1.clearCards();
                this.turnButton.setEnabled(false);
                this.gameState = GameState.END;
                return;
            }

            // handles the case when we need to finish the game
            if (this.hand2.getCardAmount() == 0) {
                this.statusLabel.setText("Player 1 wins the game!");
                this.cardCanvas2.clearCards();
                this.turnButton.setEnabled(false);
                this.gameState = GameState.END;
                return;
            }

            // draw new cards
            this.cardsOnTable1.add(this.hand1.drawCard());
            this.cardsOnTable2.add(this.hand2.drawCard());

            // add card images to the canvas to show the cards on table
            this.cardCanvas1.addCardImage(cardsOnTable1.get(this.cardsOnTable1.size() - 1).getImage());
            this.cardCanvas2.addCardImage(cardsOnTable2.get(this.cardsOnTable2.size() - 1).getImage());

            // handles the case when we need to compare the cards
            if (this.getLastCardValue(this.cardsOnTable1) != this.getLastCardValue(this.cardsOnTable2)) {
                // this is the case when 1 player wins the round
                if (this.getLastCardValue(this.cardsOnTable1) > this.getLastCardValue(this.cardsOnTable2)) {
                    this.statusLabel.setText("Player 1 wins the round!");
                    // putting cards from table to the winner hand
                    for (Card card : this.cardsOnTable1)
                        this.hand1.putCard(card);
                    this.cardsOnTable1.clear();
                    for (Card card : this.cardsOnTable2)
                        this.hand1.putCard(card);
                    this.cardsOnTable2.clear();
                } else {
                    this.statusLabel.setText("Player 2 wins the round!");
                    // putting cards from table to the winner hand
                    for (Card card : this.cardsOnTable1)
                        this.hand2.putCard(card);
                    this.cardsOnTable1.clear();
                    for (Card card : this.cardsOnTable2)
                        this.hand2.putCard(card);
                    this.cardsOnTable2.clear();
                }
                this.turnButton.setLabel("Next round");
                this.gameState = GameState.PLAYER_WIN;
            } else { // this else condition is used to handle the case when we have a draw
                // here we are checking if we have enough cards to continue the game
                if (this.hand1.getCardAmount() < 3) {
                    this.statusLabel.setText("Player 2 wins the game!");
                    this.turnButton.setEnabled(false);
                } else if (this.hand2.getCardAmount() < 3) {
                    this.statusLabel.setText("Player 1 wins the game!");
                    this.turnButton.setEnabled(false);
                } else {
                    // if there is enough cards for both players we have a draw
                    this.statusLabel.setText("Draw! Each player gets one more card.");
                    this.gameState = GameState.DRAW;
                }
            }

            // this catch block is used to handle the case when we do not have any cards in hand
        } catch (Exception e) {
            System.out.println("No more cards in hand");
            this.autoplayTimer.stop();
            this.switchButton.setSelected(false);
        }
    }


    /**
     * Function used to handle the action of the switchButton. It is used to enable/disable autoplay.
     *
     * @param e ItemEvent instance that is used to get the state of the switchButton.
     */
    public void autoplayAction(ItemEvent e) {
        // if the switchButton is selected we need to start the autoplay
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.turnButton.setEnabled(false);
            this.autoplayTimer.start();
        } else { // else we need to stop the autoplay
            this.turnButton.setEnabled(true);
            this.autoplayTimer.stop();
        }
    }

    /**
     * Function used to run the game. It is used to set up the frame and all the components.
     */
    public void setupUI() {
        // ############################### Main GUI setup ###################################

        // setting up the frame
        try {
            this.setupFrame();
        } catch (IOException e) {
            System.out.println("Error setting up the frame");
        }

        // setting up all the components
        this.setupCanvas(this.cardCanvas1, 50, 150);
        this.setupCanvas(this.cardCanvas2, 550, 150);

        this.setupLabel(handCount1, 50, 100, 200, 50, 20);
        this.setupLabel(handCount2, 550, 100, 200, 50, 20);
        this.setupLabel(statusLabel, 260, 670, 400, 50, 20);
        this.setupLabel(spoilerLabel, 300, 300, 200, 30, 19);
        
        this.setupButton(this.spoilerButton, 50, 40, 125, 30, 20, this::showSpoiler);
        this.setupButton(this.resturtButton, 200, 40, 125, 30, 20, this::restartGame);
        this.setupButton(this.loadButton, 350, 40, 125, 30, 20, this::loadGame);
        this.setupButton(this.saveButton, 500, 40, 125, 30, 20, this::saveGame);


        this.setupButton(this.turnButton, 170, 730, 400, 50, 30, this::drawCard);

        this.setupSwitchButton(this.switchButton, 50, 730, 100, 50, 20, this::autoplayAction);

        this.frame.repaint();

       
    }

    /**
     * Function used to set up the button. It is used to avoid code duplication and make code cleaner.
     *
     * @param button         Button instance that we want to set up.
     * @param x              x coordinate of the button's left corner.
     * @param y              y coordinate of the button's left corner.
     * @param width          width of the button.
     * @param height         height of the button.
     * @param textSize       text size of the button.
     * @param actionListener ActionListener instance that is used to handle the button action.
     */
    
    private void setupButton(JButton button, int x, int y, int width, int height, int textSize, ActionListener actionListener) {
        // setting up the button
        
        button.setSize(width, height);
        button.setLocation(x, y);
        button.setFont(new Font("Arial", Font.PLAIN, textSize));
        button.setBackground(this.settings.getButtonBgColor());
        button.setForeground(this.settings.getButtonFgColor());
        button.addActionListener(actionListener);
        
        this.frame.add(button);
        
        
    }
    

    /**
     * Function used to set up the frame.
     *
     * @throws IOException if there is an error finding the icon file.
     */
    public void setupFrame() throws IOException {
        // set icon for the frame
        try {
            // setting frame icon
            Image icon = ImageIO.read(new File("Images/gameIcon.png"));
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

    /**
     * Function used to set up the label.
     *
     * @param label  Label instance that we want to set up.
     * @param x      x coordinate of the label's left corner.
     * @param y      y coordinate of the label's left corner.
     * @param width  width of the label.
     * @param height height of the label.
     * @param size   text size of the label.
     */
    private void setupLabel(Label label, int x, int y, int width, int height, int size) {
        // setting up the label
        label.setSize(width, height);
        label.setLocation(x, y);
        label.setFont(new Font("Yu Gothic UI", Font.PLAIN, size));
        label.setBackground(this.settings.getBgColor());
        label.setForeground(this.settings.getTextColor());
        this.frame.add(label);
    }

    /**
     * Function used to set up the canvas.
     *
     * @param cardCanvas CardCanvas instance that we want to set up.
     * @param x          x coordinate of the canvas's left corner.
     * @param y          y coordinate of the canvas's left corner.
     */
    private void setupCanvas(CardCanvas cardCanvas, int x, int y) {
        cardCanvas.setBackground(this.settings.getBgColor());
        cardCanvas.setLocation(x, y);
        cardCanvas.clearAndSetBack();

        // here we calculate canvas size based on the card scale and its amount
        cardCanvas.setSize(
                (int) (this.settings.getSubImageWidth() * this.settings.getCardScale()),
                (int) (this.settings.getSubImageHeight() * this.settings.getCardScale()) +
                        this.settings.getMaxCardsOnTable() * this.settings.getCardDistance());
        this.frame.add(cardCanvas);
    }

    /**
     * Function used to set up the switchButton.
     *
     * @param switchButton Checkbox instance that we want to set up.
     * @param x            x coordinate of the switchButton's left corner.
     * @param y            y coordinate of the switchButton's left corner.
     * @param width        width of the switchButton.
     * @param height       height of the switchButton.
     * @param textSize     text size of the switchButton.
     * @param itemListener ItemListener instance that is used to handle the switchButton action.
     */
    private void setupSwitchButton(JCheckBox switchButton, int x, int y, int width, int height, int textSize, ItemListener itemListener) {
        switchButton.addItemListener(itemListener);

        switchButton.setSize(width, height);
        switchButton.setLocation(x, y);
        switchButton.setFont(new Font("Arial", Font.PLAIN, textSize));
        switchButton.setBackground(this.settings.getButtonBgColor());
        switchButton.setForeground(this.settings.getButtonFgColor());

        this.frame.add(switchButton);
    }


    /**
     * Main Function used to run the game.
     */
    public static void main(String[] args) {
        GameWindow app = new GameWindow();
        app.setupUI();
    }

}
