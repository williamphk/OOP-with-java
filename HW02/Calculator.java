import java.util.Scanner;
import java.text.DecimalFormat;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");
        Scanner input = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat("0.00");
        String operation = input.next().toLowerCase();
        switch (operation) {
            case "add":
                System.out.println("Enter two integers:");
                if (input.hasNextInt()) {
                    int integerA = input.nextInt();
                    if (input.hasNextInt()) {
                        int integerB = input.nextInt();
                        int answerA = integerA + integerB;
                        System.out.println("Answer: " + answerA);
                    } else {
                        System.out.println("Invalid input entered. Terminating...");
                    }
                } else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "subtract":
                System.out.println("Enter two integers:");
                if (input.hasNextInt()) {
                    if (input.hasNextInt()) {
                        int integerC = input.nextInt();
                        int integerD = input.nextInt();
                        int answerB = integerC - integerD;
                        System.out.println("Answer: " + answerB);
                    } else {
                        System.out.println("Invalid input entered. Terminating...");
                    }
                } else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            // System.out.println("Invalid input entered. Terminating...");
            case "multiply":
                System.out.println("Enter two doubles:");
                if (input.hasNextDouble()) {
                    double doubleA = input.nextDouble();
                    if (input.hasNextDouble()) {
                        double doubleB = input.nextDouble();
                        double answerC = doubleA * doubleB;
                        String numStr = formatter.format(answerC);
                        System.out.println("Answer: " + numStr);
                    } else {
                        System.out.println("Invalid input entered. Terminating...");
                    }
                } else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "divide":
                System.out.println("Enter two doubles:");
                if (input.hasNextDouble()) {
                    double doubleC = input.nextDouble();
                    if (input.hasNextDouble()) {
                        double doubleD = input.nextDouble();
                        if (doubleD == 0) {
                            System.out.println("Invalid input entered. Terminating...");
                        } else {
                            double answerD = doubleC / doubleD;
                            String numStr = formatter.format(answerD);
                            System.out.println("Answer: " + numStr);
                        }
                    } else {
                        System.out.println("Invalid input entered. Terminating...");
                    }
                } else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "alphabetize":
                System.out.println("Enter two words:");
                String stringA = input.next();
                String stringB = input.next();
                String answerF;
                if (stringA.toLowerCase().compareTo(stringB.toLowerCase()) > 0) {
                    answerF = String.format("%s comes before %s alphabetically.", stringB, stringA);
                } else if (stringA.toLowerCase().compareTo(stringB.toLowerCase()) < 0) {
                    answerF = String.format("%s comes before %s alphabetically.", stringA, stringB);
                } else {
                    answerF = "Chicken or Egg.";
                }
                System.out.println("Answer: " + answerF);
                break;
            default:
                System.out.println("Invalid input entered. Terminating...");
                break;
        }
    }
}
