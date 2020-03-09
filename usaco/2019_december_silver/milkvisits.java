import java.util.*;
import java.io.*;

public class milkvisits {
    public static void main(String[] args) {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("meetings.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("meetings.out"));

        Map<Integer, Character> cows = new HashMap<Integer, Character>();
        List<Edge> tree = new ArrayList<Edge>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        String cowsStr = in.next();
        for (int i = 1; i <= cowsStr.length(); i++)
            cows.put(i, cowsStr.charAt(i - 1));
        for (int i = 0; i < n - 1; i++)
            tree.add(new Edge(in.nextInt(), in.nextInt()));
        Collections.sort(tree);

        System.out.println(cows);
        System.out.println(tree);

        for (int i = 0; i < m; i++) {
            int start = in.nextInt(), end = in.nextInt();
            char cow = in.next().charAt(0);
            in.nextLine();
            System.out.println(start + ", " + end + ", " + cow);

            boolean allGood = false;

            if (start == end) {
                allGood = cows.get(start) == cow;
                System.out.println(allGood);
                continue;
            }

            int index = indexOfEnd(tree, end);
            while (tree.get(index).a != start) {
                if (cows.get(tree.get(index).a) == cow || cows.get(tree.get(index).b) == cow) {
                    allGood = allGood || true;
                    break;
                }

                index = indexOfEnd(tree, tree.get(index).a);
                if (index == -1)
                    break;
                System.out.println("current edge: " + tree.get(index));
            }
            System.out.println(allGood);
        }
    }

    // in.close();
    // out.close();

    private static int indexOfEnd(List<Edge> edges, int end) {
        for (int i = 0; i < edges.size(); i++)
            if (edges.get(i).b == end)
                return i;
        return -1;
    }

    private static class Edge implements Comparable<Edge> {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public String toString() {
            return String.format("from %d to %d", a, b);
        }

        @Override
        public int compareTo(Edge e) {
            return a < e.a ? -1 : (a > e.a ? 1 : 0);
        }
    }
}