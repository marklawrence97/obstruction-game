import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board {
//  The board class keeps track of the state of the board. It has various accessor methods that allow useful information
//  such as whether the game is over, what moves can be made on the board and whose turn is next based on the current
//  state of the board. It also has one mutator method that allows a counter to be placed on the board.

    private final String[][] board;

    public Board(int rows, int cols) {
//      Initialize board with a given height and width.
        board = new String[rows][cols];
    }

    public String[][] getBoard() {
        return this.board;
    }

    public ArrayList<int[]> getActions() {
//      This method returns an array list that contains all the possible moves a player can make.
        ArrayList<int[]> possibleActions = new ArrayList<>();

        for (int i = 0; i < this.board.length; ++i) {
            for (int j = 0; j < this.board[0].length; ++j) {
                if (this.board[i][j] == null) {
                    possibleActions.add(new int[] { i, j });
                }
            }
        }

        return possibleActions;
    }

    public void placeCounter(int[] move, String player) {
//      This method places the counter on the board after checking whether the move entered was valid.

        int col, row;

        try {
            col = move[0];
            row = move[1];
        } catch (IndexOutOfBoundsException err) {
            System.out.println("Invalid move");
            return;
        }

        if (isInList(this.getActions(), new int[] {row, col})) {
            this.board[row][col] = player;
            for (int[] emptyNeighbour: getEmptyNeighbours(row, col)) {
                int x = emptyNeighbour[0];
                int y = emptyNeighbour[1];
                this.board[x][y] = "█";
            }
        }

    }

    public boolean isTerminal() {
//      This method returns true if the game is finished, and false if not.
        return this.getActions().size() == 0;
    }

    public String getTurn() {
//      This method returns the player whose turn it is to move based on the current state of the board. The "O" player
//      plays first, so if the count of O counters on the board is lower than the count of X counters then it is
//      "X"'s turn.

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < this.board.length; ++i) {
            for (int j = 0; j < this.board[0].length; ++j) {
                if (Objects.equals(this.board[i][j], "X")) {
                    xCount += 1;
                }
                if (Objects.equals(this.board[i][j], "O")) {
                    oCount += 1;
                }
            }
        }

        return (oCount <= xCount) ? "O" : "X";
    }

    public String getWinner() {
//      This method gets the winner of the game. If the game is not yet over there is no winner so returns null.

        if (!isTerminal()) {
            return null;
        }

        return getTurn();
    }

    public static boolean isInList(ArrayList<int[]> arrayList, int[] array) {
//      This method checks whether an array list contains a particular array.

        for (int[] item: arrayList) {
            if (Arrays.equals(item, array)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<int[]> getEmptyNeighbours(int row, int col) {
//      This method returns an ArrayList containing all of the empty squares around a square at [row][col]

        ArrayList<int[]> emptyNeighbours = new ArrayList<>();

        if (col < this.board[0].length - 1 && this.board[row][col + 1] == null) {
            emptyNeighbours.add(new int[] {row, col + 1});
        }

        if (col > 0 && this.board[row][col - 1] == null) {
            emptyNeighbours.add(new int[] {row, col - 1});
        }

        if (row > 0 && this.board[row - 1][col] == null) {
            emptyNeighbours.add(new int[] {row - 1, col});
        }

        if (row < this.board.length - 1 && this.board[row + 1][col] == null) {
            emptyNeighbours.add(new int[] {row + 1, col});
        }

        if (
                row < this.board.length - 1 &&
                col < this.board[0].length - 1 &&
                this.board[row + 1][col + 1] == null
        ) {
            emptyNeighbours.add(new int[] {row + 1, col + 1});
        }

        if (
                row > 0 &&
                col > 0 &&
                this.board[row - 1][col - 1] == null
        ) {
            emptyNeighbours.add(new int[] {row - 1, col - 1});
        }

        if (
                row > 0 &&
                col < this.board[0].length - 1 &&
                this.board[row - 1][col + 1] == null
        ) {
            emptyNeighbours.add(new int[] {row - 1, col + 1});
        }

        if (
                row < this.board.length - 1 &&
                col > 0 &&
                this.board[row + 1][col - 1] == null
        ) {
            emptyNeighbours.add(new int[] {row + 1, col - 1});
        }

        return emptyNeighbours;
    }
}
