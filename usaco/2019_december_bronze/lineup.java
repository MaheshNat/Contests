import java.util.*;
import java.io.*;
import java.util.stream.*;

public class lineup {
    private static List<String> cows = new LinkedList<String>(
            Arrays.asList("Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"));

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("lineup.in")));
        PrintWriter out = new PrintWriter(new FileWriter("lineup.out"));

        Collections.sort(cows);
        List<List<String>> permutations = generatePerm(cows);

        Map<String, List<String>> constraints = new HashMap<String, List<String>>();
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().split(" ");
            if (constraints.containsKey(parts[0]))
                constraints.get(parts[0]).add(parts[parts.length - 1]);
            else {
                List<String> values = new ArrayList<String>();
                values.add(parts[parts.length - 1]);
                constraints.put(parts[0], values);
            }
        }

        List<List<String>> solutions = new ArrayList<List<String>>();
        for (List<String> permutation : permutations) {
            boolean satisfies = true;
            for (int i = 0; i < permutation.size(); i++) {
                if (constraints.containsKey(permutation.get(i)))
                    for (String constraintValue : constraints.get(permutation.get(i)))
                        if (Math.abs(
                                permutation.indexOf(constraintValue) - permutation.indexOf(permutation.get(i))) != 1) {
                            satisfies = false;
                            break;
                        }
            }
            if (satisfies)
                solutions.add(permutation);
        }

        solutions = solutions.stream().sorted((o1, o2) -> {
            for (int i = 0; i < o1.size(); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());

        for (String str : solutions.get(0))
            out.println(str);

        in.close();
        out.close();
    }

    public static List<List<String>> generatePerm(List<String> original) {
        if (original.isEmpty()) {
            List<List<String>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        String firstElement = original.remove(0);
        List<List<String>> returnValue = new ArrayList<>();
        List<List<String>> permutations = generatePerm(original);
        for (List<String> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<String> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
}