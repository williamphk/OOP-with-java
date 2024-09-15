import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {
        char[][] playerOneLocationBoard = {
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' }
        };
        char[][] playerTwoLocationBoard = {
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' }
        };
        char[][] playerOneTargetHistoryBoard = {
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' }
        };
        char[][] playerTwoTargetHistoryBoard = {
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' },
                { '-', '-', '-', '-', '-' }
        };

        System.out.println("Welcome to Battleship\n");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");

        for (int i = 0; i < 5; i++) {
            int[] userInputPosition = inputShipPosition(i + 1);
            int x = userInputPosition[0];
            int y = userInputPosition[1];
            if (x > -1 && x < 5 && y > -1 && y < 5) {
                if (isShipPlaced(playerTwoLocationBoard, userInputPosition) == false) {
                    playerOneLocationBoard[x][y] = '@';
                } else {
                    i--;
                    System.out.println("You already have a ship there. Choose different coordinates.");
                }
            } else {
                i--;
                System.out.println("Invalid coordinates. Choose different coordinates.");
            }
        }

        printBattleShip(playerOneLocationBoard);

        for (int i = 0; i < 100; i++) {
            System.out.println("\n");
        }

        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");

        for (int i = 0; i < 5; i++) {
            int[] userInputPosition = inputShipPosition(i + 1);
            int x = userInputPosition[0];
            int y = userInputPosition[1];
            if (x > -1 && x < 5 && y > -1 && y < 5) {
                if (isShipPlaced(playerTwoLocationBoard, userInputPosition) == false) {
                    playerTwoLocationBoard[x][y] = '@';
                } else {
                    i--;
                    System.out.println("You already have a ship there. Choose different coordinates.");
                }
            } else {
                i--;
                System.out.println("Invalid coordinates. Choose different coordinates.");
            }
        }

        printBattleShip(playerTwoLocationBoard);

        while (true) {
            Scanner input = new Scanner(System.in);

            if (!isWin(playerOneLocationBoard)) {
                while (true) {
                    int[] userInputPosition = inputHitPosition(1, input);
                    int x = userInputPosition[0];
                    int y = userInputPosition[1];
                    if (x > -1 && x < 5 && y > -1 && y < 5) {
                        if (!isHitAlready(playerTwoLocationBoard, userInputPosition)) {
                            if (isHit(playerTwoLocationBoard, userInputPosition)) {
                                playerTwoLocationBoard[x][y] = 'X';
                                playerOneTargetHistoryBoard[x][y] = 'X';
                                System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                                printBattleShip(playerOneTargetHistoryBoard);
                            } else {
                                playerTwoLocationBoard[x][y] = 'O';
                                playerOneTargetHistoryBoard[x][y] = 'O';
                                System.out.println("PLAYER 1 MISSED!");
                                printBattleShip(playerOneTargetHistoryBoard);
                            }
                            break;
                        } else {
                            System.out.println("You already fired on this spot. Choose different coordinates.");
                            input.nextLine();
                        }
                    } else {
                        System.out.println("Invalid coordinates. Choose different coordinates.");
                        input.nextLine();
                    }
                }
            } else {
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                break;
            }

            if (!isWin(playerTwoLocationBoard)) {
                while (true) {
                    int[] playerTwoInputPosition = inputHitPosition(2, input);
                    if (!isHitAlready(playerOneLocationBoard, playerTwoInputPosition)) {
                        int playerTwoX = playerTwoInputPosition[0];
                        int playerTwoY = playerTwoInputPosition[1];
                        if (isHit(playerOneLocationBoard, playerTwoInputPosition)) {
                            playerOneLocationBoard[playerTwoX][playerTwoY] = 'X';
                            playerTwoTargetHistoryBoard[playerTwoX][playerTwoY] = 'X';
                            System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                            printBattleShip(playerTwoTargetHistoryBoard);
                        } else {
                            playerOneLocationBoard[playerTwoX][playerTwoY] = 'O';
                            playerTwoTargetHistoryBoard[playerTwoX][playerTwoY] = 'O';
                            System.out.println("PLAYER 2 MISSED!");
                            printBattleShip(playerTwoTargetHistoryBoard);
                        }
                        break;
                    } else {
                        System.out.println("You already fired on this spot. Choose different coordinates.");
                        input.nextLine();
                    }
                }
            } else {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                break;
            }
        }

        System.out.println("Final boards:");
        printBattleShip(playerOneLocationBoard);
        printBattleShip(playerTwoLocationBoard);
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

    private static int[] inputHitPosition(int playerId, Scanner input) {
        String message = String.format("Player %s, enter hit row/column:", playerId);
        System.out.println(message);

        int[] hitPosition = new int[2];
        while (true) {
            if (input.hasNextInt()) {
                int x = input.nextInt();
                if (input.hasNextInt()) {
                    int y = input.nextInt();
                    hitPosition[0] = x;
                    hitPosition[1] = y;
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

        return hitPosition;
    }

    private static boolean isShipPlaced(char[][] locationMap, int[] target) {
        int x = target[0];
        int y = target[1];
        if (locationMap[x][y] == '@') {
            return true;
        }
        return false;
    }

    private static boolean isHitAlready(char[][] locationMap, int[] target) {
        int x = target[0];
        int y = target[1];
        if (locationMap[x][y] == 'X' || locationMap[x][y] == 'O') {
            return true;
        }
        return false;
    }

    private static boolean isHit(char[][] locationMap, int[] target) {
        int x = target[0];
        int y = target[1];
        if (locationMap[x][y] == '@') {
            return true;
        }
        return false;
    }

    private static boolean isWin(char[][] locationMap) {
        for (int i = 0; i < locationMap.length; i++) {
            for (int j = 0; j < locationMap[i].length; j++) {
                if (locationMap[i][j] == '@') {
                    return false;
                }
            }
        }
        return true;
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
