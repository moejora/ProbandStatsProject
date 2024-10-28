package PokemonStats;

public class RunCardGame {
    public static void main(String[] args) {
        CardGame pokeGame = new CardGame();
        pokeGame.run();

        // Run Monte Carlo simulations for 1 to 60 Pokémon
        int trials = 10000; // Number of trials for the simulation
        for (int i = 1; i <= 60; i++) {
            pokeGame.monteCarloSimulation(i, trials);
        }
    }
}
