package PokemonTCG;

public interface Playable {

    void play(Player player, Player opponent);
}

abstract class Card implements Playable {

    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
