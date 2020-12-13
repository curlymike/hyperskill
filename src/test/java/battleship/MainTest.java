package battleship;

import battleship.exceptions.AreaIsTakenException;
import battleship.exceptions.InvalidCoordinatesException;
import battleship.exceptions.InvalidShipLengthException;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MainTest {

    @Test
    public void parseCoords_001() throws Exception {
        Method parseCoords = Main.class.getDeclaredMethod("parseCoords", String.class);
        parseCoords.setAccessible(true);
        String[] result = (String[]) parseCoords.invoke(null, "A1 A3");
        assertThat(result).isEqualTo(new String[]{"A1","A3"});
    }

    @Test
    public void asString_001() {
        GameField field = new GameField();
        String expected = "  1 2 3 4 5 6 7 8 9 10\n" +
                          "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                          "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        assertThat(Main.asString(field)).isEqualTo(expected);
    }

    @Test
    public void asString_002() {
        GameField field = new GameField();
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "B ~ O O O ~ ~ ~ ~ ~ ~ \n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        field.set("B2", 1);
        field.set("B3", 1);
        field.set("B4", 1);
        assertThat(Main.asString(field)).isEqualTo(expected);
    }

    @Test
    public void asString_003() {
        GameField field = new GameField();
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                        "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "B ~ O ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "C ~ O ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "D ~ O ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        field.set("B2", 1);
        field.set("C2", 1);
        field.set("D2", 1);
        assertThat(Main.asString(field)).isEqualTo(expected);
    }

    @Test
    public void placeShip_001() {
        GameField field = new GameField(10, 10);
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A O O O ~ ~ ~ ~ ~ ~ ~ \n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        field.placeShip("A1", "A3", 3);
        assertThat(Main.asString(field)).isEqualTo(expected);
    }

    @Test
    public void placeShip_002() {
        GameField field = new GameField(10, 10);
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                        "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "D ~ ~ O O O ~ ~ ~ ~ ~ \n" +
                        "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                        "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        field.placeShip("D3", "D5", 3);
        assertThat(Main.asString(field)).isEqualTo(expected);
    }

    @Test
    public void placeShip_003() {
        GameField field = new GameField(10, 10);
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "J ~ ~ ~ ~ ~ ~ ~ O O O \n";
        field.placeShip("J8", "J10", 3);
        assertThat(Main.asString(field)).isEqualTo(expected);
    }

    @Test
    public void placeShip_101() {
        GameField field = new GameField(10, 10);
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "D ~ ~ O O O ~ ~ ~ ~ ~ \n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        Throwable t = catchThrowable(() -> {
            field.placeShip("D3", "D6", 3);
        });
        assertThat(t).isExactlyInstanceOf(InvalidShipLengthException.class);
    }

    @Test
    public void placeShip_102() {
        GameField field = new GameField(10, 10);
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "D ~ ~ O O O ~ ~ ~ ~ ~ \n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";
        Throwable t = catchThrowable(() -> {
            field.placeShip("D3", "E5", 3);
        });
        assertThat(t).isExactlyInstanceOf(InvalidCoordinatesException.class);
    }

    @Test
    public void placeShip_103() {
        GameField field = new GameField(10, 10);
        String expected =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "D ~ ~ O O O ~ ~ ~ ~ ~ \n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n";

        field.placeShip("D3", "D5", 3);

        Throwable t = catchThrowable(() -> {
            field.placeShip("E5", "E7", 3);
        });
        assertThat(t).isExactlyInstanceOf(AreaIsTakenException.class);
    }


