public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        do {
            game.startNewGame();
        } while (game.isNewGame());
    }
}
