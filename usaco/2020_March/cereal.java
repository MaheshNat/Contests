import java.util.*;
import java.io.*;

public class cereal {
    static int n, m;
    static Map<Integer, List<Integer>> p, s;

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("socdist.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("socdist.out"));
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        p = new HashMap<>();
        s = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> newList = new ArrayList<Integer>();
            int p1 = in.nextInt(), s1 = in.nextInt();
            if (!p.containsKey(p1)) {
                newList.add(i);
                p.put(p1, newList);
            } else {
                newList.addAll(p.get(p1));
                newList.add(i);
                p.replace(p1, newList);
            }

            newList.clear();
            if (!s.containsKey(s1)) {
                newList.add(i);
                s.put(s1, newList);
            } else {
                newList.addAll(s.get(s1));
                newList.add(i);
                s.replace(s1, newList);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < i; j++)
                removeAll(j, p, s);
            Map<Integer, List<Integer>> pc = new HashMap<Integer, List<Integer>>();
            Map<Integer, List<Integer>> sc = new HashMap<Integer, List<Integer>>();
            pc.putAll(p);
            sc.putAll(s);
            int remove = i;
            while (!isEmpty(pc) && !isEmpty(sc)) {
                // removeAll(remove, pc, sc);
                System.out.println("remove: " + remove + ", p: " + pc + ", sc: " + sc);
                remove++;
            }
            System.out.println("i: " + i + ", remove: " + remove);
        }

        System.out.println("n: " + n + ", m: " + ", p: " + p.toString() + ", s: " + s.toString());

        // out.close();
        // in.close();
    }

    private static void removeAll(int n, Map<Integer, List<Integer>> pc, Map<Integer, List<Integer>> sc) {
        for (Map.Entry<Integer, List<Integer>> entry : pc.entrySet())
            pc.get(entry.getKey()).removeIf(new Integer(n)::equals);
        for (Map.Entry<Integer, List<Integer>> entry : sc.entrySet())
            sc.get(entry.getKey()).removeIf(new Integer(n)::equals);
    }

    private static void feedCow(int n, Map<Integer, List<Integer>> pc, Map<Integer, List<Integer>> sc) {
        for (Map.Entry<Integer, List<Integer>> entry : pc.entrySet())
            pc.get(entry.getKey()).removeIf(new Integer(n)::equals);
        for (Map.Entry<Integer, List<Integer>> entry : sc.entrySet())
            sc.get(entry.getKey()).removeIf(new Integer(n)::equals);
    }

    private static boolean isEmpty(Map<Integer, List<Integer>> map) {
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet())
            if (!map.get(entry.getKey()).isEmpty())
                return false;
        return true;
    }
}