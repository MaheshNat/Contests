import java.util.*;
import java.io.*;

public class triangles2 {

    private static Point[] points;
    private static List<Point> vertices;
    private static List<Point[]> triangles;

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("triangles.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("triangles.out"));
        Scanner in = new Scanner(System.in);
        points = new Point[in.nextInt()];
        vertices = new ArrayList<Point>();
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.nextInt(), in.nextInt());
        }

        for (Point p : points) {
            int xPlanars = 0;
            int yPlanars = 0;
            for (Point o : points) {
                if (p.equals(o))
                    continue;
                if (p.x == o.x)
                    xPlanars++;
                else if (p.y == o.y)
                    yPlanars++;
            }
            if (xPlanars >= 1 && yPlanars >= 1)
                vertices.add(p);
        }

        triangles = new ArrayList<Point[]>();

        Point[] data = new Point[2];
        Point[] verticesArr = new Point[vertices.size()];
        for (int i = 0; i < vertices.size(); i++)
            verticesArr[i] = vertices.get(i);
        combo(verticesArr, data, 0, verticesArr.length - 1, 0);

        triangles.forEach(x -> {
            Arrays.stream(x).forEach(y -> System.out.print(y));
            System.out.println();
        });

        double areaSum = 0;
        Point p = points[0];
        for (Point o : vertices) {
            if (p.equals(o))
                continue;
            for (int i = 1; i < points.length; i++) {
                if (points[i].equals(o))
                    continue;
                double area = getArea(p, o, points[i]);
                areaSum += area;
            }
        }

        System.out.println(vertices);
        System.out.println((int) ((2 * areaSum) % (Math.pow(10, 9) + 7)));

        // in.close();
        // out.close();
    }

    static void combo(Point points[], Point temp[], int s, int e, int i) {
        if (i == 2) {
            triangles.add(temp.clone());
            return;
        }

        for (int n = s; n <= e && e - n + 1 >= 2 - i; n++) {
            temp[i] = points[n];
            combo(points, temp, n + 1, e, i + 1);
        }
    }

    private static double getArea(Point p1, Point p2, Point p3) {
        return 0.5 * (p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y));
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