import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {
        int[][] playerOneShipMap = {
                { -1, -1 },
                { -1, -1 },
                { -1, -1 },
                { -1, -1 },
                { -1, -1 }
        };
        int[][] playerTwoShipMap = {
                { -1, -1 },
                { -1, -1 },
                { -1, -1 },
                { -1, -1 },
                { -1, -1 }
        };

        System.out.println("Welcome to Battleship");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");

        for (int i = 0; i < 5; i++) {
            int[] userInputPosition = inputShipPosition(i + 1);
            if (userInputPosition[0] > -1 && userInputPosition[0] < 5 && userInputPosition[1] > -1
                    && userInputPosition[1] < 5) {
                if (isShipPlaced(playerOneShipMap, userInputPosition) == false) {
                    playerOneShipMap[i] = userInputPosition;
                } else {
                    i--;
                    System.out.println("You already have a ship there. Choose different coordinates.");
                }
            } else {
                i--;
                System.out.println("Invalid coordinates. Choose different coordinates.");
            }
        }

        // System.out.println(Arrays.deepToString(playerOneShipMap));

        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");

        for (int i = 0; i < 5; i++) {
            int[] userInputPosition = inputShipPosition(i + 1);
            if (userInputPosition[0] > -1 && userInputPosition[0] < 5 && userInputPosition[1] > -1
                    && userInputPosition[1] < 5) {
                if (isShipPlaced(playerTwoShipMap, userInputPosition) == false) {
                    playerTwoShipMap[i] = userInputPosition;
                } else {
                    i--;
                    System.out.println("You already have a ship there. Choose different coordinates.");
                }
            } else {
                i--;
                System.out.println("Invalid coordinates. Choose different coordinates.");
            }
        }

        // System.out.println(Arrays.deepToString(playerTwoShipMap));
    }

    private static int[] inputShipPosition(int count) {
        String message = String.format("Enter ship %s location:", count);
        System.out.println(message);

        Scanner input = new Scanner(System.in);
        int[] shipPosition = new int[2];
        while (true) {
            if (input.hasNextInt()) {
                int x = input.nextInt();
                if (input.hasNextInt()) {
                    int y = input.nextInt();
                    shipPosition[0] = x;
                    shipPosition[1] = y;
                    break;
                } else {
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    System.out.println(message);
                    input.next();
                }
            } else {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                System.out.println(message);
                input.next();
            }
        }

        return shipPosition;
    }

    private static boolean isShipPlaced(int[][] shipMap, int[] position) {
        for (int i = 0; i < shipMap.length; i++) {
            if (shipMap[i][0] == position[0] && shipMap[i][1] == position[1]) {
                return true;
            }
        }
        return false;
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
