public class Game {
//  The game class instantiates the board class and the display class in order to play the game.

    private final Display display;

    public Game() {
        this.display = new Display();
    }

    public void startNewGame() {
        Board board = new Board(6, 6);
        this.display.displayBoard(board.getBoard());

        while (!board.isTerminal()) {
            String player = board.getTurn();
            board.placeCounter(this.display.getMove(board.getBoard(), player), player);
            this.display.displayBoard(board.getBoard());
        }
        this.display.printWinner(board.getWinner());
    }

    public boolean isNewGame() {
        return this.display.newGame();
    }
}
