import java.util.*;
import java.io.*;

public class whereami {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("whereami.in")));
        PrintWriter out = new PrintWriter(new FileWriter("whereami.out"));

        in.nextLine();
        String input = in.nextLine();

        List<String> sequences = new ArrayList<String>();
        for (int i = 1; i < input.length(); i++) {
            boolean ok = true;
            for (int j = 0; j < input.length() - i + 1; j++) {
                String str = input.substring(j, j + i);
                if (sequences.contains(str)) {
                    ok = false;
                    break;
                }
                sequences.add(str);
            }
            if (ok) {
                out.println(i);
                out.close();
                in.close();
                break;
            }
        }
    }
}