package PokemonTCG;

public class UltraBall extends TrainerCard {
    public UltraBall() {
        super("Ultra Ball");
    }
    
    @Override
    public void play(Player player, Player opponent) {
        // Discard 2 cards to search for a Pokemon
        if (player.getHandSize() >= 3) { // Need at least 3 cards including Ultra Ball
            player.discardRandomCards(2);
            player.searchDeckForPokemon();
            System.out.println(player.getName() + " used Ultra Ball!");
        }
    }
}