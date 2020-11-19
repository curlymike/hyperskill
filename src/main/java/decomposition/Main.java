package decomposition;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        decomp2(1);
//        System.out.println("------------");
//        decomp2(2);
//        System.out.println("------------");
        //decomp2(5);
        //decomp2(7);
        //decomp2(4);
        int n = 5;
        for (String str : decomp(n, n)) {
            System.out.println(str);
        }
//        System.out.println("------------");
//        decomp2(4);
//        System.out.println("------------");
//        decomp2(5);
//        System.out.println("------------");
    }

    // Do I even need "cap" here?
    public static List<String> decomp(int cap, int n) {
        List<String> output = new ArrayList<>();
        if (n > 1) {
            for (int i = 1; i <= cap && i <= n; i++) {
                for (String str : decomp(i, n - i)) {
                    StringBuilder sb = new StringBuilder();
                    output.add(sb.append(i).append(' ').append(str).toString());
                }
            }
        } else if (n == 1) {
            output.add("1");
        } else {
            output.add(String.valueOf(n));
        }
        return output;
    }

}
