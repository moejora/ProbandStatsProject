package StatsLibrary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatsLibraryTester {

    public static void main(String[] args) {
        StatsLibrary statsLibrary = new StatsLibrary();

        // Modified list of numbers
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(15);
        listOfNumbers.add(25);
        listOfNumbers.add(30);
        listOfNumbers.add(45);
        listOfNumbers.add(55);

        // Mean Tester
        double mean = statsLibrary.computeMean(listOfNumbers);
        System.out.println("Calculated Mean: " + String.format("%.5f", mean));

        // Median Tester
        double median = statsLibrary.computeMedian(listOfNumbers);
        System.out.println("Determined Median: " + String.format("%.5f", median));

        // Mode Tester
        Integer mode = statsLibrary.computeMode(listOfNumbers);
        if (mode != null) {
            System.out.println("Identified Mode: " + mode);
        } else {
            System.out.println("No unique mode found in the list.");
        }

        // Standard Deviation Tester
        double standardDeviation = statsLibrary.computeStandardDeviation(listOfNumbers);
        System.out.println("Computed Standard Deviation: " + String.format("%.5f", standardDeviation));

        // Variance Tester
        double variance = statsLibrary.computeVariance(listOfNumbers);
        System.out.println("Computed Variance: " + String.format("%.5f", variance));

        // Definition 2.6 Probability Axioms - Testing Valid Probability
        double probabilityA = 0.4;
        System.out.println("2.6: Is " + String.format("%.5f", probabilityA) + " a valid probability? " 
                           + StatsLibrary.isValidProbability(probabilityA));

        // Test Axioms 2
        double probabilityB = 0.9;
        System.out.println("2.6: Is " + String.format("%.5f", probabilityB) + " representing the full sample space? "
                           + StatsLibrary.isConditionalProbability(probabilityB));

        // Test Axioms 3 for mutually exclusive event probability
        ArrayList<Double> eventProbabilities = new ArrayList<>();
        eventProbabilities.add(0.25);
        eventProbabilities.add(0.25);
        eventProbabilities.add(0.5);

        ArrayList<Boolean> eventResults = new ArrayList<>();
        eventResults.add(true);
        eventResults.add(true);
        eventResults.add(false);

        boolean areExclusive = StatsLibrary.areMutuallyExclusive(eventProbabilities, eventResults);
        System.out.println("2.6: Are the events exclusive? " + areExclusive);

        if (areExclusive) {
            double unionProbability = StatsLibrary.calculateUnionOfMutuallyExclusiveEvents(eventProbabilities);
            System.out.println("2.6: Total probability of exclusive events: " + String.format("%.5f", unionProbability));
        } else {
            System.out.println("2.6: Events are NOT mutually exclusive!");
        }

        // Definition 2.9 Tester - Conditional Probability
        double pAandB = 0.15;
        double pBConditional = 0.6;
        try {
            double conditionalProbability = StatsLibrary.calculateConditionalProbability(pAandB, pBConditional);
            System.out.println("2.9: Computed P(A|B) as: " + String.format("%.5f", conditionalProbability));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Definition 2.10 - Independence Check (Example 1: Should be True)
        double probabilityA2 = 0.45;
        double probabilityB2 = 0.5;
        double pAandB2 = 0.225;
        boolean areIndependent = StatsLibrary.areIndependent(probabilityA2, probabilityB2, pAandB2);
        System.out.println("2.10: (Should be True) Independence of A and B? " + areIndependent);

        // Definition 2.10 - Independence Check (Example 2: Should be False)
        double probabilityA3 = 0.6;
        double probabilityB3 = 0.2;
        double pAandB3 = 0.12;
        boolean areIndependent2 = StatsLibrary.areIndependent(probabilityA3, probabilityB3, pAandB3);
        System.out.println("2.10: (Should be False) Independence of A and B? " + areIndependent2);

        // Definition 2.11 Partition Check (Example 1: Should be True)
        Set<Integer> universe = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            universe.add(i);
        }
        Set<Integer> B1 = new HashSet<>(Set.of(1, 2));
        Set<Integer> B2 = new HashSet<>(Set.of(3));
        Set<Integer> B3 = new HashSet<>(Set.of(4, 5, 6));

        ArrayList<Set<Integer>> sets = new ArrayList<>(Set.of(B1, B2, B3));
        boolean isPartition = StatsLibrary.isPartition(sets, universe);
        System.out.println("2.11: (Should be True) Does it partition S? " + isPartition);

        // Definition 2.11 Partition Check (Example 2: Should be False)
        Set<Integer> B1False = new HashSet<>(Set.of(1, 3));
        Set<Integer> B2False = new HashSet<>(Set.of(2, 4));
        Set<Integer> B3False = new HashSet<>(Set.of(5));
        ArrayList<Set<Integer>> setsFalse = new ArrayList<>(Set.of(B1False, B2False, B3False));
        boolean isPartitionFalse = StatsLibrary.isPartition(setsFalse, universe);
        System.out.println("2.11: (Should be False) Does it partition S? " + isPartitionFalse);

        // Definition 3.4 Expected Value
        ArrayList<Double> values = new ArrayList<>(List.of(2.0, 4.0, 5.0));
        ArrayList<Double> probabilities = new ArrayList<>(List.of(0.3, 0.4, 0.3));
        double expectedValue = StatsLibrary.calculateExpectedValue(values, probabilities);
        System.out.println("3.4: Calculated Expected Value of Y: " + String.format("%.5f", expectedValue));

        // Definition 3.5 Variance and Standard Deviation
        double varianceResult = StatsLibrary.calculateVariance(values, probabilities);
        System.out.println("3.5: Computed Variance V(Y): " + String.format("%.5f", varianceResult));

        double standardDeviationResult = StatsLibrary.calculateStandardDeviation(values, probabilities);
        System.out.println("3.5: Computed Standard Deviation of Y: " + String.format("%.5f", standardDeviationResult));

        // Definition 3.7 Binomial Probability
        int n = 6;
        int y = 4;
        double p = 0.5;
        double binomialProbability = StatsLibrary.calculateBinomialProbability(n, y, p);
        System.out.println("3.7: Calculated Binomial Probability: " + String.format("%.5f", binomialProbability));

        // Definition 3.8 Geometric Probability
        int yGeometric = 2;
        double pGeometric = 0.3;
        double geometricProbability = StatsLibrary.calculateGeometricProbability(yGeometric, pGeometric);
        System.out.println("3.8: Geometric Probability P(Y = " + yGeometric + "): " + String.format("%.5f", geometricProbability));
    }
}
