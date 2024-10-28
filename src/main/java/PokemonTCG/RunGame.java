package PokemonTCG;

import java.util.ArrayList;

public class RunGame {

    public static void main(String[] args) {
        //Creates deck for player
        ArrayList<Object> deck1 = Main.createDeck();
        ArrayList<Object> deck2 = Main.createDeck();

        // Create players with their decks
        Player player1 = new Player("Ash", deck1);
        Player player2 = new Player("Gary", deck2);

        // Start the game
        Game game = new Game(player1, player2);
        game.startGame();
    }
}
