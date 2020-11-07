package tictactoe;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class MediumStrategyTest {
    private Main.Cell invoke_oneMoveWin(int[][] field, int character) throws Exception {
        MediumStrategy strategy = new MediumStrategy();
        Method oneMoveWin = MediumStrategy.class.getDeclaredMethod("oneMoveWin", int[][].class, int.class);
        oneMoveWin.setAccessible(true);
        return (Main.Cell) oneMoveWin.invoke(strategy, field, character);
    }

    @Test
    public void oneMoveWin_test001() throws Exception {
        int[][] field = {
                {0, 1, 1},
                {2, 0, 0},
                {0, 0, 2},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(0, 0));
    }

    @Test
    public void oneMoveWin_test002() throws Exception {
        int[][] field = {
                {1, 1, 0},
                {2, 0, 0},
                {0, 0, 2},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(0, 2));
    }

    @Test
    public void oneMoveWin_test003() throws Exception {
        int[][] field = {
                {0, 1, 0},
                {2, 1, 0},
                {0, 0, 2},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(2, 1));
    }

    @Test
    public void oneMoveWin_test004() throws Exception {
        int[][] field = {
                {1, 0, 2},
                {2, 1, 0},
                {0, 0, 0},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(2, 2));
    }

    @Test
    public void oneMoveWin_test005() throws Exception {
        int[][] field = {
                {0, 0, 2},
                {2, 1, 0},
                {0, 0, 1},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(0, 0));
    }

    @Test
    public void oneMoveWin_test006() throws Exception {
        int[][] field = {
                {0, 0, 1},
                {2, 1, 0},
                {0, 0, 2},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(2, 0));
    }

    @Test
    public void oneMoveWin_test007() throws Exception {
        int[][] field = {
                {0, 0, 0},
                {2, 1, 0},
                {1, 0, 2},
        };
        Main.Cell result = invoke_oneMoveWin(field, 1);
        assertThat(result).isEqualTo(new Main.Cell(0, 2));
    }

}