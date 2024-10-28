package BiNomial;

public class BinomialTester {

    public static void main(String[] args) {
        // Testing BinomialDistribution
        int n = 8;       // Total number of trials
        int k = 4;       // Number of successful outcomes
        double p = 0.6;  // Probability of success in each trial

        // Calculate and display the binomial probability
        double probability = BinomialDistribution.calculateBinomialProbability(n, k, p);
        System.out.println("Binomial Probability Result: " + String.format("%.5f", probability));

        // Testing MathOperations
        int combinationN = 7;
        int combinationR = 4;
        System.out.println("Combination Example (7 choose 4): " + MathOperations.calculateCombinations(combinationN, combinationR));

        int permutationN = 7;
        int permutationR = 4;
        System.out.println("Permutation Example (7 permute 4): " + MathOperations.calculatePermutations(permutationN, permutationR));
    }
}
