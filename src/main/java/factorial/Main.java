package factorial;

public class Main {
    public static void main(String[] args) {
        System.out.println("1=" + calc(1));
        System.out.println("2=" + calc(2));
        System.out.println("3=" + calc(3));
        System.out.println("4=" + calc(4));
        System.out.println("5=" + calc(5));
    }

    public static long calc(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        if (n == 0)
            return 1;
        return calc(n - 1) * n;
    }

}
