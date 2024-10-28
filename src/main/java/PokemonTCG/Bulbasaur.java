package PokemonTCG;

public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbasaur", 70, 15);
    }

    // Bulbasaur-specific special attack
    @Override
    public void useSpecialAttack(Pokemon target) {
        int specialDamage = 18;
        System.out.println("Bulbasaur uses Vine Whip on " + target.getName() + " for " + specialDamage + " damage!");
        target.takeDamage(specialDamage);
    }
}
