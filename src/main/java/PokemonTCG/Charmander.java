package PokemonTCG;

public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", 70, 15);
    }
    
    @Override
    public void attack(Pokemon target) {
        if (canAttack()) {
            System.out.println("Charmander uses Scratch!");
            target.damage(getAttackDamage());
        }
    }
}