package PokemonTCG;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", 65, 1);
    }
    
    @Override
    public void attack(Pokemon target) {
        if (canAttack()) {
            System.out.println("Bulbasaur uses Vine Whip!");
            target.damage(18);
        }
    }
}