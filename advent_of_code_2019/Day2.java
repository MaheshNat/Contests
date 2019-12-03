import java.util.*;
import java.io.*;

import Constants;

public class Day2 {

    private static List<Integer> program = new ArrayList<Integer>();
    private static boolean ended;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day2")));

        List<Integer> clearedProgram = new ArrayList<Integer>();
        String[] programStr = in.next().split(",");
        for (String num : programStr)
            clearedProgram.add(Integer.parseInt(num));

        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                ended = false;
                program.clear();
                program.addAll(clearedProgram);

                program.set(1, noun);
                program.set(2, verb);

                for (int i = 0; i < program.size() && !ended; i += 4)
                    runProgram(i);

                if (noun == 12 && verb == 2)
                    System.out.println("part 1: " + program.get(0));
                else if (program.get(0) == 19690720)
                    System.out.println("part 2: " + noun + "" + verb);
            }
        }

        in.close();
    }

    private static void runProgram(int start) {
        if (program.get(start) == 1) {
            program.set(program.get(start + 3),
                    program.get(program.get(start + 1)) + program.get(program.get(start + 2)));
        } else if (program.get(start) == 2) {
            program.set(program.get(start + 3),
                    program.get(program.get(start + 1)) * program.get(program.get(start + 2)));
        } else if (program.get(start) == 99) {
            ended = true;
        }
    }
}