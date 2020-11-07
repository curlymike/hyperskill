package tictactoe;

import java.util.List;
import java.util.Random;

public class EasyStrategy implements AiStrategy {
    private static Random random = new Random();

    public EasyStrategy() {
    }

    @Override
    public void move(int[][] field, int playsFor) {
        List<Main.Cell> list = Main.freeCells(field);
        Main.Cell randomFreeCell = list.get(random.nextInt(list.size()));
        field[randomFreeCell.y][randomFreeCell.x] = playsFor;
    }
}
