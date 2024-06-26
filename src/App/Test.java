package App;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class Test {
    private final VisualApp app = new VisualApp();
    private final int testDrawsAmount = 10;

    private void testCardDrawing() {
        // Test if the card canvas can draw a card
        ActionEvent e = new ActionEvent(this.app, 0, "");
        for (int i = 0; i < testDrawsAmount; i++)
            this.app.drawCard(e);
        System.out.println("Card drawing test passed!");
    }

    private void testAutplay() {
        // Test if the autoplay can play a game
        ItemEvent selected = new ItemEvent(new JCheckBox(), 0, "", ItemEvent.SELECTED);
        ItemEvent deselected = new ItemEvent(new JCheckBox(), 0, "", ItemEvent.DESELECTED);

        Timer timer = new Timer(2000, (e) -> {this.app.autoplayAction(deselected);});

        this.app.autoplayAction(selected);

        timer.start();

        System.out.println("Autoplay test passed!");
    }

    private void testPrediction() {
        // Test if the prediction can predict a winner
        ActionEvent e = new ActionEvent(this.app, 0, "");
        this.app.showSpoiler(e);
        this.app.showSpoiler(e);

        System.out.println("Prediction test passed!");
    }

    private void testRestart() {
        // Test if the game can be restarted
        ActionEvent e = new ActionEvent(this.app, 0, "");
        this.app.drawCard(e);
        this.app.restartGame(e);
        this.app.drawCard(e);

        System.out.println("Restart test passed!");
    }

    public void testApp() {
        // Test if the app can be set up
        this.app.setupUI();

        // Run all tests
        this.testCardDrawing();
        this.testAutplay();
        this.testPrediction();
        this.testRestart();
    }

    public static void main(String[] args) {
        Test test = new Test();
        Timer exitTimer = new Timer(5000, (e) -> {
            System.out.println("All tests passed!");
            System.exit(0);
        });

        System.out.println("Running tests...");
        test.testApp();
        exitTimer.start();
    }
}
