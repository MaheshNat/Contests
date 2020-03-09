import java.util.*;
import java.io.*;

public class moobuzz {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter out = new PrintWriter(new FileWriter("moobuzz.out"));

        int num = Integer.parseInt(in.readLine());
        int targetIndex = 0, target = 0, i = 1;
        while (targetIndex != num) {
            if (i % 3 != 0 && i % 5 != 0) {
                targetIndex = targetIndex + 1;
                target = i;
            }
            i = i + 1;
        }

        out.println(target);

        in.close();
        out.close();
    }
}