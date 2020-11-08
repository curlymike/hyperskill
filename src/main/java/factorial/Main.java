package factorial;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("1=" + fib(1));
        System.out.println("2=" + fib(2));
        System.out.println("3=" + fib(3));
        System.out.println("4=" + fib(4));
        System.out.println("5=" + fib(5));
        System.out.println("20=" + fib(20));
        long time1 = System.currentTimeMillis();
        System.out.println("43=" + fib(43));
        System.out.println("time=" + (System.currentTimeMillis() - time1) + "ms");

        time1 = System.currentTimeMillis();
        System.out.println("43=" + fib2(43));
        System.out.println("time=" + (System.currentTimeMillis() - time1) + "ms");

    }

    public static long fib(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static long fib2(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        long[] numbers = new long[n + 1];
        numbers[0] = 0;
        numbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            numbers[i] = numbers[i - 1] + numbers[i - 2];
        }
        return numbers[n];
    }

    public static long factorial(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        if (n == 0)
            return 1;
        return factorial(n - 1) * n;
    }

}
