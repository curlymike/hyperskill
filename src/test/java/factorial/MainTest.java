package factorial;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void fib2_of_10_is_55() {
        assertThat(Main.fib2(10)).isEqualTo(55);
    }

    @Test
    public void fib2_of_20_is_6765() {
        assertThat(Main.fib2(20)).isEqualTo(6765);
    }

    @Test
    public void fib2_of_43_is_433494437() {
        assertThat(Main.fib2(43)).isEqualTo(433494437);
    }

    @Test
    public void fib2_of_20_is_4181() {
        assertThat(Main.fib2(10)).isEqualTo(55);
    }

    @Test
    public void factorial_of_5_is_120() {
        assertThat(Main.factorial(5)).isEqualTo(120);
    }

    @Test
    public void factorial_of_8_is_40320() {
        assertThat(Main.factorial(8)).isEqualTo(40320);
    }


}