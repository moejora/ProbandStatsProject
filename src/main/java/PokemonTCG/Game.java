package PokemonTCG;

import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponent;
    private int turnCounter;
    private Scanner scanner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.opponent = player2;
        this.turnCounter = 1;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Starting the Pokémon TCG game!");

        player1.setupPrizeCards();
        player2.setupPrizeCards();

        player1.drawStartingHand();
        player2.drawStartingHand();

        startBattle();
    }

    public void startBattle() {
        System.out.println("\nThe battle begins!");

        while (player1.hasPrizeCards() && player2.hasPrizeCards() && player1.hasCardsLeft() && player2.hasCardsLeft()) {
            System.out.println("\n--- Turn " + turnCounter + " ---");

            // Display prize card status
            System.out.println(player1.getName() + " has collected " + (6 - player1.remainingPrizeCards()) + " out of 6 prize cards.");
            System.out.println(player2.getName() + " has collected " + (6 - player2.remainingPrizeCards()) + " out of 6 prize cards.");

            // Current player's turn
            System.out.println(currentPlayer.getName() + "'s turn!");
            playerTurn();

            // Check for win condition
            if (!currentPlayer.hasPrizeCards()) {
                System.out.println("\nGame Over! " + currentPlayer.getName() + " wins by collecting all prize cards!");
                break;
            }

            // Switch turn
            if (currentPlayer == player1) {
                currentPlayer = player2;
                opponent = player1;
            } else {
                currentPlayer = player1;
                opponent = player2;
            }

            turnCounter++;
        }

        // If no cards are left in the deck for either player, check for a win condition
        if (!player1.hasCardsLeft()) {
            System.out.println("\nGame Over! " + player2.getName() + " wins as " + player1.getName() + " has no cards left in the deck!");
        } else if (!player2.hasCardsLeft()) {
            System.out.println("\nGame Over! " + player1.getName() + " wins as " + player2.getName() + " has no cards left in the deck!");
        }
    }

    private void playerTurn() {
        currentPlayer.drawCard();
        boolean turnEnded = false;

        while (!turnEnded) {
            System.out.println("\nChoose an action:");
            System.out.println("1. View Hand");
            System.out.println("2. Play Pokémon (Active or Bench)");
            System.out.println("3. Play Trainer Card");
            System.out.println("4. Attach Energy to Active Pokémon");
            System.out.println("5. Attack Opponent's Active Pokémon");
            System.out.println("6. End Turn");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    currentPlayer.viewHand();
                    break;
                case 2:
                    if (currentPlayer.hasActivePokemon()) {
                        System.out.println("Active Pokémon: " + currentPlayer.getActivePokemon().getName());
                        System.out.println("Would you like to switch or add to bench? (yes/no)");
                        String response = scanner.nextLine().toLowerCase();
                        if (response.equals("yes")) {
                            currentPlayer.switchActivePokemon();
                        }
                    } else {
                        currentPlayer.setFirstActivePokemon();
                    }
                    break;
                case 3:
                    currentPlayer.playTrainerCard(opponent); // Pass opponent as parameter
                    break;
                case 4:
                    currentPlayer.attachEnergy();
                    break;
                case 5:
                    if (currentPlayer.hasActivePokemon() && opponent.hasActivePokemon()) {
                        currentPlayer.attack(opponent.getActivePokemon());

                        // Check if opponent's Pokémon fainted and collect prize card
                        if (!opponent.getActivePokemon().isAlive()) {
                            System.out.println(opponent.getActivePokemon().getName() + " has fainted!");
                            System.out.println(currentPlayer.getName() + " has defeated " + opponent.getName() + "'s Pokémon!");
                            currentPlayer.collectPrizeCard();

                            // Check win condition after collecting prize card
                            if (!currentPlayer.hasPrizeCards()) {
                                System.out.println("\nGame Over! " + currentPlayer.getName() + " wins by collecting all prize cards!");
                                turnEnded = true; // End game immediately
                                return;
                            }

                            opponent.switchActivePokemon();
                        }
                    } else {
                        System.out.println("Cannot attack; either you or your opponent has no active Pokémon.");
                    }
                    break;
                case 6:
                    turnEnded = true;
                    break;
                default:
                    System.out.println("Invalid choice, please select again.");
                    break;
            }
        }
    }
}
