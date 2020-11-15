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

}