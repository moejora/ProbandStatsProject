package ThreeDoorPorblem;

public class ThreeDoorClass {

public void runSimulations(int trials) {
        int winsWithoutSwitching = simulateNoSwitch(trials);
        int winsWithSwitching = simulateSwitch(trials);

        // Output results
        System.out.println("Win percentage without switching: " + (winsWithoutSwitching / (double) trials) * 100 + "%");
        System.out.println("Win percentage with switching: " + (winsWithSwitching / (double) trials) * 100 + "%");
    }

    private int simulateNoSwitch(int trials) {
        int wins = 0;

        for (int i = 0; i < trials; i++) {
            int correctDoor = (int) (Math.random() * 3);
            int chosenDoor = (int) (Math.random() * 3);
            if (chosenDoor == correctDoor) {
                wins++;
            }
        }

        return wins;
    }

    private int simulateSwitch(int trials) {
        int wins = 0;

        for (int i = 0; i < trials; i++) {
            int correctDoor = (int) (Math.random() * 3);
            int chosenDoor = (int) (Math.random() * 3);
            if (chosenDoor != correctDoor) {
                wins++;
            }
        }

        return wins;
    }
}