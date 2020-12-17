package battleship;

import battleship.exceptions.AreaIsTakenException;
import battleship.exceptions.InvalidCoordinatesException;
import battleship.exceptions.InvalidShipLengthException;

import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern coordPairPattern = Pattern.compile("^([A-Z]{1}[0-9]+)\\s+([A-Z]{1}[0-9]+)$");
    private static GameField field = new GameField();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        placeShips(field);
        print(field);
        startGame(field);
    }

    public static void startGame(GameField field) {
        System.out.println("The game starts!");
        System.out.println();
        print(field, true);
        System.out.println("Take a shot!");
        while (!isAllShipSunk(field)) {
            boolean success = false;
            int result = -1;

            while (!success) {
                String coordinates = scanner.nextLine();
                try {
                    result = field.fire(coordinates);
                    success = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                }
            }
            System.out.println();
            print(field, true);
            switch (result) {
                case GameField.RESULT_HIT:
                case GameField.RESULT_HIT_AND_SUNK:
                    if (isAllShipSunk(field)) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else if (result == GameField.RESULT_HIT_AND_SUNK) {
                        System.out.println("You sank a ship! Specify a new target:");
                    } else {
                        System.out.println("You hit a ship! Try again:");
                    }
                    break;
                case GameField.RESULT_MISS:
                    System.out.println("You missed!");
                    break;
            }
            System.out.println();
            //print(field);
        }
    }

    public static void placeShipsQuick(GameField field) {
        field.reset(); // just in case
        field.placeShip("F3", "F7", -1);
        field.placeShip("A1", "D1", -1);
        field.placeShip("J10", "J8", -1);
        field.placeShip("B9", "D9", -1);
        field.placeShip("I2", "J2", -1);
    }
    public static void placeShipsQuicker(GameField field) {
        field.reset(); // just in case
        field.placeShip("I2", "J2", -1);
    }
    public static void placeShipsQuicker2(GameField field) {
        field.reset(); // just in case
        field.placeShip("B9", "D9", -1);
        field.placeShip("I2", "J2", -1);
    }

    public static void placeShips(GameField field) {
        for (ShipType shipType : ShipType.values()) {
            print(field);
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", shipType.getName(), shipType.size());
            //System.out.print("> ");
            boolean success = false;
            while (!success) {
                String coordPair = scanner.nextLine();
                if ("***".equals(coordPair)) {
                    placeShipsQuick(field);
                    return;
                } else if ("****".equals(coordPair)) {
                    placeShipsQuicker(field);
                    return;
                } else if ("*****".equals(coordPair)) {
                    placeShipsQuicker2(field);
                    return;
                }
                try {
                    String[] coordsArr = parseCoords(coordPair);
                    field.placeShip(coordsArr[0], coordsArr[1], shipType.size());
                    success = true;
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error! Invalid input! Try again:");
                    //System.out.print("> ");
                } catch (InvalidCoordinatesException e) {
                    System.out.println("Error! Wrong ship location! Try again:");
                    //System.out.print("> ");
                } catch (InvalidShipLengthException e) {
                    System.out.printf("Error! Wrong length of the %s! Try again:%n", shipType.getName());
                    //System.out.print("> ");
                } catch (AreaIsTakenException e) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    //System.out.print("> ");
                }
            }
        }
    }

    public static boolean isAllShipSunk(GameField gameField) {
        int[][] field = gameField.getField();
        Iterator<Integer> iter = gameField.iterator();
        int shipCellCount = 0;
        while (iter.hasNext()) {
            if (iter.next() == GameField.SHIP) {
                shipCellCount++;
            }
        }
        return shipCellCount == 0;
    }

    private static String[] parseCoords(String coordPair) {
        Matcher m = coordPairPattern.matcher(coordPair);
        if (!m.matches()) {
            throw new IllegalArgumentException();
        }
        return new String[]{m.group(1), m.group(2)};
    }

    public static void print(GameField field) {
        print(field, false);
    }

    public static void print(GameField field, boolean fogged) {
        System.out.println(asString(field, fogged));
    }

    public static String asString(GameField field) {
        return asString(field, false);
    }

    public static String asString(GameField field, boolean fogged) {
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
                switch (gameField[y][x]) {
                    case GameField.FREE: sb.append('~'); break;
                    case GameField.SHIP: sb.append(fogged ? '~' : 'O'); break;
                    case GameField.HIT: sb.append('X'); break;
                    case GameField.MISS: sb.append('M'); break;
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
