package PokemonTCG;

public class Energy extends Card {
    private String type;
    
    public Energy(String type) {
        super(type + " Energy");
        this.type = type;
    }
    
    @Override
    public void play(Player player, Player opponent) {
        if (player.hasActivePokemon() && !player.hasPlayedEnergy()) {
            player.getActivePokemon().attachEnergy(this);
            player.setPlayedEnergy(true);
            System.out.println(player.getName() + " attached " + getName() + " to " + 
                             player.getActivePokemon().getName());
        }
    }
}