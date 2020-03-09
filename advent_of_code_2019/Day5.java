import Constants;

import java.util.*;
import java.io.*;

public class Day5 {

    private static int[] program;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day5")));
        String[] parts = in.nextLine().split(",");
        program = new int[parts.length];
        for (int i = 0; i < program.length; i++)
            program[i] = Integer.parseInt(parts[i]);

        System.out.println(program.length);

        int i = 0;
        boolean stopped = false;
        for (; i < program.length && !stopped;) {
            int[] opCodes = intToArr(program[i]);

            switch (opCodes[opCodes.length - 1]) {
            case 1:
                System.out.println("1. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]
                        + ", program[i + 2]: " + program[i + 2] + ", program[i + 3]: " + program[i + 3]);
                if (opCodes.length == 3)
                    program[program[i + 3]] = (opCodes[0] == 0 ? program[program[i + 1]] : program[i + 1])
                            + program[program[i + 2]];
                else
                    program[program[i + 3]] = opCodes.length == 1 ? program[program[i + 1]] + program[program[i + 2]]
                            : (opCodes[1] == 0 ? program[program[i + 1]] : program[i + 1])
                                    + (opCodes[0] == 0 ? program[program[i + 2]] : program[i + 2]);
                i += 4;
                break;
            case 2:
                System.out.println("2. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]
                        + ", program[i + 2]: " + program[i + 2] + ", program[i + 3]: " + program[i + 3]);
                if (opCodes.length == 3)
                    program[program[i + 3]] = (opCodes[0] == 0 ? program[program[i + 1]] : program[i + 1])
                            * program[program[i + 2]];
                else
                    program[program[i + 3]] = (opCodes.length == 1) ? program[program[i + 1]] * program[program[i + 2]]
                            : (opCodes[1] == 0 ? program[program[i + 1]] : program[i + 1])
                                    * (opCodes[0] == 0 ? program[program[i + 2]] : program[i + 2]);
                i += 4;
                break;
            case 3:
                System.out
                        .println("3. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]);
                program[program[i + 1]] = 0;
                i += 2;
                break;
            case 4:
                System.out
                        .println("4. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]);
                System.out.println(opCodes.length == 1 ? program[program[i + 1]]
                        : (opCodes[0] == 0 ? program[program[i + 1]] : program[i + 1]));
                i += 2;
                break;
            case 5:
                System.out.println("5. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]
                        + ", program[i + 2]: " + program[i + 2] + ", program[i + 3]: " + program[i + 3]);
                int prevI = i;
                if (opCodes.length == 3) {
                    if (opCodes[0] == 0)
                        i = program[program[i + 1]] != 0 ? program[program[i + 2]] : i;
                    else
                        i = program[i + 1] != 0 ? program[program[i + 2]] : i;
                } else {
                    if (opCodes.length == 1)
                        i = program[program[i + 1]] != 0 ? program[program[i + 2]] : i;
                    else {
                        if (opCodes[1] == 0)
                            i = program[program[i + 1]] != 0
                                    ? (opCodes[0] == 0 ? program[program[i + 2]] : program[i + 2])
                                    : i;
                        else
                            i = program[i + 1] != 0 ? (opCodes[0] == 0 ? program[program[i + 2]] : program[i + 2]) : i;
                    }
                }
                i += prevI == i ? 3 : 0;
                break;
            case 6:
                System.out.println("6. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]
                        + ", program[i + 2]: " + program[i + 2] + ", program[i + 3]: " + program[i + 3]);
                int prevI1 = i;
                if (opCodes.length == 3) {
                    if (opCodes[0] == 0)
                        i = program[program[i + 1]] == 0 ? program[program[i + 2]] : i;
                    else
                        i = program[i + 1] == 0 ? program[program[i + 2]] : i;
                } else {
                    if (opCodes.length == 1)
                        i = program[program[i + 1]] == 0 ? program[program[i + 2]] : i;
                    else {
                        if (opCodes[1] == 0)
                            i = program[program[i + 1]] == 0
                                    ? (opCodes[0] == 0 ? program[program[i + 2]] : program[i + 2])
                                    : i;
                        else
                            i = program[i + 1] == 0 ? (opCodes[0] == 0 ? program[program[i + 2]] : program[i + 2]) : i;
                    }
                }
                i += prevI1 == i ? 3 : 0;
                break;
            case 7:
                System.out.println("7. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]
                        + ", program[i + 2]: " + program[i + 2] + ", program[i + 3]: " + program[i + 3]);
                if (opCodes.length == 3) {
                    if (opCodes[0] == 0)
                        program[program[i + 3]] = program[program[i + 1]] < program[program[i + 2]] ? 1 : 0;
                    else
                        program[program[i + 3]] = program[i + 1] < program[program[i + 2]] ? 1 : 0;
                } else {
                    if (opCodes.length == 1)
                        program[program[i + 3]] = program[program[i + 1]] < program[program[i + 2]] ? 1 : 0;
                    else {
                        if (opCodes[1] == 0)
                            program[program[i
                                    + 3]] = program[program[i + 1]] < (opCodes[0] == 0 ? program[program[i + 2]]
                                            : program[i + 2]) ? 1 : 0;
                        else
                            program[program[i + 3]] = program[i + 1] < (opCodes[0] == 0 ? program[program[i + 2]]
                                    : program[i + 2]) ? 1 : 0;
                    }
                }
                i += 4;
                break;
            case 8:
                System.out.println("8. i: " + i + ", program[i]: " + program[i] + ", program[i + 1]: " + program[i + 1]
                        + ", program[i + 2]: " + program[i + 2] + ", program[i + 3]: " + program[i + 3]);
                if (opCodes.length == 3) {
                    if (opCodes[0] == 0)
                        program[program[i + 3]] = program[program[i + 1]] == program[program[i + 2]] ? 1 : 0;
                    else
                        program[program[i + 3]] = program[i + 1] == program[program[i + 2]] ? 1 : 0;
                } else {
                    if (opCodes.length == 1)
                        program[program[i + 3]] = program[program[i + 1]] == program[program[i + 2]] ? 1 : 0;
                    else {
                        if (opCodes[1] == 0)
                            program[program[i
                                    + 3]] = program[program[i + 1]] == (opCodes[0] == 0 ? program[program[i + 2]]
                                            : program[i + 2]) ? 1 : 0;
                        else
                            program[program[i + 3]] = program[i + 1] == (opCodes[0] == 0 ? program[program[i + 2]]
                                    : program[i + 2]) ? 1 : 0;
                    }
                }
                i += 4;
                break;
            case 9:
                stopped = true;
                break;
            default:
                System.out.println("unknown upcode: " + Arrays.toString(opCodes));
                stopped = true;
                break;
            }
            System.out.println(Arrays.toString(program));
        }

        in.close();

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