package App;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import CardHolders.Deck;
import CardHolders.Hand;
import Cards.Card;
import CustomComponents.CardCanvas;
import VisualEngine.CardLoader;

public class VisualApp {
    Deck deck = new Deck();
    List<Hand> hands = deck.getRandomHands();
    Hand hand1 = hands.getFirst();
    Hand hand2 = hands.getLast();
    Settings settings = new Settings();
    Frame frame = new Frame(settings.getTitle());
    CardCanvas cardCanvas = new CardCanvas();

    public void drawCard() {
        try {
            Card card = this.hand1.drawCard();
            Image cardImage = card.getImage();
            this.cardCanvas.setCardImage(cardImage);
            System.out.println(card.getCardInfo());
        } catch (Exception e) {
            System.out.println("No more cards in hand");
        }
    }

    public void runGame() {
        // ############################### Main GUI setup ###################################
        this.cardCanvas.setLocation(100, 100);
        CardLoader cardLoader = new CardLoader();
        this.cardCanvas.setCardImage(cardLoader.loadCardBack());
        this.frame.add(this.cardCanvas);
        this.frame.setSize(this.settings.getWidth(), this.settings.getHeight());
        this.frame.setLayout(null);
        this.frame.setVisible(true);

        this.frame.setBackground(Color.BLACK);

        Button button = new Button("Pull");
        button.setSize(100, 50);
        button.setLocation(500, 500);
        button.setBackground(Color.RED);
        button.addActionListener(e -> this.drawCard());

        this.frame.add(button);
        // ############################### End of Main GUI setup ###################################


        // ############################### Main Logic ###################################


        // ############################### End of Main Logic ###################################


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
