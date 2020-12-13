package battleship;

import battleship.exceptions.AreaIsTakenException;
import battleship.exceptions.InvalidCoordinatesException;
import battleship.exceptions.InvalidShipLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameField {
    private static final int DEFAULT_SIZE = 10;
    private static final int ASCII_OFFSET = 64;
    private static final Pattern coordPattern = Pattern.compile("^([A-Z]{1})([0-9]+)$");

    public static final int FREE = 0;
    public static final int SHIP = 1;
    public static final int HIT = 2;
    public static final int MISS = 3;

    private final int[][] field = new int[DEFAULT_SIZE][DEFAULT_SIZE];
    private final int width;
    private final int height;

    public GameField() {
        this(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void reset() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                field[y][x] = 0;
            }
        }
    }

    /**
     * Fire at the field
     * @param coordinates
     * @return int indicating hit or miss (see the constants at the top of the class)
     * @throws InvalidCoordinatesException in case invalid or out of bounds coordinates were given
     */

    public int fire(String coordinates) {
        Coords coords = translate(coordinates);
        set(coords, get(coords) == SHIP ? HIT : MISS);
        return get(coords);
    }

    // Wrong length of the Submarine (if not vertical, horizontal, or wrong length)
    // Wrong ship location
    // You placed it too close to another one
    public void placeShip(String beginStr, String endStr, int expectedLength) {
        Coords begin = null;
        Coords end = null;
        try {
            begin = translate(beginStr);
            end = translate(endStr);
        } catch (IllegalArgumentException e) {
            throw new InvalidCoordinatesException();
        }
        // this throws InvalidCoordinatesException is coords are skewed
        int length = computeLength(begin, end);
        if (expectedLength != -1 && length != expectedLength) {
            throw new InvalidShipLengthException();
        }
        if (!isFree(begin, end)) {
            throw new AreaIsTakenException();
        }
        setRange(begin, end, 1);
    }

    /**
     * Checks begin to end plus 1 step around
     * returns true if the area is free, false otherwise
     */

    private boolean isFree(Coords begin, Coords end) {
        int y1 = begin.y < end.y ? begin.y - 1 : end.y - 1;
        int y2 = begin.y >= end.y ? begin.y + 1 : end.y + 1;
        int x1 = begin.x < end.x ? begin.x - 1 : end.x - 1;
        int x2 = begin.x >= end.x ? begin.x + 1 : end.x + 1;
        if (y1 < 1) y1 = 1;
        if (x1 < 1) x1 = 1;
        if (y2 > height) y2 = height;
        if (x2 > width) x2 = width;
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (field[y - 1][x - 1] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setRange(Coords begin, Coords end, int value) {
        int y1 = begin.y < end.y ? begin.y : end.y;
        int y2 = begin.y >= end.y ? begin.y : end.y;
        int x1 = begin.x < end.x ? begin.x : end.x;
        int x2 = begin.x >= end.x ? begin.x : end.x;
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                set(x, y, value);
            }
        }
    }

    private boolean validCoordinates(Coords coords) {
        return coords.x > 0 && coords.x <= width && coords.y > 0 && coords.y <= height;
    }

    /**
     * Computes ship length
     * @param begin
     * @param end
     * @return ship length according to given coordinates
     * @throws InvalidCoordinatesException
     */

    private int computeLength(Coords begin, Coords end) {
        if (validCoordinates(begin) && validCoordinates(end)) {
            int coordLength = -1;
            if (begin.x == end.x) {
                coordLength = begin.y > end.y ? begin.y - end.y : end.y - begin.y;
            } else if (begin.y == end.y) {
                coordLength = begin.x > end.x ? begin.x - end.x : end.x - begin.x;
            }
            if (coordLength >= 0) {
                return coordLength + 1;
            }
        }
        throw new InvalidCoordinatesException();
    }

//    private boolean validShip(Coords begin, Coords end, int length) {
//        if (begin.x != end.x && begin.y != end.y) {
//            return false;
//        }
//        int coordLength;
//        if (begin.x == end.x) {
//            coordLength = begin.y > end.y ? begin.y - end.y : end.y - begin.y;
//        } else if (begin.y == end.y) {
//            coordLength = begin.x > end.x ? begin.x - end.x : end.x - begin.x;
//        } else {
//            return false;
//        }
//        return coordLength + 1 == length && validCoordinates(begin) && validCoordinates(end);
//    }

    /**
     *
     * @param coordinates
     * @param value
     * @throws IllegalArgumentException in case given coordinates are out of bounds
     */

    public void set(String coordinates, int value) {
        Coords coords = translate(coordinates);
        set(coords, value);
    }

    /**
     *
     * @param coords
     * @param value
     * @throws IllegalArgumentException in case given coordinates are out of bounds
     */

    public void set(Coords coords, int value) {
        set(coords.x, coords.y, value);
    }

    /**
     *
     * @param x
     * @param y
     * @param value
     * @throws IllegalArgumentException in case given coordinates are out of bounds
     */

    private void set(int x, int y, int value) {
        try {
            field[y - 1][x - 1] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param coords
     * @return
     * @throws IllegalArgumentException
     */

    private Coords translate(String coords) {
        Matcher m = coordPattern.matcher(coords);
        if (!m.matches()) {
            throw new IllegalArgumentException();
        }
        int y = letterToIndex(m.group(1).charAt(0));
        int x = Integer.valueOf(m.group(2));
        return new Coords(x, y);
    }

    private int letterToIndex(char letter) {
        return letter - ASCII_OFFSET;
    }

    /**
     *
     * @param coords
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */

    private int get(Coords coords) {
        try {
            return get(coords.x, coords.y);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */

    private int get(int x, int y) {
        return field[y - 1][x - 1];
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int maxNumber() {
        return width;
    }
    public char maxLetter() {
        return (char) (height + ASCII_OFFSET);
    }

    public int[][] getField() {
        return field;
    }

    /**
     *
     *
     *
     */

    static class Coords {
        final int x;
        final int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coords coords = (Coords) o;

            if (x != coords.x) return false;
            return y == coords.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "Coords{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
