package BiNomial;

public class MathOperations {

    // Factorial Calculation: Finds the factorial of a given integer 'n'
    public static int factorial(int n) {
        int result = 1;  // Start with 1 as the factorial base
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Combination Calculation: Finds the number of ways to choose 'r' items from 'n' total
    public static int calculateCombinations(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // Permutation Calculation: Finds the number of ways to arrange 'r' items from 'n' in order
    public static int calculatePermutations(int n, int r) {
        return factorial(n) / factorial(n - r);
    }
}
