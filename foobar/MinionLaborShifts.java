import java.util.*;

public class MinionLaborShifts {
    public static int[] solution(int[] data, int n) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i : data)
            count.put(i, count.containsKey(i) ? count.get(i) + 1 : 1);
        System.out.println(count);
        return Arrays.stream(data).filter(x -> count.get(x) <= n).toArray();
    }
}