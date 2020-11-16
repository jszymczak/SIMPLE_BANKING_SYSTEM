import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberA = scanner.nextInt();
        int numberB = scanner.nextInt();
        int numberN = scanner.nextInt();
        int numberK = scanner.nextInt();
        int numberOfSeeds = numberB - numberA;
        int seedPerRandom = numberA;
        int[][] allNumbers = new int[numberOfSeeds + 1][numberN];
        int[] maxNumbers = new int[numberOfSeeds + 1];
        for (int i = 0; i <= numberOfSeeds; i++) {
            Random random = new Random(seedPerRandom);
            for (int j = 0; j < numberN; j++) {
                allNumbers[i][j] = random.nextInt(numberK);
            }
            seedPerRandom++;
        }

        for (int i = 0; i <= numberOfSeeds; i++) {
            Arrays.sort(allNumbers[i]);
            maxNumbers[i] = allNumbers[i][numberN - 1];
        }

        int minFromMaxValues = maxNumbers[0];
        int seedNumber = numberA;

        for (int i = 1; i <= numberOfSeeds; i++) {
            if (minFromMaxValues > (Math.min(maxNumbers[i - 1], maxNumbers[i]))) {
                minFromMaxValues = maxNumbers[i];
                seedNumber = i + numberA;
            }
        }

        System.out.println(seedNumber + "\n" + minFromMaxValues);
    }
}