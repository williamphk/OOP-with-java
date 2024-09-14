import java.util.Arrays;
import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {
        int[][] player1ShipPosition = new int[5][];
        int[][] player2ShipPosition = new int[5][];

        System.out.println("Welcome to Battleship");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");

        for (int i = 0; i < 5; i++) {
            player1ShipPosition[i] = inputShipPosition(i + 1);
        }

        System.out.println(Arrays.deepToString(player1ShipPosition));

        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");

        for (int i = 0; i < 5; i++) {
            player2ShipPosition[i] = inputShipPosition(i + 1);
        }

        System.out.println(Arrays.deepToString(player2ShipPosition));
    }

    private static int[] inputShipPosition(int count) {
        String message = String.format("Enter ship %s location:", count);
        System.out.println(message);

        Scanner input = new Scanner(System.in);
        int[] shipPosition = new int[2];

        if (input.hasNextInt()) {
            int x = input.nextInt();
            if (x > 0 && x < 6) {
                shipPosition[0] = x;
            } else {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                inputShipPosition(count);
            }
            if (input.hasNextInt()) {
                int y = input.nextInt();
                if (y > 0 && y < 6) {
                    shipPosition[1] = y;
                } else {
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    inputShipPosition(count);
                }
            } else {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                inputShipPosition(count);
            }
        } else {
            System.out.println("Invalid coordinates. Choose different coordinates.");
            inputShipPosition(count);
        }

        return shipPosition;
    }

    // Use this method to print game boards to the console.
    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }
}
