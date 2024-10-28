package PokemonTCG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private String name;
    ArrayList<Card> hand;
    ArrayList<Card> deck;
    private ArrayList<Card> prizeCards;
    private ArrayList<Pokemon> bench;
    private Pokemon activePokemon;
    private boolean playedEnergy;
    
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.prizeCards = new ArrayList<>();
        this.bench = new ArrayList<>();
        this.playedEnergy = false;
    }
    
    public String getName() { return name; }
    public Pokemon getActivePokemon() { return activePokemon; }
    public boolean hasActivePokemon() { return activePokemon != null && !activePokemon.isKnockedOut(); }
    public boolean hasPlayedEnergy() { return playedEnergy; }
    public void setPlayedEnergy(boolean played) { this.playedEnergy = played; }
    public void setActivePokemon(Pokemon pokemon) { this.activePokemon = pokemon; }
    public int getHandSize() { return hand.size(); }
    public boolean hasBenchedPokemon() { return !bench.isEmpty(); }
    
    public void setupPrizeCards() {
        for (int i = 0; i < 6; i++) {
            if (!deck.isEmpty()) {
                prizeCards.add(deck.remove(deck.size() - 1));
            }
        }
    }
    
    public void drawCard() {
        if (!deck.isEmpty()) {
            Card card = deck.remove(deck.size() - 1);
            hand.add(card);
            System.out.println(name + " drew " + card.getName());
        }
    }
    
    public void addToBench(Pokemon pokemon) {
        if (bench.size() < 5) {
            bench.add(pokemon);
        }
    }
    
    public void discardHand() {
        hand.clear();
    }
    
    public void discardRandomCards(int count) {
        for (int i = 0; i < count && !hand.isEmpty(); i++) {
            hand.remove(hand.size() - 1);
        }
    }
    
    public void searchDeckForPokemon() {
        for (Card card : deck) {
            if (card instanceof Pokemon) {
                hand.add(card);
                deck.remove(card);
                System.out.println(name + " found " + card.getName());
                break;
            }
        }
    }
    
    public void switchRandomBenchedWithActive() {
        if (!bench.isEmpty()) {
            Pokemon benchedPokemon = bench.remove(0);
            if (activePokemon != null) {
                bench.add(activePokemon);
            }
            activePokemon = benchedPokemon;
            System.out.println(name + " switched active Pokémon to " + benchedPokemon.getName());
        }
    }
    
    public boolean checkWinCondition() {
        return prizeCards.isEmpty() || deck.isEmpty();
    }     
   public boolean hasPokemonInHand() {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }
    
    public void returnHandToDeck() {
        deck.addAll(hand);
        hand.clear();
    }
    
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    public int getPrizeCardsCount() {
        return prizeCards.size();
    }
    
    public void takePrizeCard() {
        if (!prizeCards.isEmpty()) {
            Card prizeCard = prizeCards.remove(0);
            hand.add(prizeCard);
            System.out.println(name + " drew " + prizeCard.getName() + " as a prize card!");
        }
    }
    
    public List<Card> getHand() {
        return hand;
    }
}