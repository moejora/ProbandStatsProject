package BiNomial;

public class BinomialDistribution {

    // Binomial Distribution Calculation: Finds the probability of 'k' successes in 'n' trials with success probability 'p'
    public static double calculateBinomialProbability(int n, int k, double p) {
        int numCombinations = MathOperations.calculateCombinations(n, k); // Calculates combinations for n and k
        double successProbability = Math.pow(p, k);                       // Success probability raised to k
        double failureProbability = Math.pow(1 - p, n - k);               // Failure probability for remaining trials

        return numCombinations * successProbability * failureProbability;
    }
}
