package PokemonTCG;

import java.util.ArrayList;

public class Pokemon {

    protected String name;
    protected int hp;
    protected int baseDamage;
    protected ArrayList<Energy> energies = new ArrayList<>();
    private ArrayList<Energy> attachedEnergies = new ArrayList<>();

    public Pokemon(String name, int hp, int baseDamage) {
        this.name = name;
        this.hp = hp;
        this.baseDamage = baseDamage;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    // Method to add energy to the Pokémon
    public void addEnergy(Energy energy) {
        energies.add(energy);
        System.out.println(name + " now has " + energy.getType() + " energy attached.");
    }

    // Method to check if the Pokémon has a specific type of energy attached
    public boolean hasEnergy(String type) {
        for (Energy energy : energies) {
            if (energy.getType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    // Standard attack method that deals base damage to a target Pokémon
    public void attack(Pokemon target) {
        System.out.println(name + " attacks " + target.getName() + " for " + baseDamage + " damage!");
        target.takeDamage(baseDamage);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // Modify `takeDamage` to prevent negative HP
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage); // Prevents HP from going below zero
        System.out.println(name + " takes " + damage + " damage. Remaining HP: " + hp);
        if (hp == 0) {
            System.out.println(name + " has fainted!");
        }
    }

    public void attachEnergy(Energy energy) {
        attachedEnergies.add(energy);
        System.out.println("Attached " + energy.getType() + " energy to " + name + ".");
    }

    // Optional special attack method that deals additional damage
    public void useSpecialAttack(Pokemon target) {
        System.out.println(name + " uses a powerful special attack on " + target.getName() + "!");
        target.takeDamage(baseDamage + 10); // Special attack with additional damage
    }
}
