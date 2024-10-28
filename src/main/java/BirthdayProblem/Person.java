package BirthdayProblem;

// Person class to represent a person with a birthday (day of the year)
public class Person {
    int birthday;

    // Constructor: assigns a random birthday from 1 to 365 (ignores leap years)
    public Person() {
        birthday = (int) (Math.random() * 365) + 1;
    }
    
    // Getter for birthday
    public int getBirthday() {
        return birthday;
    }
}
