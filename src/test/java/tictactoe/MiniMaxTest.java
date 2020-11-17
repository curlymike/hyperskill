package tictactoe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tictactoe.Main.X;
import static tictactoe.Main.O;

public class MiniMaxTest {

    public static MiniMax.Result compute(int[][] field, int player) {
        return MiniMax.compute(field, player);
    }
    public static MiniMax.Result compute(int[][] field, int player, MiniMax miniMax) {
        return MiniMax.compute(field, player, miniMax);
    }

    @Test
    public void compute_001() {
        int[][] field = {
                {O, 0, X},
                {X, 0, X},
                {0, O, O},
        };
        MiniMax.Result result = compute(field, X);
        assertThat(result.getScore()).isEqualTo(10);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(1, 1));
    }

    @Test
    public void compute_001_1() {
        int[][] field = {
                {O, X, 0},
                {X, O, 0},
                {O, 0, X},
        };
        MiniMax.Result result = compute(field, X);
        assertThat(result.getScore()).isEqualTo(0);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(0, 2));
    }

    @Test
    public void compute_001_2() {
        int[][] field = {
                {O, X, 0},
                {X, O, 0},
                {O, 0, X},
        };
        field[2][1] = X;
        MiniMax.Result result = compute(field, O);
        assertThat(result.getScore()).isEqualTo(10);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(0, 2));
    }

    @Test
    public void compute_002() {
        int[][] field = {
                {O, X, X},
                {X, 0, X},
                {0, O, O},
        };
        assertThat(compute(field, X, MiniMax.MIN).getScore()).isEqualTo(-10);
    }

    @Test
    public void compute_003() {
        int[][] field = {
                {O, 0, X},
                {X, 0, X},
                {X, O, O},
        };
        assertThat(compute(field, X, MiniMax.MIN).getScore()).isEqualTo(-10);
        assertThat(compute(field, X, MiniMax.MAX).getScore()).isEqualTo(10);
    }

    @Test
    public void compute_100() {
        int[][] field = {
                {0, 0, 0},
                {X, O, 0},
                {0, 0, 0},
        };
        MiniMax.Result result = compute(field, X);
        //assertThat(result.getScore()).isEqualTo(10);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(1, 0));
    }

    @Test
    public void compute_101() {
        int[][] field = {
                {0, 0, 0},
                {X, O, 0},
                {0, 0, 0},
        };
        MiniMax.Result result = compute(field, O);
        //assertThat(result.getScore()).isEqualTo(10);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(0, 1));
    }

    @Test
    public void compute_102() {
        int[][] field = {
                {0, X, 0},
                {X, O, 0},
                {0, O, 0},
        };
        MiniMax.Result result = compute(field, O);
        //assertThat(result.getScore()).isEqualTo(10);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(0, 0));
    }

    @Test
    public void compute_103() {
        int[][] field = {
                {X, X, 0},
                {X, O, 0},
                {O, O, 0},
        };
        MiniMax.Result result = compute(field, O);
        //assertThat(result.getScore()).isEqualTo(10);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(0, 2));
    }

    @Test
    public void compute_200() {
        int[][] field = {
                {O, O, X},
                {0, 0, 0},
                {X, 0, 0},
        };
        MiniMax.Result result = compute(field, X);
        assertThat(result.getCell()).isEqualTo(new Main.Cell(1, 1));
    }

}