package VisualEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardLoaderVisual {
    public static void main(String[] args) throws IOException{
        try {
            BufferedImage img = ImageIO.read(new File("Images/cards.png"));
            int cardsInSuitAmount = 13;
            int cardsAmount = 52;
            double scale = 3.4;
            int subImageWidth = 40;
            int subImageHeight = 66;
            int cardDistanceX = 24;
            int cardDistanceY = 30;

            Image[] scaledImages = new Image[cardsAmount];
            Image[] scaledBacks = new Image[3];
            scaledBacks[0] = img.getSubimage(12, 495, subImageWidth, subImageHeight).getScaledInstance((int) (subImageWidth * scale), (int) (subImageHeight * scale), Image.SCALE_SMOOTH);
            scaledBacks[1] = img.getSubimage(76, 495, subImageWidth, subImageHeight).getScaledInstance((int) (subImageWidth * scale), (int) (subImageHeight * scale), Image.SCALE_SMOOTH);
            scaledBacks[2] = img.getSubimage(140, 495, subImageWidth, subImageHeight).getScaledInstance((int) (subImageWidth * scale), (int) (subImageHeight * scale), Image.SCALE_SMOOTH);
            for (int j = 0; j < 4; j++){
                for (int i = 0; i < cardsInSuitAmount; i++) {
                    scaledImages[i + j * cardsInSuitAmount] = img.getSubimage(i * (cardDistanceX + subImageWidth) + 12, 15 + j * (cardDistanceY + subImageHeight), subImageWidth, subImageHeight).getScaledInstance((int) (subImageWidth * scale), (int) (subImageHeight * scale), Image.SCALE_SMOOTH);
                }
            }

            
            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.setSize(1000, 800);
            for(int i = 0; i < cardsAmount; i++)
                frame.getContentPane().add(new JLabel(new ImageIcon(scaledImages[i])));
            for(int i = 0; i < 3; i++)
                frame.getContentPane().add(new JLabel(new ImageIcon(scaledBacks[i])));
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}