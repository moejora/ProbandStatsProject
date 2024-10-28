package StatsLibrary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StatsLibrary {

    public double computeMean(ArrayList<Integer> listOfNumbers) {
        int sum = 0;
        for (int singleNumber : listOfNumbers) {
            sum += singleNumber;
        }
        return sum / (double) listOfNumbers.size();
    }

    public double computeMedian(ArrayList<Integer> listOfNumbers) {
        listOfNumbers.sort(Integer::compareTo);
        int size = listOfNumbers.size();
        if (size % 2 == 0) {
            return (listOfNumbers.get(size / 2 - 1) + listOfNumbers.get(size / 2)) / 2.0;
        } else {
            return listOfNumbers.get(size / 2);
        }
    }

public Integer computeMode(ArrayList<Integer> listOfNumbers) {
    Integer mode = null;
    int maxCount = 0;
    for (int i = 0; i < listOfNumbers.size(); i++) {
        int count = 0;
        for (int j = 0; j < listOfNumbers.size(); j++) {
            if (listOfNumbers.get(j).equals(listOfNumbers.get(i))) {
                count++;
            }
        }
        if (count > maxCount) {
            maxCount = count;
            mode = listOfNumbers.get(i);
        } else if (count == maxCount) {
            mode = null; // No unique mode
        }
    }
    return mode;
}

    public double computeStandardDeviation(ArrayList<Integer> listOfNumbers) {
        double mean = computeMean(listOfNumbers);
        double sumSquaredDiffs = 0;
        for (int number : listOfNumbers) {
            sumSquaredDiffs += Math.pow(number - mean, 2);
        }
        return Math.sqrt(sumSquaredDiffs / listOfNumbers.size());
    }

    public double computeVariance(ArrayList<Integer> listOfNumbers) {
        double mean = computeMean(listOfNumbers);
        double sum = 0;
        for (int number : listOfNumbers) {
            sum += Math.pow(number - mean, 2);
        }
        return sum / listOfNumbers.size();
    }

    public static boolean isValidProbability(double probability) {
        return probability >= 0;
    }

    public static boolean isConditionalProbability(double probability) {
        return probability == 1.0;
    }

    public static boolean areMutuallyExclusive(ArrayList<Double> probabilities, ArrayList<Boolean> mutuallyExclusiveFlags) {
        for (Boolean flag : mutuallyExclusiveFlags) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public static double calculateUnionOfMutuallyExclusiveEvents(ArrayList<Double> probabilities) {
        double sum = 0;
        for (double probability : probabilities) {
            sum += probability;
        }
        return sum;
    }

    public static double calculateConditionalProbability(double pAandB, double pB) {
        if (pB == 0) {
            throw new IllegalArgumentException("P(B) must be greater than 0 to calculate conditional probability.");
        }
        return pAandB / pB;
    }

    public static boolean areIndependent(double pA, double pB, double pAandB) {
        return pAandB == pA * pB || (pB > 0 && pAandB / pB == pA) || (pA > 0 && pAandB / pA == pB);
    }

    public static boolean isPartition(ArrayList<Set<Integer>> sets, Set<Integer> universe) {
        Set<Integer> unionOfSets = new HashSet<>();
        for (Set<Integer> set : sets) {
            unionOfSets.addAll(set);
        }
        if (!unionOfSets.equals(universe)) {
            return false;
        }
        for (int i = 0; i < sets.size(); i++) {
            for (int j = i + 1; j < sets.size(); j++) {
                Set<Integer> intersection = new HashSet<>(sets.get(i));
                intersection.retainAll(sets.get(j));
                if (!intersection.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static double calculateExpectedValue(ArrayList<Double> values, ArrayList<Double> probabilities) {
        if (values.size() != probabilities.size()) {
            throw new IllegalArgumentException("The sizes of values and probabilities must match");
        }
        double expectedValue = 0;
        for (int i = 0; i < values.size(); i++) {
            expectedValue += values.get(i) * probabilities.get(i);
        }
        return expectedValue;
    }

    public static double calculateVariance(ArrayList<Double> values, ArrayList<Double> probabilities) {
        double mean = calculateExpectedValue(values, probabilities);
        double variance = 0;
        for (int i = 0; i < values.size(); i++) {
            double deviation = values.get(i) - mean;
            variance += Math.pow(deviation, 2) * probabilities.get(i);
        }
        return variance;
    }

    public static double calculateStandardDeviation(ArrayList<Double> values, ArrayList<Double> probabilities) {
        return Math.sqrt(calculateVariance(values, probabilities));
    }

    private static long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static double binomialCoefficient(int n, int y) {
        return factorial(n) / (factorial(y) * factorial(n - y));
    }

    public static double calculateBinomialProbability(int n, int y, double p) {
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("The probability must be between 0 and 1");
        }
        if (y < 0 || y > n) {
            throw new IllegalArgumentException("The number of successes must be between 0 and n");
        }
        double q = 1 - p;
        return binomialCoefficient(n, y) * Math.pow(p, y) * Math.pow(q, n - y);
    }

    public static double calculateGeometricProbability(int y, double p) {
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("Probability p must be between 0 and 1.");
        }
        if (y < 1) {
            throw new IllegalArgumentException("The trial number y must be 1 or greater.");
        }
        double q = 1 - p;
        return Math.pow(q, y - 1) * p;
    }
}
