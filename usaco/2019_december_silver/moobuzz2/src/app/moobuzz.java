package app;

import java.util.*;
import java.io.*;

public class moobuzz {
    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("moobuzz.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("moobuzz.out"));

        // Scanner in = new Scanner(System.in);

        // long num = in.nextInt();
        // long targetIndex = 0, target = 0;
        // for (int i = 1; targetIndex != num; i = i + 1) {
        // if (i % 3 != 0 && i % 5 != 0) {
        // targetIndex = targetIndex + 1;
        // target = i;
        // }
        // }

        // int num = in.nextInt();
        // long targetIndex = 0, target = 0, i = 1;
        // while (targetIndex != num) {
        // if (i % 3 != 0 && i % 5 != 0) {
        // targetIndex = targetIndex + 1;
        // target = i;
        // }
        // i = i + 1;
        // }

        System.out.println(rec(4));

        // in.close();
        // SYstem.ou.close();
    }

    private static int rec(int n) {
        if (n == 1)
            return 1;
        int prev = rec(n - 1);
        int count = 0;
        for (int i = prev; i <= n; i++)
            if (i % 3 == 0 || i % 5 == 0)
                count++;
        return prev + count + 1;
    }
}