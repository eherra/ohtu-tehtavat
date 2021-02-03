
package ohtu;

public class App {

    public static void main(String[] args) {
        TennisGame game = new TennisGame("player1", "player2");

        System.out.println(game.getGameScore());

        game.wonPoint("player1");
        System.out.println(game.getGameScore());

        game.wonPoint("player1");
        System.out.println(game.getGameScore());

        game.wonPoint("player2");
        System.out.println(game.getGameScore());

        game.wonPoint("player1");
        System.out.println(game.getGameScore());

        game.wonPoint("player1");
        System.out.println(game.getGameScore());
    }
}
