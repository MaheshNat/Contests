import java.util.*;
import java.io.*;
import java.util.Comparator.*;

public class moop {
    static int n;
    private static List<Particle> particles;

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("moop.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("moop.out"));
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        particles = new ArrayList<Particle>();
        for (int i = 0; i < n; i++)
            particles.add(new Particle(in.nextInt(), in.nextInt()));

        List<Particle> particles2 = new ArrayList<Particle>(particles);

        Collections.sort(particles);
        Collections.sort(particles2, Comparator.comparing(a -> a.y).thenComparing(a -> a.x));
        // Arrays.sort(particles, Comparator.comparingDouble((int[] a) ->
        // a[0]).thenComparingDouble(a -> a[1]));

        System.out.println(particles);
        System.out.println(particles2);

        // out.close();
        // in.close();
    }

    private static int getMinLeft(int i, List<Particle> particles) {
        System.out.println("i: " + i + ", particles: " + particles);
        if (i == particles.size() - 1)
            return particles.size();
        if (particles.get(i).y > particles.get(i + 1).y)
            return getMinLeft(i + 1, particles);
        List<Particle> c1 = new ArrayList<Particle>(particles);
        c1.remove(i);
        List<Particle> c2 = new ArrayList<Particle>(particles);
        c2.remove(i + 1);
        return (int) Math.max(getMinLeft(i, c1), getMinLeft(i, c2));
    }

    private static class Particle implements Comparable {
        int x, y;

        Particle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object obj) {
            Particle p = (Particle) obj;
            if (x == p.x)
                return y - p.y;
            return x - p.x;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }
}