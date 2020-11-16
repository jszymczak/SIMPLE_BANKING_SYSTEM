import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberN = scanner.nextInt();
        int numberA = scanner.nextInt();
        int numberB = scanner.nextInt();
        int sumOfNumbers = 0;

        Random random = new Random(numberA + numberB);
        for (int i = 0; i < numberN; i++) {
            sumOfNumbers += random.nextInt(numberB - numberA + 1) + numberA;
        }
        System.out.println(sumOfNumbers);
    }
}