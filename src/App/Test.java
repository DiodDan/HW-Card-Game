package App;


import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        Timer exitTimer = new Timer(2000, e -> System.exit(0));
        VisualApp.main(args);
        exitTimer.start();
    }
}
