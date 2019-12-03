import Constants;

import java.io.*;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day1")));
        int part1Sum = 0;
        int part2Sum = 0;
        while (in.hasNextInt()) {
            int nextInt = in.nextInt();
            part1Sum += nextInt / 3 - 2;
            part2Sum += fuel(nextInt);
        }
        System.out.println("part 1: " + part1Sum);
        System.out.println("part 2: " + part2Sum);
    }

    public static int fuel(int num) {
        int fuel = num / 3 - 2;
        if (fuel <= 0)
            return 0;
        return fuel + fuel(fuel);
    }
}