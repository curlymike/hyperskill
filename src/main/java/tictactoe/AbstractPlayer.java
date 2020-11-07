package tictactoe;

public abstract class AbstractPlayer {
    protected final int[][] field;
    protected final int playsFor;

    public AbstractPlayer(int[][] field, int playsFor) {
        this.field = field;
        this.playsFor = playsFor;
    }
}
