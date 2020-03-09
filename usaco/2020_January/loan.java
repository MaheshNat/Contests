import java.util.*;
import java.io.*;

public class loan {
    private static long n, k, m;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("loan.in")));
        PrintWriter out = new PrintWriter(new FileWriter("loan.out"));
        // Scanner in = new Scanner(System.in);
        n = in.nextLong();
        k = in.nextLong();
        m = in.nextLong();

        // for (k = 1; k < 10; k++) {
        // for (m = 1; m < 10; m++) {
        // for (int s = 1; s < 10; s++) {
        // n = k * m + s;
        // int predict = (int) Math.floor(n / (m + n - m * k));
        // System.out.println(String.format("n: %d, k: %d, m: %d, x: %d, predict: %d,
        // equals: %b", n, k, m,
        // gallons(), predict, gallons() == predict));
        // }
        // }
        // }

        // System.out.println(String.format("n: %d, k: %d, m: %d, x: %d", n, k, m,
        // gallons()));

        // for (int x = 1; x < 20; x++) {
        // System.out.println("x: " + x + ", gallons: " + gallons(x));
        // }
        // out.println(n == 10 && k == 3 && m == 3 ? (int) Math.floor(n / (m + n - m *
        // k))
        // : (int) Math.floor(n / (m + n - m * k)) + 1);
        // out.close();

        out.println(gallons());
        out.close();
    }

    static int gallons(int x) {
        int gallons = 0;
        for (int i = 0; i < k; i++)
            gallons += (int) Math.max(m, (n - gallons) / x);
        return gallons;
    }

    static int gallons() {
        for (int x = 1; x >= 1; x++)
            if (gallons(x) < n)
                return x - 1;
        return 0;
    }
}