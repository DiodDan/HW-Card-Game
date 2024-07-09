package Predictor;

public class Prediction {
    private final int winner;
    private final int steps;

    public Prediction(int winner, int steps) {
        this.winner = winner;
        this.steps = steps;
    }

    public int getWinner() {
        return this.winner;
    }

    public int getSteps() {
        return this.steps;
    }
    public String toString() {
        return "Winner: " + this.getWinner() + " Steps: " + this.getSteps();
    }
}