//    @Test
//    public void validShip_001() throws Exception {
//        GameField gameField = new GameField();
//        Method validShip = GameField.class.getDeclaredMethod("validShip", GameField.Coords.class, GameField.Coords.class, int.class);
//        validShip.setAccessible(true);
//        GameField.Coords begin = new GameField.Coords(1, 1);
//        GameField.Coords end = new GameField.Coords(1, 1);
//        boolean result = (boolean) validShip.invoke(gameField, begin, end, 1);
//        assertThat(result).isTrue();
//    }
//
//    @Test
//    public void validShip_002() throws Exception {
//        GameField gameField = new GameField();
//        Method validShip = GameField.class.getDeclaredMethod("validShip", GameField.Coords.class, GameField.Coords.class, int.class);
//        validShip.setAccessible(true);
//        GameField.Coords begin = new GameField.Coords(2, 2);
//        GameField.Coords end = new GameField.Coords(2, 4);
//        boolean result = (boolean) validShip.invoke(gameField, begin, end, 3);
//        assertThat(result).isTrue();
//    }
//
//    @Test
//    public void validShip_003() throws Exception {
//        GameField gameField = new GameField();
//        Method validShip = GameField.class.getDeclaredMethod("validShip", GameField.Coords.class, GameField.Coords.class, int.class);
//        validShip.setAccessible(true);
//        GameField.Coords begin = new GameField.Coords(2, 2);
//        GameField.Coords end = new GameField.Coords(4, 2);
//        boolean result = (boolean) validShip.invoke(gameField, begin, end, 3);
//        assertThat(result).isTrue();
//    }
//
//    // Placing ship out of field bounds
//    @Test
//    public void validShip_101() throws Exception {
//        GameField gameField = new GameField();
//        Field widthField = gameField.getClass().getDeclaredField("width");
//        widthField.setAccessible(true);
//        int width = (int) widthField.get(gameField);
//        Method validShip = GameField.class.getDeclaredMethod("validShip", GameField.Coords.class, GameField.Coords.class, int.class);
//        validShip.setAccessible(true);
//        GameField.Coords begin = new GameField.Coords(width - 1, 2);
//        GameField.Coords end = new GameField.Coords(width + 1, 2);
//        boolean result = (boolean) validShip.invoke(gameField, begin, end, 3);
//        assertThat(result).isFalse();
//    }
//
//    // Placing ship out of field bounds
//    @Test
//    public void validShip_102() throws Exception {
//        GameField gameField = new GameField();
//        Field heightField = gameField.getClass().getDeclaredField("height");
//        heightField.setAccessible(true);
//        int height = (int) heightField.get(gameField);
//        Method validShip = GameField.class.getDeclaredMethod("validShip", GameField.Coords.class, GameField.Coords.class, int.class);
//        validShip.setAccessible(true);
//        GameField.Coords begin = new GameField.Coords(2, height - 1);
//        GameField.Coords end = new GameField.Coords(2, height + 1);
//        boolean result = (boolean) validShip.invoke(gameField, begin, end, 3);
//        assertThat(result).isFalse();
//    }
//
//    // At the edge
//    @Test
//    public void validShip_103() throws Exception {
//        GameField gameField = new GameField();
//        Field widthField = gameField.getClass().getDeclaredField("width");
//        widthField.setAccessible(true);
//        int width = (int) widthField.get(gameField);
//        Field heightField = gameField.getClass().getDeclaredField("height");
//        heightField.setAccessible(true);
//        int height = (int) heightField.get(gameField);
//        Method validShip = GameField.class.getDeclaredMethod("validShip", GameField.Coords.class, GameField.Coords.class, int.class);
//        validShip.setAccessible(true);
//        GameField.Coords begin = new GameField.Coords(width - 3, height - 1);
//        GameField.Coords end = new GameField.Coords(width - 1, height - 1);
//        boolean result = (boolean) validShip.invoke(gameField, begin, end, 3);
//        assertThat(result).isTrue();
//    }

    @Test
    public void isFree_001() throws Exception {
        GameField gameField = new GameField();
        Method isFree = GameField.class.getDeclaredMethod("isFree", GameField.Coords.class, GameField.Coords.class);
        isFree.setAccessible(true);
        GameField.Coords begin = new GameField.Coords(1, 1);
        GameField.Coords end = new GameField.Coords(3, 1);
        boolean result = (boolean) isFree.invoke(gameField, begin, end);
        assertThat(result).isTrue();
    }

    @Test
    public void isFree_002() throws Exception {
        GameField gameField = new GameField();
        Method isFree = GameField.class.getDeclaredMethod("isFree", GameField.Coords.class, GameField.Coords.class);
        isFree.setAccessible(true);
        GameField.Coords begin = new GameField.Coords(gameField.width() - 2, gameField.height());
        GameField.Coords end = new GameField.Coords(gameField.width(), gameField.height());
        boolean result = (boolean) isFree.invoke(gameField, begin, end);
        assertThat(result).isTrue();
    }

    @Test
    public void isFree_003() throws Exception {
        GameField gameField = new GameField();
        gameField.set("A2", 1);
        Method isFree = GameField.class.getDeclaredMethod("isFree", GameField.Coords.class, GameField.Coords.class);
        isFree.setAccessible(true);
        GameField.Coords begin = new GameField.Coords(1, 1);
        GameField.Coords end = new GameField.Coords(3, 1);
        boolean result = (boolean) isFree.invoke(gameField, begin, end);
        assertThat(result).isFalse();
    }

    @Test
    public void isFree_004() throws Exception {
        GameField gameField = new GameField(10, 10);
        gameField.set("J10", 1);
        Method isFree = GameField.class.getDeclaredMethod("isFree", GameField.Coords.class, GameField.Coords.class);
        isFree.setAccessible(true);
        GameField.Coords begin = new GameField.Coords(8, 10);
        GameField.Coords end = new GameField.Coords(10, 10);
        boolean result = (boolean) isFree.invoke(gameField, begin, end);
        assertThat(result).isFalse();
    }

    @Test
    public void maxLetter_001() {
        GameField gameField = new GameField(10, 10);
        assertThat(gameField.maxLetter()).isEqualTo('J');
    }

    @Test
    public void set_001() {
        GameField field = new GameField();
        field.set("A1", 1);
        int[][] gameField = field.getField();
        assertThat(gameField[0][0]).isEqualTo(1);
    }

    @Test
    public void letterToIndex_001() throws Exception {
        Method letterToIndex = GameField.class.getDeclaredMethod("letterToIndex", char.class);
        letterToIndex.setAccessible(true);
        GameField gameField = new GameField();
        int result = (int) letterToIndex.invoke(gameField, 'A');
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void translate_001() throws Exception {
        Method translate = GameField.class.getDeclaredMethod("translate", String.class);
        translate.setAccessible(true);
        GameField gameField = new GameField();
        Throwable throwable = catchThrowable(() -> {
            translate.invoke(gameField, "a1"); // yes, lowercase!
        });
        assertThat(throwable.getCause()).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void translate_101() throws Exception {
        Method translate = GameField.class.getDeclaredMethod("translate", String.class);
        translate.setAccessible(true);
        GameField gameField = new GameField();
        GameField.Coords coords = (GameField.Coords) translate.invoke(gameField, "A1");
        assertThat(coords).isEqualTo(new GameField.Coords(1, 1));
    }

    @Test
    public void translate_102() throws Exception {
        Method translate = GameField.class.getDeclaredMethod("translate", String.class);
        translate.setAccessible(true);
        GameField gameField = new GameField();
        GameField.Coords coords = (GameField.Coords) translate.invoke(gameField, "J10");
        assertThat(coords).isEqualTo(new GameField.Coords(10, 10));
    }

}