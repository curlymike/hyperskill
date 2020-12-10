package sandbox;

public class Main {
    public static void main(String[] args) {
        (new A()).test();
        (new B()).test();

        System.out.println("---");

        new Throwable();
        new IllegalArgumentException();
        new NullPointerException();
        String str = "";
        try {
            str.charAt(5);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("StringIndexOutOfBoundsException");
        }
    }
}
