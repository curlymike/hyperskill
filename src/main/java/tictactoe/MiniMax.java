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
        return compute(field, player, MAX, 0);
        //return compute(field, player, MAX);
    }

    public static int compute(int[][] field, int player, MiniMax miniMax, int depth) {
        if (depth > 0) {
            if (isWinner(field, player) > 0) {
                return miniMax == MAX ? 10 : -10;
            }
//            if (isWinner(field, opponent(player)) > 0) {
//                return -10;
//            }
            if (!hasFreeCells(field)) {
                return 0;
            }
            player = opponent(player);
            miniMax = inverse(miniMax);
        }

        Map<Cell, Integer> map = new HashMap<>();

        for (Cell cell : freeCells(field)) {
            field[cell.y][cell.x] = player;
            map.put(cell, compute(field, player, miniMax, depth + 1));
            //map.put(cell, compute(field, player, miniMax));
            field[cell.y][cell.x] = 0;
        }

        Cell cell = null;
        int value;
        if (miniMax == MAX) {
            value = Integer.MIN_VALUE;
            for (Map.Entry<Cell, Integer> pair : map.entrySet()) {
                if (pair.getValue() > value) {
                    value = pair.getValue();
                    cell = pair.getKey();
                }
            }
        } else {
            value = Integer.MAX_VALUE;
            for (Map.Entry<Cell, Integer> pair : map.entrySet()) {
                if (pair.getValue() < value) {
                    value = pair.getValue();
                    cell = pair.getKey();
                }
            }
        }
        if (depth == 0) {
            System.out.println(cell);
        }
        return value;
    }

    private static MiniMax inverse(MiniMax miniMax) {
        return miniMax == MIN ? MAX : MIN;
    }

}
