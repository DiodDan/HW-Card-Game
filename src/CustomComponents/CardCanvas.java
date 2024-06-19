package CustomComponents;

import java.awt.*;

public class CardCanvas extends Canvas {
    Image cardImage;

    @Override
    public void paint(Graphics g) {
        this.setBackground(Color.BLACK);
        if (this.cardImage != null) {
            g.drawImage(this.cardImage, 0, 0, this);
        }
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
        this.repaint();
        this.setSize(cardImage.getWidth(null), cardImage.getHeight(null));
    }
}