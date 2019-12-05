import Constants;
import java.util.*;
import java.io.*;

public class Day4 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day4")));
        String[] range = in.nextLine().split("-");

        int part1ValidPasswords = 0, part2ValidPasswords = 0;
        for (int i = Integer.parseInt(range[0]); i <= Integer.parseInt(range[1]); i++) {
            char[] password = String.valueOf(i).toCharArray();

            if (!increasing(password))
                continue;
            if (part1Adjacent(password))
                part1ValidPasswords++;
            if (part2Adjacent(password))
                part2ValidPasswords++;
        }
        System.out.println("part1: " + part1ValidPasswords);
        System.out.println("part2: " + part2ValidPasswords);
    }

    private static boolean increasing(char[] password) {
        for (int j = 0; j < password.length - 1; j++)
            if (Character.getNumericValue(password[j + 1]) < Character.getNumericValue(password[j]))
                return false;
        return true;
    }

    private static boolean part1Adjacent(char[] password) {
        int lastDigit = -1;
        for (int i = 0; i < password.length; i++) {
            if (lastDigit == Character.getNumericValue(password[i]))
                return true;
            lastDigit = Character.getNumericValue(password[i]);
        }
        return false;
    }

    private static boolean part2Adjacent(char[] password) {
        int digitCounter = 0;
        boolean adjacent = false;
        int lastDigit = -1;
        for (int j = 0; j < password.length; j++) {
            if (lastDigit == Character.getNumericValue(password[j]))
                digitCounter++;
            else {
                if (digitCounter == 2)
                    return true;
                digitCounter = 1;
                lastDigit = Character.getNumericValue(password[j]);
            }
        }
        return digitCounter == 2;
    }
}