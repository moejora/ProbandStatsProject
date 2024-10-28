package SetOperations;

import java.util.ArrayList;
import java.util.Arrays;

public class SetOperationsTester {
    public static void main(String[] args) {
        ArrayList<String> daysOfWeek = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        ArrayList<String> weekdays = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        
        System.out.println("Union: " + SetOperations.union(weekdays, new ArrayList<>(Arrays.asList("Saturday", "Sunday"))));
        System.out.println("Intersection: " + SetOperations.intersection(weekdays, new ArrayList<>(Arrays.asList("Saturday", "Sunday"))));
        System.out.println("Complement: " + SetOperations.complement(daysOfWeek, weekdays));
    }
}
