import java.util.*;
import java.io.*;

public class Validator {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(
                new FileReader("/Users/mahesh/Documents/Contests/bpa_java_programming/2015_regionals/input340.txt")));
        PrintWriter out = new PrintWriter(new FileWriter("bpa_java_programming/2015_regionals/output340.txt"));

        while (in.hasNextLine()) {
            String originalCode = in.nextLine();
            String code = originalCode.replaceAll("-", "");
            int sum = 0;
            for (int i = 0; i < code.length(); i++) {
                int c = code.charAt(i);
                if (c == 'X')
                    sum += 10 * (code.length() - i);
                else
                    sum += Character.getNumericValue(c) * (code.length() - i);
            }
            if (sum % 11 != 0)
                out.println(originalCode);
        }

        in.close();
        out.close();
    }
}