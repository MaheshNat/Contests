import java.util.*;
import java.io.*;

public class berries {

    private static int n, k;
    private static int[] berries;

    public static void main(String[] args) {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("berries.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("berries.out"));
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        berries = new int[n];
        for (int i = 0; i < n; i++)
            berries[i] = in.nextInt();

    }
}