package pow.recursive;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void pow_2_0__1() {
        assertThat(Main.pow(2, 0)).isEqualTo(1);
    }

    @Test
    public void pow_2_1__2() {
        assertThat(Main.pow(2, 1)).isEqualTo(2);
    }

    @Test
    public void pow_2_2__4() {
        assertThat(Main.pow(2, 2)).isEqualTo(4);
    }

    @Test
    public void pow_2_3__8() {
        assertThat(Main.pow(2, 3)).isEqualTo(8);
    }

    @Test
    public void pow_2_4__16() {
        assertThat(Main.pow(2, 4)).isEqualTo(16);
    }



}