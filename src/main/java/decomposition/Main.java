package decomposition;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 5;
        for (String str : decomp(n, n)) {
            System.out.println(str);
        }
    }

    public static List<String> decomp(int cap, int n) {
        List<String> output = new ArrayList<>();
        if (n > 1) {
            for (int i = 1; i <= cap && i <= n; i++) {
                for (String str : decomp(i, n - i)) {
                    StringBuilder sb = new StringBuilder();
                    output.add(sb.append(i).append(' ').append(str).toString());
                }
                if (n - i == 0) {
                    output.add(String.valueOf(n));
                }
            }
        } else if (n == 1) {
            output.add("1");
        }
        return output;
    }

}
