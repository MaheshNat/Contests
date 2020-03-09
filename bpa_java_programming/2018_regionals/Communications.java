import java.util.*;
import java.io.*;

public class Communications {
    public static void main(String[] args) {
        // initializing input and output objects, and handling errors if thrown
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new BufferedReader(
                    new FileReader("/Users/mahesh/Documents/Contests/bpa_java_programming/2018_regionals/input.txt")));
            out = new PrintWriter(new FileWriter("bpa_java_programming/2018_regionals/output.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        int lines = in.nextInt();
        in.nextLine();

        // iterating through input lines
        for (int i = 0; i < lines; i++) {
            // reading input and declaring variables
            int messageNumber = in.nextInt();
            int checkTotal = in.nextInt();
            int letters = in.nextInt();
            String message = in.nextLine().substring(1);
            String properMessage = message.replaceAll(" over", "");
            List<String> errors = new ArrayList<String>();

            // performing length error check
            if (properMessage.length() != letters)
                errors.add("length error");

            // performing check total
            if (checkTotal(properMessage) != checkTotal)
                errors.add("check total error");

            // performing incomplete transmission check
            if (properMessage.length() == message.length())
                errors.add("incomplete transmission");

            // outputting 'confirmed' if no errors are found
            if (errors.size() == 0)
                out.println("transmission " + messageNumber + " confirmed");

            // displaying output in a correctly formatted way
            else {
                out.print("transmission " + messageNumber + " ");
                for (int j = 0; j < errors.size(); j++) {
                    if (j == errors.size() - 1)
                        out.print(errors.get(j));
                    else
                        out.print(errors.get(j) + ", ");

                }
                out.println();
            }

            // outputting encoded message
            out.println(properMessage.replaceAll("f", "B").replaceAll("F", "P").replaceAll("e", "A")
                    .replaceAll(" ", "e").replaceAll("t", ">?/") + " over");
            out.println();
        }

        in.close();
        out.close();
    }

    private static int checkTotal(String message) {
        int total = 0;
        for (char c : message.toCharArray())
            total += (int) c;
        return total;
    }
}