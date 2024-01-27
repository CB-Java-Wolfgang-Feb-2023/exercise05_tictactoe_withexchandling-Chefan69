import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

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
            int playerPos = getPositionFromUser("player");
            userInput(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (isGameOver(result)) break;

            int cpuPos = getPositionFromUser("cpu");
            userInput(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (isGameOver(result)) break;
        }
    }

    private static int getPositionFromUser(String user) {
        if (user.equals("cpu")) {
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            return cpuPos;
        }

        Scanner scan = new Scanner(System.in);
        int position = 0;
        while (true) {
            System.out.println("Enter your placement (1-9):");
            try {
                position = scan.nextInt();
                if (position < 1 || position > 9 || playerPositions.contains(position) || cpuPositions.contains(position)) {
                    System.out.println("Invalid input or position taken, please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; please enter a number between 1 and 9.");
                scan.next(); // Clear the scanner's buffer
            }
        }
        return position;
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
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O'; // Fixed '0' to 'O'
            cpuPositions.add(position);
        }

        switch (position) {
            case 1: gameBoard[0][0] = symbol; break;
            case 2: gameBoard[0][2] = symbol; break;
            case 3: gameBoard[0][4] = symbol; break;
            case 4: gameBoard[2][0] = symbol; break;
            case 5: gameBoard[2][2] = symbol; break;
            case 6: gameBoard[2][4] = symbol; break;
            case 7: gameBoard[4][0] = symbol; break;
            case 8: gameBoard[4][2] = symbol; break;
            case 9: gameBoard[4][4] = symbol; break;
            default: break; // Should never hit this case due to input validation
        }
    }

    public static String checkWinner() {
        List<List> winning = new ArrayList<List>();
        winning.add(Arrays.asList(1, 2, 3));
        winning.add(Arrays.asList(4, 5, 6));
        winning.add(Arrays.asList(7, 8, 9));
        winning.add(Arrays.asList(1, 4, 7));
        winning.add(Arrays.asList(2, 5, 8));
        winning.add(Arrays.asList(3, 6, 9));
        winning.add(Arrays.asList(1, 5, 9));
        winning.add(Arrays.asList(7, 5, 3));

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations! You won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins! Better luck next time.";
            }
        }

        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "It's a tie!";
        }

        return "";
    }

    private static boolean isGameOver(String result) {
        if (!result.isEmpty()) {
            System.out.println(result);
            return true;
        }
        return false;
    }
}
