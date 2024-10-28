package PokemonTCG;

public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", 120, 30);
    }

    // Pikachu-specific special attack
    @Override
    public void useSpecialAttack(Pokemon target) {
        int specialDamage = 50;
        System.out.println("Pikachu uses Thunder Shock on " + target.getName() + " for " + specialDamage + " damage!");
        target.takeDamage(specialDamage);
    }
}
