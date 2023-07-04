import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static String[][] gameTable = {
        {"A", "0", "0"},
        {"B", "0", "0"},
        {"C", "0", "0"}
    }; // The gameTable array represents the game board with initial values.

    public static boolean firstCondition = false;
    public static boolean secondCondition = false; // These boolean variables are used to determine if a winner is decided.

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.startGame();
    }

    private void startGame() {
        Random random = new Random();
        char currentPlayer = (random.nextInt(2) == 0) ? 'O' : 'X'; // Randomize who moves first
        char opponentPlayer = (currentPlayer == 'O') ? 'X' : 'O';

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            printBoard();

            boolean isComputerOpponent = chooseGameMode(scanner);
            boolean gameOver = false;

            while (!gameOver) {
                if (currentPlayer == 'O') {
                    if (isComputerOpponent) {
                        makeRandomMove(currentPlayer);
                    } else {
                        makePlayerMove(currentPlayer, scanner);
                    }
                } else {
                    makePlayerMove(currentPlayer, scanner);
                }

                printBoard();

                if (checkWinCondition(currentPlayer)) {
                    System.out.println(currentPlayer + " wins the game!");
                    updateScore(currentPlayer);
                    gameOver = true;
                } else if (isBoardFull()) {
                    System.out.println("The game is a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'O') ? 'X' : 'O';
                }
            }

            printScore();

            System.out.print("Play again? (Y/N): ");
            String playAgainInput = scanner.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("Y");
        }

        scanner.close();
    }

    private void initializeBoard() {
        gameTable = new String[][] {
            {"A", "0", "0"},
            {"B", "0", "0"},
            {"C", "0", "0"}
        };
    }

    private void printBoard() {
        for (int i = 0; i < gameTable.length; i++) {
            for (int j = 0; j < gameTable[i].length; j++) {
                System.out.print(gameTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean chooseGameMode(Scanner scanner) {
        System.out.print("Select game mode: (1) Computer vs. Computer, (2) Human vs. Computer: ");
        int mode = scanner.nextInt();
        scanner.nextLine();
        return mode == 2;
    }

    private void makeRandomMove(char currentPlayer) {
        Random random = new Random();
        int row = random.nextInt(3);
        int col = random.nextInt(3);

        while (!isValidMove(row, col)) {
            row = random.nextInt(3);
            col = random.nextInt(3);
        }

        gameTable[row][col] = String.valueOf(currentPlayer);
    }

    private void makePlayerMove(char currentPlayer, Scanner scanner) {
        System.out.print("Enter move (e.g., A1, B3): ");
        String move = scanner.nextLine();
        int row = convertRow(move.charAt(0));
        int col = convertCol(move.charAt(1));

        while (!isValidMove(row, col)) {
            System.out.println("Invalid move! Try again.");
            System.out.print("Enter move (e.g., A1, B3): ");
            move = scanner.nextLine();
            row = convertRow(move.charAt(0));
            col = convertCol(move.charAt(1));
        }

        gameTable[row][col] = String.valueOf(currentPlayer);
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && gameTable[row][col].equals("0");
    }

    private int convertRow(char rowChar) {
        return Character.toUpperCase(rowChar) - 'A';
    }

    private int convertCol(char colChar) {
        return Character.getNumericValue(colChar) - 1;
    }

    private boolean checkWinCondition(char currentPlayer) {
        return checkRows(currentPlayer) || checkColumns(currentPlayer) || checkDiagonals(currentPlayer);
    }

    private boolean checkRows(char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0].equals(String.valueOf(currentPlayer))
                && gameTable[i][1].equals(String.valueOf(currentPlayer))
                && gameTable[i][2].equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i].equals(String.valueOf(currentPlayer))
                && gameTable[1][i].equals(String.valueOf(currentPlayer))
                && gameTable[2][i].equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char currentPlayer) {
        if (gameTable[0][0].equals(String.valueOf(currentPlayer))
            && gameTable[1][1].equals(String.valueOf(currentPlayer))
            && gameTable[2][2].equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (gameTable[0][2].equals(String.valueOf(currentPlayer))
            && gameTable[1][1].equals(String.valueOf(currentPlayer))
            && gameTable[2][0].equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameTable[i][j].equals("0")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateScore(char currentPlayer) {
        if (currentPlayer == 'O') {
            // Update score for player O
        } else {
            // Update score for player X
        }
    }

    private void printScore() {
        // Print the current scores
    }
}
