import java.util.*;
import java.io.*;

public class Cipher {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(
                new FileReader("/Users/mahesh/Documents/Contests/bpa_java_programming/2014_regionals/ciphers.txt")));
        PrintWriter out = new PrintWriter(new FileWriter("bpa_java_programming/2014_regionals/plain.txt"));

        while (in.hasNextLine()) {
            String cipherText = in.nextLine();
            String key = in.nextLine();
            String output = "";
            for (int i = 0; i < cipherText.length(); i++) {
                char c = (char) (cipherText.charAt(i) - key.charAt(i % key.length()) + 64);
                output += c - 64 < 0 ? (char) (c + 26) : c;
            }
            out.println(output);
        }

        in.close();
        out.close();
    }
}