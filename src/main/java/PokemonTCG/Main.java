package PokemonTCG;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static ArrayList<Object> createDeck() {
        ArrayList<Object> deck = new ArrayList<>();

        // Add 20 Pokémon cards
        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                deck.add(new Charmander());
            } else if (i % 3 == 1) {
                deck.add(new Bulbasaur());
            } else {
                deck.add(new Pikachu());
            }
        }

        // Add 20 Energy cards
        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                deck.add(new Energy("Fire"));
            } else if (i % 3 == 1) {
                deck.add(new Energy("Grass"));
            } else {
                deck.add(new Energy("Electric"));
            }
        }

        // Add 20 Trainer cards
        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                deck.add(new UltraBall());
            } else if (i % 3 == 1) {
                deck.add(new BossOrders());
            } else {
                deck.add(new ProfessorResearch());
            }
        }

        // Shuffle the deck to randomize the order of cards
        Collections.shuffle(deck);

        return deck;
    }
}
