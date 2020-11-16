import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberK = scanner.nextInt();
        int numberN = scanner.nextInt();
        double numberM = scanner.nextDouble();
        double temp;
        boolean isBroken = false;

        do {
            for (int i = 0; i < numberN; i++) {
                Random random = new Random(numberK);
                temp = random.nextGaussian();
                if (temp > numberM) {
                    isBroken = true;
                    break;
                } else {
                    isBroken = false;
                }
            }
            numberK++;
        } while (isBroken);
        System.out.println(numberK);
    }
}