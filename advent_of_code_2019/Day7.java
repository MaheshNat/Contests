import Constants;

import java.util.*;
import java.io.*;

public class Day7 {

    private static int[] program;
    private static IntcodeVM vm;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day7")));
        String[] parts = in.nextLine().split(",");
        program = new int[parts.length];
        for (int i = 0; i < program.length; i++)
            program[i] = Integer.parseInt(parts[i]);

        vm = new IntcodeVM(program);
        Integer[] programInts = new Integer[program.length];
        for (int i = 0; i < program.length; i++)
            programInts[i] = new Integer(i);

        System.out.println(getOutput(new int[] { 9, 8, 7, 6, 5 }));

        // int maxOutput = Integer.MIN_VALUE;
        // int[] maxCombo = new int[5];
        // for (int zero = 0; zero <= 4; zero++) {
        // for (int one = 0; one <= 4; one++) {
        // if (one == zero)
        // continue;
        // for (int two = 0; two <= 4; two++) {
        // if (two == one || two == zero)
        // continue;
        // for (int three = 0; three <= 4; three++) {
        // if (three == two || three == one || three == zero)
        // continue;
        // for (int four = 0; four <= 4; four++) {
        // if (four == three || four == two || four == one || four == zero)
        // continue;
        // // System.out.println(
        // // "all not equal: " + Arrays.toString(new int[] { zero, one, two, three,
        // four
        // // }));
        // int output = getOutput(new int[] { zero, one, two, three, four });
        // System.out.println("combo: " + Arrays.toString(new int[] { zero, one, two,
        // three, four })
        // + ", output: " + output);
        // if (output > maxOutput) {
        // maxOutput = output;
        // maxCombo = new int[] { zero, one, two, three, four };
        // }
        // }
        // }
        // }
        // }
        // }

        // System.out.println(maxOutput);
        // System.out.println(Arrays.toString(maxCombo));

    }

    private static int getOutput(int[] thrusterSignals) {
        int prevStart = 0;
        do {
            vm.reset();
            vm.run(thrusterSignals[0], prevStart, prevStart, prevStart, prevStart, prevStart, prevStart, prevStart,
                    prevStart, prevStart, prevStart);
            if (vm.output.size() == 0)
                return Integer.MIN_VALUE;
            int next = vm.output.get(0);
            if (vm.halted)
                break;
            vm.reset();
            vm.run(thrusterSignals[1], next, next, next, next, next, next, next, next, next, next);
            if (vm.output.size() == 0)
                return Integer.MIN_VALUE;
            int next1 = vm.output.get(0);
            if (vm.halted)
                break;
            vm.reset();
            vm.run(thrusterSignals[2], next1, next1, next1, next1, next1, next1, next1, next1, next1, next1);
            if (vm.output.size() == 0)
                return Integer.MIN_VALUE;
            int next2 = vm.output.get(0);
            if (vm.halted)
                break;
            vm.reset();
            vm.run(thrusterSignals[3], next2, next2, next2, next2, next2, next2, next2);
            if (vm.output.size() == 0)
                return Integer.MIN_VALUE;
            int next3 = vm.output.get(0);
            if (vm.halted)
                break;
            vm.reset();
            vm.run(thrusterSignals[4], next3, next3, next3, next3, next3, next3, next3, next3, next3, next3, next3,
                    next3, next3, next3, next3);
            prevStart = vm.output.get(0);
        } while (!vm.halted);

        return vm.output.get(0);
    }

    private static int[] intToArr(int num) {
        int[] arr = new int[String.valueOf(num).length()];
        int counter = arr.length - 1;
        do {
            arr[counter] = num % 10;
            num /= 10;
            counter--;
        } while (num > 0);
        return arr;
    }
}