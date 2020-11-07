package tictactoe;

import java.util.*;

public class Main {
    public static final int FREE = 0;
    public static final int X = 1;
    public static final int O = 2;
    public static Scanner scanner;

    private static int whoMoves = X;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        game("_________");
    }

    public static int opponent(int player) {
        if (player != X && player != O) {
            throw new IllegalArgumentException();
        }
        return player == X ? O : X;
    }

    public static void game(String fieldState) {
        // 0 - empty, 1 - X, 2 - O
        int[][] field = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        //fillField(field, fieldState);

        Command cmd = gameMenu();

        if ("exit".equals(cmd.command)) {
            return;
        }

        Player playerX = "user".equals(cmd.arguments[0]) ? new Human(field, X) : new AI(field, X, AI.Level.valueOf(cmd.arguments[0].toUpperCase()));
        Player playerO = "user".equals(cmd.arguments[1]) ? new Human(field, O) : new AI(field, O, AI.Level.valueOf(cmd.arguments[1].toUpperCase()));

        State gameState = State.NOT_FINISHED;

        printField(field);

        while (gameState == State.NOT_FINISHED) {
            playerX.move();
            printField(field);
            gameState = evaluate(field);
            if (gameState != State.NOT_FINISHED) {
                continue;
            }
            playerO.move();
            printField(field);
            gameState = evaluate(field);
            //System.out.println(gameState);
            //whoMoves = whoMoves == X ? O : X;
        }
        System.out.println(gameState);
    }

    /**
     *
     */

    public static boolean isValidArgumentsForStart(String[] args) {
        if (args.length != 2) {
            return false;
        }
        for (String arg : args) {
            if (!isValidArgumentForStart(arg)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidArgumentForStart(String argument) {
        final String[] validArgs = {"user", "easy", "medium", "hard"};
        for (String arg : validArgs) {
            if (arg.equals(argument)) {
                return true;
            }
        }
        return false;
    }

    public static Command gameMenu() {
        while (true) {
            System.out.print("Input command: ");
            Command cmd = Command.create(scanner.nextLine().split("\\s+"));
            if ("exit".equals(cmd.command) ||
                "start".equals(cmd.command) && isValidArgumentsForStart(cmd.arguments)) {
                return cmd;
            }
            System.out.println("Bad parameters!");
        }
    }

    public static boolean canMove(int[][] field) {
        return Main.freeCells(field).size() > 0;
    }

    public static List<Cell> freeCells(int[][] field) {
        List<Cell> list = new ArrayList<>();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                if (field[y][x] == 0) {
                    list.add(new Cell(y, x));
                }
            }
        }
        return list;
    }

    public static int count(int[][] field, int what) {
        int count = 0;
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                if (field[y][x] == what)
                    count++;
            }
        }
        return count;
    }

    public static State evaluate(int[][] field) {
        if (!isImpossible(field)) {
            boolean xWins = isWinner(field, X) > 0;
            boolean oWins = isWinner(field, O) > 0;
            int winner = xWins ? X : oWins ? O : 0;

            if (hasFreeCells(field) && winner == 0) {
                return State.NOT_FINISHED;
            } else if (winner > 0) {
                return xWins ? State.X_WINS : State.O_WINS;
            } else {
                return State.DRAW;
            }
        }
        return State.IMPOSSIBLE;
    }

    public static boolean isValidMove(int[][] field, int x, int y) {
        return field[y][x] == 0;
    }

    // Coordinates start with 1
    public static boolean isCoordinatesValid(int[][] field, int x, int y) {
        return y > 0 && y <= field.length && x > 0 && x <= field[0].length;
    }

    public static boolean hasFreeCells(int[][] field) {
        int totalCells = field.length * field[0].length;
        return busyCellsCount(field) < totalCells;
    }

    public static boolean isImpossible(int[][] field) {
        boolean xWins = isWinner(field, X) > 0;
        boolean oWins = isWinner(field, O) > 0;
        int cellsTakenX = busyCellsCount(field, X);
        int cellsTakenO = busyCellsCount(field, O);
        int diff = cellsTakenO > cellsTakenX ? cellsTakenO - cellsTakenX : cellsTakenX - cellsTakenO;
        return diff > 1 || xWins && oWins;
    }

    public static int busyCellsCount(int[][] field) {
        return busyCellsCount(field, -1);
    }

    public static int busyCellsCount(int[][] field, int character) {
        boolean any = character == -1;
        int count = 0;
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == character || any && field[y][x] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int isWinner(int[][] field, int character) {
        int count = 0;
        for (int y = 0; y < field.length; y++) {
            boolean fullRow = true;
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] != character) {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) {
                count++;
            }
        }
        for (int x = 0; x < field[0].length; x++) {
            boolean fullRow = true;
            for (int y = 0; y < field.length; y++) {
                if (field[y][x] != character) {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) {
                count++;
            }
        }
        // Diagonal
        boolean fullRow = true;
        for (int x = 0; x < field.length; x++) {
            if (field[x][x] != character) {
                fullRow = false;
                break;
            }
        }
        if (fullRow) {
            count++;
        }
        fullRow = true;
        for (int y = 0, x = field.length - 1;  y < field.length; y++, x--) {
            if (field[y][x] != character) {
                fullRow = false;
                break;
            }
        }
        if (fullRow) {
            count++;
        }
        return count;
    }

    public static void fillField(int[][] field, String fieldState) {
        int i = 0;
        for (int y = field.length - 1; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                switch (fieldState.charAt(i)) {
                    case 'X': field[y][x] = 1; break;
                    case 'O': field[y][x] = 2; break;
                    case '_': field[y][x] = 0; break;
                }
                i++;
                if (i == fieldState.length()) {
                    return;
                }
            }
        }
    }

    public static void printField(int[][] field) {
        System.out.println("---------");

        for (int y = field.length - 1; y >= 0; y--) {
            System.out.print("| ");
            for (int x = 0; x < field[y].length; x++) {
                System.out.print(field[y][x] == 0 ? '_' : field[y][x] == 1 ? 'X' : 'O');
                System.out.print(' ');
            }
            System.out.println('|');
        }

        System.out.println("---------");
    }

    /**
     *
     */

    public static class Command {
        public final String command;
        public final String[] arguments;

        public Command(String command, String[] arguments) {
            this.command = command;
            this.arguments = arguments;
        }

        public boolean hasArguments() {
            return arguments != null;
        }


        public static Command create(String[] commandWithArgs) {
            if (commandWithArgs.length < 1) {
                throw new IllegalArgumentException();
            }
            String[] args;
            if (commandWithArgs.length > 1) {
                args = new String[commandWithArgs.length - 1];
                for (int i = 1; i < commandWithArgs.length; i++) {
                    args[i - 1] = commandWithArgs[i];
                }
            } else {
                args = new String[0];
            }
            return new Command(commandWithArgs[0], args);
        }
    }

    public static class Cell {
        public final int y;
        public final int x;

        public Cell(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (y != cell.y) return false;
            return x == cell.x;
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    public enum State {
        NOT_FINISHED("Game not finished"),
        X_WINS("X wins"),
        O_WINS("O wins"),
        DRAW("Draw"),
        IMPOSSIBLE("Impossible");

        private final String value;

        State(String state) {
            value = state;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
