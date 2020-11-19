package decomposition;

public class Main {

    public static void main(String[] args) {
        decomp2(1);
        System.out.println("------------");
        decomp2(2);
        System.out.println("------------");
        decomp2(3);
//        System.out.println("------------");
//        decomp2(4);
//        System.out.println("------------");
//        decomp2(5);
//        System.out.println("------------");
    }

    public static void decomp2(int n) {
        if (n == 1) {
            System.out.print("1 ");
        } else if (n == 2) {
            System.out.print("1 1 ");
        } else {
            decomp2(n - 1);
            System.out.print("1 ");
        }
    }

//    public static int[] decomp(int n) {
//        if (n == 1) {
//            int[] arr = new int[1];
//            arr[0] = 1;
//            return arr;
//        }
//        if (n == 2) {
//            int[] arr = new int[2];
//            arr[0] = 1;
//            arr[1] = 1;
//            return arr;
//        }
//        return decomp(n - 1);
//    }
}
