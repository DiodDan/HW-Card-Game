package App;

import java.awt.*;
import javax.swing.*;
public class MainMenu extends JFrame{

    public MainMenu() {
        // To set the frame
        setTitle("MainMenu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // To create the panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout((new GridLayout(3, 1, 10, 10)));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20,20,20));

        //Buttons to press to play against each other or the computer.
        JButton playComputerButton = new JButton("Play Against Computer");
        JButton playPlayersButton = new JButton("Play Against Other Players");

        // To Add buttons to the panel
        panel.add(playComputerButton);
        panel.add(playPlayersButton);

        //  To Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // To Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // To Create menus
        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help");

        // To Create menu items
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem saveGameItem = new JMenuItem("Save Game");
        JMenuItem openGameItem = new JMenuItem("Open Game");
        JMenuItem aboutItem = new JMenuItem("About");

        // To Add menu items to menus
        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(openGameItem);
        helpMenu.add(aboutItem);

        // To Add menus to menu bar
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        //To Add menu bar to the frame
        setJMenuBar(menuBar);

        // To Make the frame visible
        setVisible(true);
    }

    private void startGame(boolean againstComputer) {
        if (againstComputer) {
            JOptionPane.showMessageDialog(this, "Starting game against the computer...");


            // Code to start the game against the computer
        } else {
            JOptionPane.showMessageDialog(this, "Starting game against other players...");

            // Code to start the game against other PLAYERS
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        new MainMenu ();
    }
}




