//Number Guessing Game.

import java.util.Scanner;

public class 𝒐𝒊𝒃𝒔𝒊𝒑_𝒕𝒂𝒔𝒌𝒏𝒐_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean playAgain = true;
        System.out.println("Hello, Player! What's your name?");
        String playerName = sc.nextLine();
        System.out.println("Welcome, " + playerName + "! Let's play the Number Guessing Game.");

        while (playAgain) {
            int randomNumber = (int) (Math.random() * 100) + 1;
            System.out.println("Rules :");
            System.out.println(" 1. Number is between 1 and 100.");
            System.out.println(" 2. You have 10 attempts to guess it. Good luck!");
            System.out.println(" 3. Each wrong guess will deduct 10 points of you score out of 100.");
            boolean won = false;
            int score = 100;

            for (int i = 1; i <= 10; i++) {
                System.out.print("Attempt " + (i) + ": Enter your guess: ");
                int guess = sc.nextInt();
                score = score - 10;
                if (randomNumber == guess) {
                    System.out.println("\uD83C\uDF89 Congratulations, " + playerName + "! You won the game . You guessed the number in " + i + " attempts!");
                    System.out.println("Your final score is: " + score);
                    won = true;
                    break;
                } else if (randomNumber > guess) {
                    System.out.println("⬇\uFE0F Too low! Try guessing a higher number.");
                } else {
                    System.out.println("⬆\uFE0F Too high! Try guessing a lower number.");
                }
                if (i <= 10) {
                    System.out.println("You have " + (10 - i) + " attempts remaining.");
                }
            }

            if (!won) {
                System.out.println("😢 Sorry, " + playerName + ". You've used all your attempts. The correct number was " + randomNumber + ".");
                System.out.println("Better luck next time!");
            }

            System.out.print("Do you want to play again, " + playerName + "? (yes/no): ");
            String response = sc.next();
            playAgain = response.equalsIgnoreCase("yes");
            if (playAgain) {
                System.out.println("\nGreat! Let's play another round.");
            } else {
                System.out.println("Thank you for playing, " + playerName + "! See you next time!");
            }
        }
    }
}
