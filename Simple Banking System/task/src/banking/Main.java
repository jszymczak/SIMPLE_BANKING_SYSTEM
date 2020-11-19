package banking;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DatabaseProcessing.connect();
        DatabaseProcessing.createCardTable();

        Map<String, String> accountNumbers = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int input = 99;
        while (input != 666) {
            switch (input) {
                case 99:
                    System.out.println("1. Create an account");
                    System.out.println("2. Log into account");
                    System.out.println("0. Exit");
                    input = scanner.nextInt();
                    System.out.println();
                    break;
                case 98:
                    System.out.println("1. Balance");
                    System.out.println("2. Log out");
                    System.out.println("0. Exit");
                    input = scanner.nextInt() + 10;
                    input = input == 10 ? 0 : input;
                    break;
                case 1:
                    String generatedCardNumber = generateCardNumber();
                    String generatedPinNumber = generatePinNumber();
                    accountNumbers.put(generatedCardNumber, generatedPinNumber);
                    System.out.println("Your card has been created");
                    System.out.println("Your card number:\n" + generatedCardNumber);
                    System.out.println("Your card PIN: \n" + generatedPinNumber);
                    DatabaseProcessing.insertNewCardValues(generatedCardNumber, generatedPinNumber);
                    System.out.println();
                    input = 99;
                    break;
                case 2:
                    System.out.println("Enter your card number: ");
                    String logInCardNumber = scanner.next();
                    System.out.println("Enter your PIN: ");
                    String logInPinNumber = scanner.next();
                    if (accountNumbers.containsKey(logInCardNumber) &&
                            accountNumbers.get(logInCardNumber).equals(logInPinNumber)) {
                        resultOfLogIn("You have successfully logged in!");
                        input = 98;
                    } else {
                        resultOfLogIn("Wrong card number or PIN");
                        input = 99;
                    }
                    break;
                case 11:
                    System.out.println();
                    System.out.println("Balance: 0");
                    System.out.println();
                    input = 98;
                    break;
                case 12:
                    System.out.println();
                    System.out.println("You have successfully logged out!");
                    System.out.println();
                    input = 99;
                    break;
                case 0:
                    System.out.println("Bye!");
                    input = 666;
                    break;
                default:
                    input = 99;
            }
        }
}

    private static void resultOfLogIn(String s) {
        System.out.println();
        System.out.println(s);
        System.out.println();
    }

    private static String generatePinNumber() {
        Random random = new Random();
        int pin = random.nextInt(9000) + 1000;
        return String.valueOf(pin);
    }

    private static String generateCardNumber() {
        Random random = new Random();
        String cardNumber = "400000";
        long accountNumber = random.nextInt(900000000) + 100000000;
        StringBuilder longCardNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(cardNumber + accountNumber + i)) {
                longCardNumber.append(cardNumber);
                longCardNumber.append(accountNumber);
                longCardNumber.append(i);
                break;
            }
        }
        return longCardNumber.toString();
    }
}