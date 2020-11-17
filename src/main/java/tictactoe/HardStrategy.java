package tictactoe;

public class HardStrategy implements AiStrategy {

    public HardStrategy() {
    }

    @Override
    public void move(int[][] field, int playsFor) {
        MiniMax.Result result = MiniMax.compute(field, playsFor);
        field[result.getCell().y][result.getCell().x] = playsFor;
    }

}
