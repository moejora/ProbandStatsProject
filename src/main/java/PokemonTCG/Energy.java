package PokemonTCG;

public class Energy {

    private String type;

    // Define the energy type
    public Energy(String type) {
        this.type = type;
    }

    // Get the type of energy
    public String getType() {
        return type;
    }

    // Attach the energy to a Pokémon, allowing it to boost or use specific attacks
    public void attachToPokemon(Pokemon pokemon) {
        System.out.println("Attaching " + type + " energy to " + pokemon.getName());
        pokemon.addEnergy(this);
    }
}
