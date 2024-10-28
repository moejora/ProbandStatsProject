package PokemonTCG;

public class Charmander extends Pokemon {

    public Charmander() {
        super("Charmander", 100, 25);
    }

    // Charmander-specific special attack
    @Override
    public void useSpecialAttack(Pokemon target) {
        int specialDamage = 20;
        System.out.println("Charmander uses Ember on " + target.getName() + " for " + specialDamage + " damage!");
        target.takeDamage(specialDamage);
    }
}
