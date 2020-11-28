package decomposition;

import java.util.ArrayList;
import java.util.List;

/**
 * Ok, so now that it works and I checked out comments and links
 * and stuff, the theory behind this task is "integer partitions".
 * See https://www.youtube.com/watch?v=XN3Vy2M5ov8
 * Task: https://hyperskill.org/learn/step/3126
 */

/**
 * Each number can be broken down into parts, or addends. For example, number 3 may be broken down into such addends as 1 + 1 + 1, 2 + 1, 3. This procedure is known as decomposition.
 * In this task, you'll need to find out all decompositions of number N (1 <= N <= 40) and list its positive addends. The decomposition should be printed in lexicographical order.
 * Each decomposition should consist of the addends in a descending order, where each subsequent number of the list is equal or less than the previous one.
 * Note, that inversion of addends doesn't count and 2 + 1 and 1 + 2 are the same operations.
 * 0 is not a positive number.
 *
 * Sample Input 1:
 *
 * 5
 * Sample Output 1:
 *
 * 1 1 1 1 1
 * 2 1 1 1
 * 2 2 1
 * 3 1 1
 * 3 2
 * 4 1
 * 5
 */

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
