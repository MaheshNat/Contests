import java.util.*;
import java.util.stream.*;
import java.io.*;

public class IntcodeComputer {
    private static Map<Integer, OpCode> opCodeMap;
    private List<Integer> output;
    private Queue<Integer> input;
    private int[] code;
    private int pointer;
    private boolean running;

    public IntcodeComputer(int[] code) {
        this.code = code;
        output = new ArrayList<Integer>();
        pointer = 0;
        running = true;

        opCodeMap = new HashMap<Integer, OpCode>() {
            {
                put(1, () -> {
                    setPos(3, getPos(1) + getPos(2));
                    return 3;
                });
                put(2, () -> {
                    setPos(3, getPos(1) * getPos(2));
                    return 3;
                });
                put(3, () -> {
                    setPos(1, input.poll());
                    return 1;
                });
                put(4, () -> {
                    output.add(getPos(1));
                    return 1;
                });
                put(5, () -> {
                    pointer = getPos(1) != 0 ? getPos(2) : pointer;
                    return 2;
                });
                put(6, () -> {
                    pointer = getPos(1) == 0 ? getPos(2) : pointer;
                    return 2;
                });
                put(7, () -> {
                    setPos(3, getPos(1) < getPos(2) ? 1 : 0);
                    return 2;
                });
                put(8, () -> {
                    setPos(3, getPos(1) == getPos(2) ? 1 : 0);
                    return 2;
                });
                put(99, () -> {
                    running = false;
                    return 0;
                });
            }
        };
    }

    private void setPos(int pos, int val) {
        code[pos] = code[pointer + val];
    }

    private int getPos(int pos) {
        return code[pointer + pos];
    }

    public void run(List<Integer> input) {
        while (pointer < code.length && running) {
            int skip = opCodeMap.get(code[pointer] % 10).run();
            pointer += skip;
        }
    }

    private interface OpCode {
        int run();
    }
}
