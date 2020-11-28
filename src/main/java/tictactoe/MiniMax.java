package tictactoe;

import static tictactoe.Main.isWinner;
import static tictactoe.Main.opponent;
import static tictactoe.Main.hasFreeCells;
import static tictactoe.Main.freeCells;
import static tictactoe.Main.Cell;

public enum MiniMax {
    MIN, MAX;

    public static Result compute(int[][] field, int player) {
        return compute(field, player, MiniMax.MAX, 0);
    }

    public static Result compute(int[][] field, int player, MiniMax miniMax) {
        return compute(field, player, miniMax, 0);
    }

    public static Result compute(int[][] field, int player, MiniMax miniMax, int depth) {
        Cell cell = null;
        int largestScoreValue = miniMax == MAX ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (Cell freeCell : freeCells(field)) {
            int value;
            field[freeCell.y][freeCell.x] = player;
            if (isWinner(field, player) > 0) {
                value = miniMax == MAX ? 10 : -10;
            } else if (!hasFreeCells(field)) {
                value = 0;
            } else {
                value = compute(field, opponent(player), inverse(miniMax), depth + 1).score;
            }
            if (miniMax == MAX && value > largestScoreValue || miniMax == MIN && value < largestScoreValue) {
                largestScoreValue = value;
                cell = freeCell;
            }
            field[freeCell.y][freeCell.x] = 0;
        }
        return new Result(largestScoreValue, cell);
    }

    private static MiniMax inverse(MiniMax miniMax) {
        return miniMax == MIN ? MAX : MIN;
    }

    /**
     *
     */

    public static class Result {
        private final int score;
        private final Cell cell;

        public Result(int score, Cell cell) {
            this.score = score;
            this.cell = cell;
        }

        public int getScore() {
            return score;
        }

        public Cell getCell() {
            return cell;
        }
    }

}
