package battleship;

import battleship.exceptions.AreaIsTakenException;
import battleship.exceptions.InvalidCoordinatesException;
import battleship.exceptions.InvalidShipLengthException;

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
    }

    public static void placeShips(GameField field) {
        for (ShipType shipType : ShipType.values()) {
            print(field);
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", shipType.getName(), shipType.size());
            System.out.print("> ");
            boolean success = false;
            while (!success) {
                String coordPair = scanner.nextLine();
                try {
                    String[] coordsArr = parseCoords(coordPair);
                    field.placeShip(coordsArr[0], coordsArr[1], shipType.size());
                    success = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error! Invalid input! Try again:");
                    System.out.print("> ");
                } catch (InvalidCoordinatesException e) {
                    System.out.println("Error! Wrong ship location! Try again:");
                    System.out.print("> ");
                } catch (InvalidShipLengthException e) {
                    System.out.printf("Error! Wrong length of the %s! Try again:%n", shipType.getName());
                    System.out.print("> ");
                } catch (AreaIsTakenException e) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.print("> ");
                }
            }
        }
    }

    public static void print(GameField field) {
        System.out.println(asString(field));
    }

    private static String[] parseCoords(String coordPair) {
        Matcher m = coordPairPattern.matcher(coordPair);
        if (!m.matches()) {
            throw new IllegalArgumentException();
        }
        return new String[]{m.group(1), m.group(2)};
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
