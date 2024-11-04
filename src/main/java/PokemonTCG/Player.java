package PokemonTCG;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private String name;
    private Pokemon activePokemon;
    private ArrayList<Object> deck;
    private ArrayList<Object> hand;
    private ArrayList<Pokemon> bench;
    private ArrayList<Object> prizeCards;

    public Player(String name, ArrayList<Object> deck) {
        this.name = name;
        this.deck = new ArrayList<>(deck);
        this.hand = new ArrayList<>();
        this.bench = new ArrayList<>();
        this.prizeCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHandSize() {
        return hand.size();
    }

    public void drawStartingHand() {
        System.out.println(name + " is drawing their starting hand of 7 cards...");
        for (int i = 0; i < 7; i++) {
            drawCard();
        }
        setFirstActivePokemon();
    }

    public void drawCard() {
        if (!deck.isEmpty()) {
            hand.add(deck.remove(0));
            System.out.println(name + " drew a card.");
        } else {
            System.out.println(name + " has no cards left to draw.");
        }
    }

    // Checks if the player has any cards left in the deck
    public boolean hasCardsLeft() {
        return !deck.isEmpty();
    }

    public void setupPrizeCards() {
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizeCards.add(deck.remove(0));
        }
        System.out.println(name + " has set aside 6 prize cards.");
    }

    public boolean hasPrizeCards() {
        return !prizeCards.isEmpty();
    }

    public void collectPrizeCard() {
        if (!prizeCards.isEmpty()) {
            hand.add(prizeCards.remove(0));
            System.out.println(name + " collected a prize card!");
        } else {
            System.out.println(name + " has no prize cards left.");
        }
    }

    public int remainingPrizeCards() {
        return prizeCards.size();
    }

    //View all cards in hand
    public void viewHand() {
        System.out.println("\n" + name + "'s hand:");
        if (hand.isEmpty()) {
            System.out.println("Your hand is empty.");
        } else {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ". " + hand.get(i).getClass().getSimpleName());
            }
        }
    }

    public void setFirstActivePokemon() {
        for (Object card : hand) {
            if (card instanceof Pokemon) {
                activePokemon = (Pokemon) card;
                hand.remove(card);
                System.out.println(name + " has chosen " + activePokemon.getName() + " as the active Pokémon.");
                return;
            }
        }
        System.out.println(name + " has no Pokémon in their starting hand to set as active.");
    }

    public boolean hasActivePokemon() {
        return activePokemon != null && activePokemon.isAlive();
    }

    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    public void attack(Pokemon target) {
        if (activePokemon != null && activePokemon.isAlive()) {
            activePokemon.attack(target);
        } else {
            System.out.println(name + " has no active Pokémon to attack with.");
        }
    }

    public void switchActivePokemon() {
        for (Object card : hand) {
            if (card instanceof Pokemon) {
                activePokemon = (Pokemon) card;
                hand.remove(card);
                System.out.println(name + " switches in " + activePokemon.getName() + " as the new active Pokémon.");
                return;
            }
        }
        activePokemon = null;
        System.out.println(name + " has no more Pokémon in their hand to switch in.");
    }

    public void playTrainerCard(Player opponent) {
        for (Object card : hand) {
            if (card instanceof TrainerCard) {
                TrainerCard trainerCard = (TrainerCard) card;
                trainerCard.play(this, opponent); // Pass both players
                hand.remove(card);
                System.out.println(name + " played " + trainerCard.getClass().getSimpleName() + ".");
                return;
            }
        }
        System.out.println(name + " has no Trainer cards to play.");
    }

    // Attaches the first Energy card in hand to the active Pokémon
    public void attachEnergy() {
        if (activePokemon == null) {
            System.out.println(name + " has no active Pokémon to attach energy to.");
            return;
        }

        for (Object card : hand) {
            if (card instanceof Energy) {
                Energy energy = (Energy) card;
                activePokemon.attachEnergy(energy);
                hand.remove(card);
                System.out.println(name + " attached " + energy.getType() + " energy to " + activePokemon.getName() + ".");
                return;
            }
        }
        System.out.println(name + " has no Energy cards to attach.");
    }

    public void discardRandomCards(int count) {
        Collections.shuffle(hand);
        for (int i = 0; i < count && !hand.isEmpty(); i++) {
            Object discarded = hand.remove(0);
            System.out.println(name + " discarded a card.");
        }
    }

    // Searches the deck for a Pokémon card and adds it to the hand
    public Pokemon searchDeckForPokemon() {
        for (Object card : deck) {
            if (card instanceof Pokemon) {
                deck.remove(card);
                hand.add(card);
                System.out.println(name + " found a Pokémon in the deck and added it to their hand.");
                return (Pokemon) card;
            }
        }
        System.out.println(name + " did not find a Pokémon in the deck.");
        return null;
    }

    // Discards all cards in the player's hand
    public void discardHand() {
        hand.clear();
        System.out.println(name + " discarded their entire hand.");
    }

    // Checks if there are any Pokémon in the bench
    public boolean hasBenchedPokemon() {
        return !bench.isEmpty();
    }

    // Switches benched Pokémon with the active Pokémon
    public void switchRandomBenchedWithActive() {
        if (!bench.isEmpty()) {
            Collections.shuffle(bench);
            Pokemon newActive = bench.remove(0);
            bench.add(activePokemon);
            activePokemon = newActive;
            System.out.println(name + " switched their active Pokémon with a benched Pokémon.");
        } else {
            System.out.println(name + " has no benched Pokémon to switch.");
        }
    }
}
