package App;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MenueWindow {
    // ############################### Game Logical Vars ###################################
    /** Settings instance is used to store game settings information */
    private final Settings settings = new Settings();

    // ############################### GUI Vars ###################################
    /** frame to hold all the components */
    private final Frame frame = new Frame(this.settings.getTitle());


    public void setupUI() {
        // ############################### Main GUI setup ###################################

        // setting up the frame
        try {
            this.setupFrame();
        } catch (IOException e) {
            System.out.println("Error setting up the frame");
        }
    }

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
}
