import java.util.*;
import java.io.*;

public class socdist {
    static int n, m;
    static int[][] intervals;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("socdist.in")));
        PrintWriter out = new PrintWriter(new FileWriter("socdist.out"));
        // Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        intervals = new int[m][2];
        for (int i = 0; i < m; i++)
            intervals[i] = new int[] { in.nextInt(), in.nextInt() };

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        // System.out.println(Arrays.deepToString(intervals));

        int ans = -1, l = intervals[0][0], r = intervals[intervals.length - 1][1];

        // binary search
        while (l < r) {
            int m = (l + r) / 2;
            if (possible(m)) {
                l = m + 1;
                ans = Math.max(m, ans);
            } else
                r = m;
        }

        out.println(ans);

        out.close();
        in.close();
    }

    private static boolean possible(int dist) {
        int curr = intervals[0][0], els = 1;

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] - curr >= dist) {
                curr = intervals[i][0];
                els++;
                if (els == n)
                    return true;
            }
            while (curr + dist <= intervals[i][1]) {
                curr += dist;
                els++;
                if (els == n)
                    return true;
            }
        }
        return false;
    }
}