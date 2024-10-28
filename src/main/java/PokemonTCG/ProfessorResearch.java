package PokemonTCG;

public class ProfessorResearch extends TrainerCard {

    public ProfessorResearch() {
        super("Professor's Research");
    }

    @Override
    public void play(Player player, Player opponent) {
        // Discard hand and draw 7 new cards
        player.discardHand();
        for (int i = 0; i < 7; i++) {
            player.drawCard();
        }
        System.out.println(player.getName() + " used Professor's Research!");
    }
}
