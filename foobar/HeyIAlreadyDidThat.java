import java.util.*;
import java.util.stream.*;

public class HeyIAlreadyDidThat {
    public static int solution(String n, int b) {
        String slow = new String(n), fast = new String(n);
        int start = -1, end = -1, i = 0;
        do {
            slow = step(slow, b, n.length());
            fast = step(step(fast, b, n.length()), b, n.length());
            if (slow.equals(fast) && start == -1)
                start = i;
            else if (slow.equals(fast)) {
                end = i;
                break;
            }
            i++;
        } while (true);
        return end - start;
    }

    private static String step(String n, int b, int k) {
        int[] nums = n.chars().map(x -> Character.getNumericValue(x)).toArray();
        long x = Long.parseLong(Arrays.stream(nums).sorted().mapToObj(String::valueOf).collect(Collectors.joining()),
                b);
        long y = Long.parseLong(Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).map(String::valueOf)
                .collect(Collectors.joining()), b);
        return String.format("%1$" + k + "s", Long.toString(y - x, b)).replace(' ', '0');
    }
}