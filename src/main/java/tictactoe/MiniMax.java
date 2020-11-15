package tictactoe;

import java.util.HashMap;
import java.util.Map;

import static tictactoe.Main.isWinner;
import static tictactoe.Main.opponent;
import static tictactoe.Main.hasFreeCells;
import static tictactoe.Main.freeCells;
import static tictactoe.Main.Cell;

public enum MiniMax {
    MIN, MAX;
    public static int compute(int[][] field, int player) {
        return compute(field, player, MAX);
    }

    public static int compute(int[][] field, int player, MiniMax miniMax) {
        if (isWinner(field, player) > 0) {
            return 10;
        }
        if (isWinner(field, opponent(player)) > 0) {
            return -10;
        }
        if (!hasFreeCells(field)) {
            return 0;
        }

        Map<Cell, Integer> map = new HashMap<>();

        for (Cell cell : freeCells(field)) {
            field[cell.y][cell.x] = player;
            //map.put(cell, compute(field, opponent(player), invert(miniMax)));
            map.put(cell, compute(field, player, miniMax));
            field[cell.y][cell.x] = 0;
        }

        int value;
        if (invert(miniMax) == MAX) {
            value = Integer.MIN_VALUE;
            for (Map.Entry<Cell, Integer> pair : map.entrySet()) {
                if (pair.getValue() > value) {
                    value = pair.getValue();
                }
            }
        } else {
            value = Integer.MAX_VALUE;
            for (Map.Entry<Cell, Integer> pair : map.entrySet()) {
                if (pair.getValue() < value) {
                    value = pair.getValue();
                }
            }
        }
        return value;
    }

    private static MiniMax invert(MiniMax miniMax) {
        return miniMax == MIN ? MAX : MIN;
    }

}
