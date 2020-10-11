package bulls_cows;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {
    public static final String SECRET = "9305";

    @Test
    public void grade001() {
        assertThat(Main.grade(SECRET, "9305")).isEqualTo(new int[] {4, 0});
    }

    //The numbers 3059, 3590, 5930, 5039 contain 0 bulls and 4 cows since all 4 digits are correct but their positions don't match the positions in the secret code.

    @Test
    public void grade020() {
        assertThat(Main.grade(SECRET, "3059")).isEqualTo(new int[] {0, 4});
    }

    @Test
    public void grade021() {
        assertThat(Main.grade(SECRET, "3590")).isEqualTo(new int[] {0, 4});
    }

    @Test
    public void grade022() {
        assertThat(Main.grade(SECRET, "5930")).isEqualTo(new int[] {0, 4});
    }

    @Test
    public void grade023() {
        assertThat(Main.grade(SECRET, "5039")).isEqualTo(new int[] {0, 4});
    }

    //The numbers 9306, 9385, 9505, 1305 contain 3 bulls.

    @Test
    public void grade030() {
        assertThat(Main.grade(SECRET, "9306")).isEqualTo(new int[] {3, 0});
    }

    @Test
    public void grade031() {
        assertThat(Main.grade(SECRET, "9385")).isEqualTo(new int[] {3, 0});
    }

    @Test
    public void grade032() {
        // Task description said it should be {3, 0} but I believe that's an error, there is one cow too
        assertThat(Main.grade(SECRET, "9505")).isEqualTo(new int[] {3, 1});
    }

    @Test
    public void grade033() {
        assertThat(Main.grade(SECRET, "1305")).isEqualTo(new int[] {3, 0});
    }

    //The numbers 9350, 9035, 5309, 3905 contain 2 bulls and 2 cows.

    @Test
    public void grade040() {
        assertThat(Main.grade(SECRET, "9350")).isEqualTo(new int[] {2, 2});
    }

    @Test
    public void grade041() {
        assertThat(Main.grade(SECRET, "9035")).isEqualTo(new int[] {2, 2});
    }

    @Test
    public void grade042() {
        assertThat(Main.grade(SECRET, "5309")).isEqualTo(new int[] {2, 2});
    }

    @Test
    public void grade043() {
        assertThat(Main.grade(SECRET, "3905")).isEqualTo(new int[] {2, 2});
    }

    //The numbers 1293, 5012, 3512, 5129 contain 0 bulls and 2 cows.

    @Test
    public void grade050() {
        assertThat(Main.grade(SECRET, "1293")).isEqualTo(new int[] {0, 2});
    }

    @Test
    public void grade051() {
        assertThat(Main.grade(SECRET, "5012")).isEqualTo(new int[] {0, 2});
    }

    @Test
    public void grade052() {
        assertThat(Main.grade(SECRET, "3512")).isEqualTo(new int[] {0, 2});
    }

    @Test
    public void grade053() {
        assertThat(Main.grade(SECRET, "5129")).isEqualTo(new int[] {0, 2});
    }

    //The numbers 1246, 7184, 4862, 2718 contain 0 bulls and 0 cows.

    @Test
    public void grade060() {
        assertThat(Main.grade(SECRET, "1246")).isEqualTo(new int[] {0, 0});
    }

    @Test
    public void grade061() {
        assertThat(Main.grade(SECRET, "7184")).isEqualTo(new int[] {0, 0});
    }

    @Test
    public void grade062() {
        assertThat(Main.grade(SECRET, "4862")).isEqualTo(new int[] {0, 0});
    }

    @Test
    public void grade063() {
        assertThat(Main.grade(SECRET, "2718")).isEqualTo(new int[] {0, 0});
    }

    //The number 1111 contains 0 bulls and 0 cows.
    //The number 9999 contains 1 bull and 3 cows.
    //The number 9955 contains 2 bulls and 2 cows.

    @Test
    public void grade070() {
        assertThat(Main.grade(SECRET, "1111")).isEqualTo(new int[] {0, 0});
    }

    @Test
    public void grade071() {
        assertThat(Main.grade(SECRET, "9999")).isEqualTo(new int[] {1, 3});
    }

    @Test
    public void grade072() {
        assertThat(Main.grade(SECRET, "9955")).isEqualTo(new int[] {2, 2});
    }

}