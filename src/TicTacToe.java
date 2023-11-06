
import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {
            try {
                Scanner scan = new Scanner(System.in);

                System.out.println("Enter your placement (1-9)");
                int playerPos = getPlayerInput(scan);

                while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                    System.out.println("Position taken!");
                    playerPos = getPlayerInput(scan);
                }

                userInput(gameBoard, playerPos, "player");
                printGameBoard(gameBoard);

                String result = checkWinner();
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break;
                }

                int cpuPos = getCPUInput();
                while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                    cpuPos = getCPUInput();
                }

                userInput(gameBoard, cpuPos, "cpu");
                printGameBoard(gameBoard);

                result = checkWinner();
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static int getPlayerInput(Scanner scanner) {
        int playerPos = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return playerPos;
    }

    public static int getCPUInput() {
        return new Random().nextInt(9) + 1;
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void userInput(char[][] gameBoard, int position, String user) {
        char symbol = (user.equals("player")) ? 'X' : 'O';

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

        if (user.equals("player")) {
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            cpuPositions.add(position);
        }
    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List<Integer> l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Well done, you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry, cpu won!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "A Tie Game!";
            }
        }

        return "";
    }
}