package PokemonStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CardGame {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private static final int TOTAL_CARDS = 60; // Total cards in the deck

    public CardGame() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void fillDeck() {
        deck.clear(); // Clear existing deck
        for (int i = 0; i < 4; i++) {
            deck.add(new Charmander()); // Add 4 Charmanders
        }
        // Fill remaining slots with random Trainer and Energy cards, but only 4 Rare Candies
        int rareCandyCount = 0;
        Random rng = new Random();
        while (deck.size() < TOTAL_CARDS) {
            if (rng.nextBoolean()) {
                deck.add(new Energy()); // Add an Energy card
            } else {
                if (rareCandyCount < 4) {
                    deck.add(new RareCandy()); // Add a Trainer card (RareCandy)
                    rareCandyCount++;
                } else {
                    deck.add(new Energy()); // Add an Energy card if RareCandy limit reached
                }
            }
        }
    }

    public void drawHand() {
        Random rng = new Random();
        hand.clear(); // Clear hand before drawing
        for (int i = 0; i < 7; i++) {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
        }
    }

    public boolean PokemonInHand() {
        for (Card singleCard : hand) {
            if (singleCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        for (int numPokemon = 1; numPokemon <= 9; numPokemon++) {
            monteCarloSimulation(numPokemon, 10000);
        }
    }

    public void monteCarloSimulation(int numPokemon, int trials) {
        int totalReshuffles = 0;
        int successes = 0; // Count successful draws
        double[] rareCandyOdds = new double[5]; // To store odds of 0 to 4 Rare Candies

        // Clear odds for each simulation run
        Arrays.fill(rareCandyOdds, 0);

        for (int trial = 0; trial < trials; trial++) {
            fillDeckWithPokemon(numPokemon);
            int reshuffles = 0;

            drawHand();

            if (PokemonInHand()) {
                successes++; // Count as success if there's a Pokémon in hand
            }

            while (!PokemonInHand()) {
                reshuffles++;
                fillDeckWithPokemon(numPokemon);
                drawHand();
            }

            totalReshuffles += reshuffles;

            // Count Rare Candies in the prize deck
            countRareCandiesInPrizeDeck(rareCandyOdds);
        }

        // Calculate average reshuffles and success rate
        double averageReshuffles = (double) totalReshuffles / trials;
        double successRate = (double) successes / trials * 100; // Convert to percentage
        System.out.printf("Average reshuffles for %d Pokémon: %.6f\n", numPokemon, averageReshuffles);
        System.out.printf("Success rate for %d Pokémon: %.6f%%\n", numPokemon, successRate);
        System.out.println(); // Add a space between different Pokémon counts

        // Normalize rare candy counts to percentages
        for (int i = 0; i <= 4; i++) {
            rareCandyOdds[i] = (rareCandyOdds[i] / trials) * 100; // Convert to percentage
        }

        // Odds of Rare Candies in the prize deck
        printRareCandyOdds(numPokemon, rareCandyOdds);
    }

    private void fillDeckWithPokemon(int numPokemon) {
        deck.clear(); // Clear existing deck
        for (int i = 0; i < numPokemon; i++) {
            deck.add(new Charmander()); // Add Pokémon cards
        }
        // Fill remaining slots with random Trainer and Energy cards, but only 4 Rare Candies
        int rareCandyCount = 0;
        Random rng = new Random();
        while (deck.size() < TOTAL_CARDS) {
            if (rng.nextBoolean()) {
                deck.add(new Energy()); // Add an Energy card
            } else {
                if (rareCandyCount < 4) {
                    deck.add(new RareCandy()); // Add a Trainer card (RareCandy)
                    rareCandyCount++;
                } else {
                    deck.add(new Energy()); // Add an Energy card if RareCandy limit reached
                }
            }
        }
    }

    private void countRareCandiesInPrizeDeck(double[] rareCandyOdds) {
        // Draw 4 cards to form the prize deck
        Random rng = new Random();
        int rareCandyCount = 0;

        for (int i = 0; i < 4; i++) {
            int cardIndex = rng.nextInt(deck.size());
            Card drawnCard = deck.get(cardIndex);
            if (drawnCard instanceof RareCandy) {
                rareCandyCount++;
            }
        }

        // Increment the count for the corresponding number of Rare Candies
        rareCandyOdds[rareCandyCount]++;
    }

    private void printRareCandyOdds(int numPokemon, double[] rareCandyOdds) {
        System.out.printf("When there is %d Pokémon card(s) in the deck:\n", numPokemon);
        for (int i = 0; i <= 4; i++) {
            System.out.printf("Odds of having %d Rare Candies in prize deck: %.6f%%\n", i, rareCandyOdds[i]);
        }
        System.out.println(); // Add a space after the odds section
    }
}
