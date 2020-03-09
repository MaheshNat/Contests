import Constants;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Day10 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day10")));
        List<Point> asteroids = new ArrayList<Point>();
        for (int i = 0; in.hasNextLine(); i++) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++)
                if (line.charAt(j) == '#')
                    asteroids.add(new Point(j, i));
        }

        int best = Integer.MIN_VALUE;
        Map<Double, List<Point>> bestAnglePoints = new HashMap<Double, List<Point>>();
        for (Point station : asteroids) {
            Map<Double, List<Point>> anglePoints = new HashMap<Double, List<Point>>();
            for (Point other : asteroids) {
                if (station.equals(other))
                    continue;
                double angle = Math.atan2((double) other.y - station.y, (double) other.x - station.x);
                if (anglePoints.containsKey(angle))
                    anglePoints.get(angle).add(other);
                else
                    anglePoints.put(angle, new ArrayList<Point>() {
                        {
                            add(other);
                        }
                    });
            }
            if (anglePoints.size() > best) {
                best = anglePoints.size();
                bestAnglePoints = anglePoints;
            }
        }

        System.out.println("best: " + best);

        List<Double> angles = bestAnglePoints.keySet().stream().collect(Collectors.toList());
        Collections.sort(angles);
        List<Map.Entry<Double, List<Point>>> listEntrySet = bestAnglePoints.entrySet().stream()
                .collect(Collectors.toList());

        List<Point> pointsDestroyed = new ArrayList<Point>();
        while (!listEntrySet.isEmpty()) {
            List<Double> toClear = new ArrayList<Double>();
            for (double angle : bestAnglePoints.keySet()) {
                Point toDestroy = bestAnglePoints.get(angle).get(0);
                bestAnglePoints.get(angle).remove(toDestroy);
                pointsDestroyed.add(toDestroy);
                if (bestAnglePoints.get(angle).size() == 0)
                    toClear.add(angle);
            }
            for (double angle : toClear)
                bestAnglePoints.remove(angle);
        }
        Point p2 = pointsDestroyed.get(199);
        System.out.println("Part 2: " + (100 * p2.x + p2.y));

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(x: %d, y: %d)", x, y);
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }
    }
}