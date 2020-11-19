package pow.recursive;

public class Main {

    public static double pow(double a, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        if (n == 2) {
            return a * a;
        }
        if (n % 2 == 0) {
            return pow(a * a, n / 2);
        }
        return a * pow(a, n - 1);
    }
}
