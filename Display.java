import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {
//  The display class acts as the UI for the game. It is responsible for receiving and displaying input from the user.

    public Display() {
        System.out.println("Welcome to obstruction, take it in turns to place your counter on the board. The aim of the" +
                        "game is to force your opponent to play the final move");
        System.out.println();
    }

    public void displayBoard(String[][] board) {

        System.out.print(" ");
        System.out.print(" | ");
        for (int i = 0; i < board[0].length; ++i) {
            System.out.print(i + 1);
            System.out.print(" | ");
        }

        System.out.println();
        System.out.print("---");

        for (int i = 0; i < board[0].length; ++i) {
            System.out.print("----");
        }

        System.out.println();

        for (int i = 0; i < board.length; ++i) {
            System.out.print(i + 1);
            System.out.print(" | ");
            for (int j = 0; j < board[0].length; ++j) {
                if (Objects.equals(board[i][j], "X")) {
                    System.out.print("X");
                    System.out.print(" | ");
                }

                if (Objects.equals(board[i][j], "O")) {
                    System.out.print("O");
                    System.out.print(" | ");
                }

                if (board[i][j] == null) {
                    System.out.print(" ");
                    System.out.print(" | ");
                }

                if (Objects.equals(board[i][j], "█")) {
                    System.out.print("█");
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printWinner(String winner) {
        System.out.printf("The winner of this game was player \"%s\"\n", winner);
    }

    public boolean newGame() {
//      This function returns true if the user wants to play again, and false if the user does not.

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Would you like to play again? (y/n)");
            userInput = scanner.nextLine();
        } while(!userInput.matches("[yY]|[nN]"));

        return userInput.matches("[yY]");
    }

    public int[] getMove(String[][] board, String player) {
//      This method gets input from the user.

        System.out.printf("It is player %s's turn\n", player);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.printf("Player %s, please enter two digits the first representing the column and the second representing" +
                    " the row:\n", player);
            userInput = scanner.nextLine();
        } while (!isValidInput(userInput, board));

        ArrayList<Integer> numbers = getDigits(userInput);

        return new int[] {numbers.get(0) - 1, numbers.get(1) - 1};
    }

    private ArrayList<Integer> getDigits(String input) {
//      This method parses a string and returns an ArrayList containing all of the numbers found in the string.

        ArrayList<Integer> numbers = new ArrayList<>();
        Pattern digit = Pattern.compile("\\d+");
        Matcher matcher = digit.matcher(input);

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        return numbers;
    }

    private boolean isValidInput(String userInput, String[][] board) {
//      This method returns true if a string entered by a user contains valid input for the board and false if not.

        ArrayList<Integer> numbers = getDigits(userInput);

        if (numbers.size() != 2) {
            System.out.println("Oops... please enter 2 digits!");
            return false;
        }

        int col = numbers.get(0);
        int row = numbers.get(1);

        if (col > board[0].length || col < 1 || row > board.length || row < 1) {
            System.out.println("Oops... that number isn't on the board!");
            return false;
        }

        if (board[row - 1][col - 1] != null) {
            System.out.println("Oops... can't play here, find an empty spot!");
            return false;
        }

        return true;
    }
}
