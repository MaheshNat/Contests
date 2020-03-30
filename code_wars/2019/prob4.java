import java.util.*;

public class prob4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int pairs = in.nextInt();
        for (int i = 0; i < pairs; i++) {
            double taxRate = in.nextDouble() * 0.01;
            double amount = in.nextDouble();
            System.out.println(String.format("On your $%.2f purchase, the tax amount was $%.2f", amount,
                    (amount / (1.0 + taxRate)) * taxRate));
        }
    }
}