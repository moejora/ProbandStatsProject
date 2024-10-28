package PokemonTCG;

public class Pikachu extends Pokemon {
    public Pikachu() {
        super("Pikachu", 60, 1);
    }
    
    @Override
    public void attack(Pokemon target) {
        if (canAttack()) {
            System.out.println("Pikachu uses Thundershock!");
            target.damage(getAttackDamage());
        }
    }
}