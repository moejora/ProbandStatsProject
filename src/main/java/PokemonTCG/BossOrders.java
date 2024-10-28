package PokemonTCG;

public class BossOrders extends TrainerCard {
    public BossOrders() {
        super("Boss's Orders");
    }
    
    public void play(Player player, Player opponent) {
        // Switch opponent's active with a benched Pokemon
        if (opponent.hasBenchedPokemon()) {
            opponent.switchRandomBenchedWithActive();
            System.out.println(player.getName() + " used Boss's Orders!");
        }
    }
}