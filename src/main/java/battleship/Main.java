package battleship;

public class Main {
    private static GameField field = new GameField();

    public static void main(String[] args) {
        placeShips(field);
    }

    public static void placeShips(GameField field) {
        for (ShipType shipType : ShipType.values()) {
            System.out.println(shipType.size() + " - " + shipType.getName());
        }
    }

    public static void print(GameField field) {
        System.out.println(asString(field));
    }

    public static String asString(GameField field) {
        char[] letters = new char[]{'A','B','C','D','E','F','G','H','I','J'};
        int[][] gameField = field.getField();
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int y = 0; y < field.width(); y++) {
            sb.append(' ').append(y + 1);
        }
        sb.append('\n');
        int[][] f = field.getField();
        for (int y = 0; y < field.width(); y++) {
            sb.append(letters[y]).append(' ');
            for (int x = 0; x < field.height(); x++) {
                sb.append(gameField[y][x] == 0 ? '~' : 'O').append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
