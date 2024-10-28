package PokemonTCG;

import java.util.ArrayList;

public abstract class Pokemon extends Card {
    private int hp;
    protected int energyRequired;
    private ArrayList<Energy> attachedEnergy;
    private boolean active;
    int attackDamage;
    
    public Pokemon(String name, int hp, int energyRequired) {
        super(name);
        this.hp = hp;
        this.energyRequired = energyRequired;
        this.attachedEnergy = new ArrayList<>();
    }
    
    public void attachEnergy(Energy energy) {
        attachedEnergy.add(energy);
    }
    
    public boolean canAttack() {
        return attachedEnergy.size() >= energyRequired;
    }
    
    public void damage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }
    
    public boolean isKnockedOut() {
        return hp <= 0;
    }
    
    public int getAttackDamage() {
    
        return attackDamage;
    }
    public int getHp() {
        return hp;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    abstract void attack(Pokemon target);
    
    @Override
    public void play(Player player, Player opponent) {
        if (!player.hasActivePokemon()) {
            setActive(true);
            player.setActivePokemon(this);
            System.out.println(player.getName() + " plays " + getName() + " as active Pokémon!");
        } else {
            player.addToBench(this);
            System.out.println(player.getName() + " adds " + getName() + " to bench!");
        }
    }
    public String getStatus() {
    return getName() + " (HP: " + getHp() + ")";
}
}