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

    // TODO: Choose randomly between moves with the same weight
    public static Result compute(int[][] field, int player, MiniMax miniMax, int depth) {
        Cell cell = null;
        int largestScoreValue = miniMax == MAX ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int scoreSum = 0;
        for (Cell freeCell : freeCells(field)) {
            int value;
            field[freeCell.y][freeCell.x] = player;
            if (isWinner(field, player) > 0) {
                value = miniMax == MAX ? 10 : -10;
                scoreSum += value;
            } else if (!hasFreeCells(field)) {
                value = 0;
            } else {
                Result result = compute(field, opponent(player), inverse(miniMax), depth + 1);
                value = result.scoreSum;
                scoreSum += result.scoreSum;
            }
            if (miniMax == MAX && value > largestScoreValue || miniMax == MIN && value < largestScoreValue) {
                largestScoreValue = value;
                cell = freeCell;
            }
            field[freeCell.y][freeCell.x] = 0;
        }
        return new Result(largestScoreValue, cell, scoreSum);
    }

    private static MiniMax inverse(MiniMax miniMax) {
        return miniMax == MIN ? MAX : MIN;
    }

    /**
     *
     */

    public static class Result {
        private final int score;
        private final int scoreSum;
        private final Cell cell;

        public Result(int score, Cell cell, int scoreSum) {
            this.score = score;
            this.cell = cell;
            this.scoreSum = scoreSum;
        }

        public int getScore() {
            return score;
        }

        public int getScoreSum() {
            return scoreSum;
        }

        public Cell getCell() {
            return cell;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "score=" + score +
                    ", scoreSum=" + scoreSum +
                    ", cell=" + cell +
                    '}';
        }
    }

}
