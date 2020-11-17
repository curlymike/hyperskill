package tictactoe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tictactoe.Main.X;
import static tictactoe.Main.O;

public class MiniMaxTest {

    @Test
    public void compute_001() {
        int[][] field = {
                {O, 0, X},
                {X, 0, X},
                {0, O, O},
        };
        assertThat(MiniMax.compute(field, X)).isEqualTo(10);
    }

    @Test
    public void compute_002() {
        int[][] field = {
                {O, X, X},
                {X, 0, X},
                {0, O, O},
        };
        assertThat(MiniMax.compute(field, X, MiniMax.MIN)).isEqualTo(-10);
    }

    @Test
    public void compute_003() {
        int[][] field = {
                {O, 0, X},
                {X, 0, X},
                {X, O, O},
        };
        assertThat(MiniMax.compute(field, X, MiniMax.MIN)).isEqualTo(-10);
        assertThat(MiniMax.compute(field, X, MiniMax.MAX)).isEqualTo(10);
    }

}