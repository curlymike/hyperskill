package bulls_cows;

public class Main {
    public static void main(String[] args) {

    }

    public static int[] grade(String secret, String guess) {
        int[] result = {0, 0};

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                for (int f = 0; f < secret.length(); f++) {
                    if (guess.charAt(i) == secret.charAt(f)) {
                        cows++;
                        break;
                    }
                }
            }
        }
        result[0] = bulls;
        result[1] = cows;

        return result;
    }

    /**
     *
     */

    public static class Result {
        private final int bulls;
        private final int cows;

        public Result(int bulls, int cows) {
            this.bulls = bulls;
            this.cows = cows;
        }

        public int getBulls() {
            return bulls;
        }

        public int getCows() {
            return cows;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Result result = (Result) o;

            if (bulls != result.bulls) return false;
            return cows == result.cows;
        }

        @Override
        public int hashCode() {
            int result = bulls;
            result = 31 * result + cows;
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "bulls=" + bulls +
                    ", cows=" + cows +
                    '}';
        }
    }

}
