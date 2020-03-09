import java.util.*;
import java.io.*;

public class gymnastics {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("gymnastics.in")));
        PrintWriter out = new PrintWriter(new FileWriter("gymnastics.out"));

        String[] first = in.nextLine().split(" ");
        int k = Integer.parseInt(first[0]);
        int n = Integer.parseInt(first[1]);

        int[][] data = new int[k][n];
        for (int i = 0; i < k; i++)
            for (int j = 0; j < n; j++)
                data[i][j] = in.nextInt();

        int pairs = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    continue;
                boolean consistent = true;
                for (int l = 0; l < k; l++)
                    if (indexOf(data[l], i) > indexOf(data[l], j)) {
                        consistent = false;
                        break;
                    }
                if (consistent)
                    pairs++;
            }
        }

        out.println(pairs);
        in.close();
        out.close();
    }

    private static int indexOf(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == num)
                return i;
        return -1;
    }
}