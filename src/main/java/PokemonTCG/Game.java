package PokemonTCG;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponent;
    private boolean gameOver;
    
    public Game() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        initializeDecks();
    }
    
    private void initializeDecks() {
        // Add Pokemon
        for (int i = 0; i < 4; i++) {
            addCardToDeck(player1, new Pikachu());
            addCardToDeck(player1, new Charmander());
            addCardToDeck(player1, new Bulbasaur());
            addCardToDeck(player2, new Pikachu());
            addCardToDeck(player2, new Charmander());
            addCardToDeck(player2, new Bulbasaur());
        }
        
        // Add Energy
        for (int i = 0; i < 15; i++) {
            addCardToDeck(player1, new Energy("Basic"));
            addCardToDeck(player2, new Energy("Basic"));
        }
        
        // Add Trainer cards
        for (int i = 0; i < 4; i++) {
            addCardToDeck(player1, new ProfessorResearch());
            addCardToDeck(player1, new UltraBall());
            addCardToDeck(player1, new BossOrders());
            addCardToDeck(player2, new ProfessorResearch());
            addCardToDeck(player2, new UltraBall());
            addCardToDeck(player2, new BossOrders());
        }
        
        // Shuffle decks
        Collections.shuffle(player1.deck);
        Collections.shuffle(player2.deck);
    }
    
    private void addCardToDeck(Player player, Card card) {
        player.deck.add(card);
    }
    
    public void start() {
        setupGame();
        while (!gameOver) {
            playTurn();
            checkGameOver();
            switchTurn();
        }
        announceWinner();
    }
    
    private void setupGame() {
        // Draw starting hands
        for (int i = 0; i < 7; i++) {
            player1.drawCard();
            player2.drawCard();
        }
        
        // Set up prize cards
        player1.setupPrizeCards();
        player2.setupPrizeCards();
        
        // Coin flip
        currentPlayer = new Random().nextBoolean() ? player1 : player2;
        opponent = (currentPlayer == player1) ? player2 : player1;
        System.out.println("\n" + currentPlayer.getName() + " won the coin flip and goes first!");
    }
    

private void playTurn() {
    System.out.println("\n=== " + currentPlayer.getName() + "'s turn ===");
    currentPlayer.drawCard();
    currentPlayer.setPlayedEnergy(false);
    
    boolean turnEnded = false;

    while (!turnEnded) {
        showMenu();
        String choice = getPlayerChoice();

        switch (choice) {
            case "1":
                playFirstPokemon();
                break;
            case "2":
                playEnergy();
                break;
            case "3":
                showHand();
                break;
            case "4":
                turnEnded = true; // End turn
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
    
    attack(); // Attack after the player's actions are done
}

private void showMenu() {
    System.out.println("Choose an action:");
    System.out.println("1. Play a Pokémon");
    System.out.println("2. Attach an Energy card");
    System.out.println("3. See hand");
    System.out.println("4. End turn");
}

private String getPlayerChoice() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your choice: ");
    return scanner.nextLine();
}

private void showHand() {
    System.out.println(currentPlayer.getName() + "'s hand:");
    for (Card card : currentPlayer.getHand()) {
        System.out.println("- " + card.getName());
    }
}

    
    private void playFirstPokemon() {
        for (Card card : currentPlayer.hand) {
            if (card instanceof Pokemon) {
                card.play(currentPlayer, opponent);
                currentPlayer.hand.remove(card);
                break;
            }
        }
    }
    
    private void playEnergy() {
        if (!currentPlayer.hasPlayedEnergy() && currentPlayer.hasActivePokemon()) {
            for (Card card : currentPlayer.hand) {
                if (card instanceof Energy) {
                    card.play(currentPlayer, opponent);
                    currentPlayer.hand.remove(card);
                    break;
                }
            }
        }
    }
    
    private void attack() {
        if (currentPlayer.hasActivePokemon() && opponent.hasActivePokemon()) {
            Pokemon attacker = currentPlayer.getActivePokemon();
            Pokemon defender = opponent.getActivePokemon();
            attacker.attack(defender);
            
            if (defender.isKnockedOut()) {
                System.out.println(opponent.getName() + "'s " + defender.getName() + " was knocked out!");
                opponent.setActivePokemon(null);
            }
        }
    }
    
    private void switchTurn() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }
    
    private void checkGameOver() {
        if (currentPlayer.checkWinCondition() || opponent.checkWinCondition()) {
            gameOver = true;
        }
    }
    
    private void announceWinner() {
        Player winner = currentPlayer.checkWinCondition() ? opponent : currentPlayer;
        System.out.println("\n=== Game Over ===");
        System.out.println(winner.getName() + " wins the game!");
    }
}