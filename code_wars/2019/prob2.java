import java.util.*;

public class prob2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();

        System.out.printf("%d %d %d. ", h, m, s);
        System.out.println((double) m / s <= h ? "I will make it!" : "I will be late!");
    }
}