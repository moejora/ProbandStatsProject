package BirthdayProblem;

import java.util.Scanner;

public class BirthdayProgram {

public void runSimulation(int classSize, int numberOfRuns) {
        int matchingBirthdaysCount = 0;

        for (int i = 0; i < numberOfRuns; i++) {
            if (hasSharedBirthday(classSize)) {
                matchingBirthdaysCount++;
            }
        }

        // Calculate and display the probability of shared birthdays
        double probability = (double) matchingBirthdaysCount / numberOfRuns * 100;
        System.out.println("Probability of shared birthdays with " + classSize + " people: " + probability + "%");
    }

    private boolean hasSharedBirthday(int classSize) {
        Person[] people = new Person[classSize];
        int[] birthdayTracker = new int[365];

        // Initialize people and count each birthday in the tracker
        for (int i = 0; i < classSize; i++) {
            people[i] = new Person();
            int birthday = people[i].getBirthday();

            // If birthday already exists in tracker, return true for a match
            if (birthdayTracker[birthday - 1] > 0) {
                return true;
            }
            birthdayTracker[birthday - 1]++;
        }
        return false;
    }
}