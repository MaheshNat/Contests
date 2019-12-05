import Constants;

import java.util.*;
import java.io.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day3")));
        String[] wire1Str = in.nextLine().split(",");
        String[] wire2Str = in.nextLine().split(",");
        Set<Pair> wire1 = getPath(wire1Str);
        Set<Pair> wire2 = getPath(wire2Str);
        Map<Pair, Integer> wire1Times = getTimes(wire1Str);
        Map<Pair, Integer> wire2Times = getTimes(wire2Str);

        wire1.retainAll(wire2);

        int minDistance = Integer.MAX_VALUE;
        for (Pair pair : wire1) {
            if (pair.row == 0 && pair.col == 0)
                continue;
            minDistance = Math.min(minDistance, Math.abs(pair.row) + Math.abs(pair.col));
        }
        System.out.println("part1: " + minDistance);

        int minTime = Integer.MAX_VALUE;
        for (Pair pair : wire1) {
            if (pair.row == 0 && pair.col == 0)
                continue;
            minTime = Math.min(minTime, wire1Times.get(pair) + wire2Times.get(pair));
        }
        System.out.println("part2: " + minTime);
        in.close();
    }

    private static Map<Pair, Integer> getTimes(String[] wireStr) {
        Map<Pair, Integer> times = new HashMap<Pair, Integer>();
        Pair pos = new Pair(0, 0);
        int time = 0;

        for (String wirePiece : wireStr) {
            char direction = wirePiece.charAt(0);
            int length = Integer.parseInt(wirePiece.substring(1));

            for (int i = 0; i < length; i++) {
                time++;
                switch (direction) {
                case 'R':
                    pos.row++;
                    break;
                case 'L':
                    pos.row--;
                    break;
                case 'U':
                    pos.col++;
                    break;
                case 'D':
                    pos.col--;
                    break;
                }
                times.put(pos.clone(), time);
            }
        }
        return times;
    }

    private static Set<Pair> getPath(String[] wireStr) {
        Set<Pair> path = new HashSet<Pair>();
        Pair pos = new Pair(0, 0);

        for (String wirePiece : wireStr) {
            char direction = wirePiece.charAt(0);
            int length = Integer.parseInt(wirePiece.substring(1));

            for (int i = 0; i < length; i++) {
                switch (direction) {
                case 'R':
                    pos.row++;
                    break;
                case 'L':
                    pos.row--;
                    break;
                case 'U':
                    pos.col++;
                    break;
                case 'D':
                    pos.col--;
                    break;
                }
                path.add(pos.clone());
            }
        }
        return path;
    }

    private static class Pair implements Cloneable {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Pair clone() {
            return new Pair(row, col);
        }

        public String toString() {
            return String.format("row: %d, col: %d", row, col);
        }

        @Override
        public boolean equals(Object obj) {
            Pair p = (Pair) obj;
            return row == p.row && col == p.col;
        }

        @Override
        public int hashCode() {
            return row + col;
        }
    }
}