import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        int numberToGuess;
        int userGuess;
        int attempts = 0;

        numberToGuess = (int) (Math.random() * 100) + 1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Guess the Number!");
        System.out.println("I have randomly chosen a number between 1 and 100.");
        System.out.println("Try to guess it!");

        do {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
            }
        } while (userGuess != numberToGuess);

        scanner.close();
    }
}




