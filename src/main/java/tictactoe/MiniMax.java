package tictactoe;

import java.util.*;

import static tictactoe.Main.isWinner;
import static tictactoe.Main.opponent;
import static tictactoe.Main.hasFreeCells;
import static tictactoe.Main.freeCells;
import static tictactoe.Main.Cell;

public enum MiniMax {
    MIN, MAX;

    public static int compute(int[][] field, int player) {
        return compute(field, player, MAX, 0);
    }

    public static int compute(int[][] field, int player, MiniMax miniMax) {
        return compute(field, player, miniMax, 0);
    }

    public static int compute(int[][] field, int player, MiniMax miniMax, int depth) {
        if (isWinner(field, player) > 0) {
            return miniMax == MAX ? 10 : -10;
        }
        if (!hasFreeCells(field)) {
            return 0;
        }
        if (depth > 0) {
            player = opponent(player);
            miniMax = inverse(miniMax);
        }

        List<Integer> scores = new ArrayList<>();

        for (Cell freeCell : freeCells(field)) {
            field[freeCell.y][freeCell.x] = player;
            scores.add(compute(field, player, miniMax, depth + 1));
            field[freeCell.y][freeCell.x] = 0;
        }

        int value;
        if (miniMax == MAX) {
            value = Collections.max(scores);
        } else {
            value = Collections.min(scores);
        }
        return value;
    }

    private static MiniMax inverse(MiniMax miniMax) {
        return miniMax == MIN ? MAX : MIN;
    }

}
