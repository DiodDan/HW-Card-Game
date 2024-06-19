package App;

public class Settings {
    private int width = 800;
    private int height = 800;
    private String title = "Card Game";

    Settings() {
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }

    String getTitle() {
        return this.title;
    }
}
