import java.util.*;

public class prob5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == b && a == 0)
                break;

            switch (b) {
                case 0:
                    System.out.println(a % 2 == 0 ? a + 2 : a + 1);
                    break;
                case 1:
                    for (int i = a + 1; i >= a; i++)
                        if (isPrime(i))
                            System.out.println(i);
                    break;
                case 2:
                    for (int i = a + 1; i >= a; i++)
                        if (isSquare(i))
                            System.out.println(i);
                    break;
                case 3:
                    for (int i = a + 1; i >= a; i++)
                        if (isCube(i))
                            System.out.println(i);
                    break;
            }
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; n++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static boolean isSquare(int n) {
        double sqrt = Math.sqrt(n);
        return sqrt == Math.floor(sqrt);
    }

    private static boolean isCube(int n) {
        double cubert = Math.pow(n, 1.0 / 3.0);
        return cubert == Math.floor(cubert);
    }
}