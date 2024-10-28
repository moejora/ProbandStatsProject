package SetOperations;

import java.util.ArrayList;

public class SetOperations {
    
    // Method to compute union of two lists (no duplicate elements)
    public static ArrayList<String> union(ArrayList<String> firstList, ArrayList<String> secondList) {
        ArrayList<String> resultUnion = new ArrayList<>(firstList);
        
        // Add elements from secondList if they aren't already in the resultUnion
        for (String item : secondList) {
            if (!resultUnion.contains(item)) {
                resultUnion.add(item);
            }
        }
        return resultUnion;
    }

    // Method to compute intersection of two lists (common elements only)
    public static ArrayList<String> intersection(ArrayList<String> firstList, ArrayList<String> secondList) {
        ArrayList<String> resultIntersection = new ArrayList<>();
        
        // Only add elements that exist in both lists
        for (String item : firstList) {
            if (secondList.contains(item)) {
                resultIntersection.add(item);
            }
        }
        return resultIntersection;
    }

    // Method to compute complement (elements in mainList but not in subList)
    public static ArrayList<String> complement(ArrayList<String> fullList, ArrayList<String> excludeList) {
        ArrayList<String> resultComplement = new ArrayList<>(fullList);
        
        // Remove elements that are found in excludeList from resultComplement
        for (String item : excludeList) {
            resultComplement.remove(item);
        }
        return resultComplement;
    }
}
