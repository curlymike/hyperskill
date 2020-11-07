package tictactoe;

import java.util.List;
import java.util.Random;

public class MediumStrategy implements AiStrategy {
    private static Random random = new Random();

    public MediumStrategy() {
    }

    @Override
    public void move(int[][] field, int playsFor) {
        List<Main.Cell> list = Main.freeCells(field);

        Main.Cell cell = oneMoveWin(field, playsFor);
        if (cell != null) {
            field[cell.y][cell.x] = playsFor;
            return;
        }

        cell = oneMoveWin(field, Main.opponent(playsFor));
        if (cell != null) {
            field[cell.y][cell.x] = playsFor;
            return;
        }

        Main.Cell randomFreeCell = list.get(random.nextInt(list.size()));
        field[randomFreeCell.y][randomFreeCell.x] = playsFor;
    }

    private Main.Cell oneMoveWin(int[][] field, int character) {
        // Rows
        RowAnalyzer rowAnalyzer = new RowAnalyzer(field, character);
        for (int y = 0; y < field.length; y++) {
            rowAnalyzer.reset();
            for (int x = 0; x < field[y].length; x++) {
                if (!rowAnalyzer.next(y, x)) {
                    break;
                }
            }
            if (rowAnalyzer.isGood()) {
                return rowAnalyzer.getFreeCell();
            }
        }
        // Columns
        for (int x = 0; x < field[0].length; x++) {
            rowAnalyzer.reset();
            for (int y = 0; y < field.length; y++) {
                if (!rowAnalyzer.next(y, x)) {
                    break;
                }
            }
            if (rowAnalyzer.isGood()) {
                return rowAnalyzer.getFreeCell();
            }
        }
        // Diagonal 1
        rowAnalyzer.reset();
        for (int x = 0; x < field.length; x++) {
            if (!rowAnalyzer.next(x, x)) {
                break;
            }
        }
        if (rowAnalyzer.isGood()) {
            return rowAnalyzer.getFreeCell();
        }
        // Diagonal 2
        rowAnalyzer.reset();
        for (int y = 0, x = field.length - 1;  y < field.length; y++, x--) {
            if (!rowAnalyzer.next(y, x)) {
                break;
            }
        }
        if (rowAnalyzer.isGood()) {
            return rowAnalyzer.getFreeCell();
        }
        return null;
    }

    /**
     *
     *
     *
     */

    public static class RowAnalyzer {
        private final int[][] field;
        private final int character;
        private Main.Cell freeCell;
        private boolean isGood = true;

        public RowAnalyzer(int[][] field, int character) {
            this.field = field;
            this.character = character;
        }

        public void reset() {
            isGood = true;
            freeCell = null;
        }

        public Main.Cell getFreeCell() {
            return freeCell;
        }

        public boolean isGood() {
            return isGood && freeCell != null;
        }

        public boolean next(int y, int x) {
            if (field[y][x] == Main.FREE) {
                if (freeCell != null) {
                    isGood = false;
                    return false;
                }
                freeCell = new Main.Cell(y, x);
            } else if (field[y][x] != character) {
                isGood = false;
                return false;
            }
            return true;
        }
    }

}
