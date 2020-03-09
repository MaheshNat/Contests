import java.util.*;
import java.io.*;

public class triangles {

    private static Point[] points;
    private static List<Point> vertices;
    private static List<Point[]> triangles;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("triangles.in")));
        PrintWriter out = new PrintWriter(new FileWriter("triangles.out"));
        // Scanner in = new Scanner(System.in);

        points = new Point[in.nextInt()];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.nextInt(), in.nextInt());
        }

        triangles = new ArrayList<Point[]>();

        Point[] data = new Point[3];
        combo(points, data, 0, points.length - 1, 0);

        double areaSum = 0;
        for (Point[] points : triangles) {
            if (isLegal(points[0], points[1], points[2]))
                areaSum += getArea(points[0], points[1], points[2]);
        }

        // System.out.println(triangles);
        // System.out.println(vertices);
        // System.out.println(areaSum);
        out.println((int) ((2 * areaSum) % (Math.pow(10, 9) + 7)));

        in.close();
        out.close();
    }

    static void combo(Point points[], Point temp[], int s, int e, int i) {
        if (i == 3) {
            triangles.add(temp.clone());
            return;
        }

        for (int n = s; n <= e && e - n + 1 >= 3 - i; n++) {
            temp[i] = points[n];
            combo(points, temp, n + 1, e, i + 1);
        }
    }

    private static boolean isLegal(Point a, Point b, Point c) {
        return (a.x == b.x && a.y == c.y || a.y == b.y && a.x == c.x)
                || (b.x == a.x && b.y == c.y || b.y == a.y && b.x == c.x)
                || (c.x == a.x && c.y == b.y || c.y == a.y && c.x == b.x);
    }

    private static double getArea(Point p1, Point p2, Point p3) {
        return Math.abs(0.5 * (p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)));
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        public boolean equals(Object o) {
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }
    }
}