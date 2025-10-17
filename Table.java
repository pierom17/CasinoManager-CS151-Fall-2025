
import java.util.Scanner;

public class Table {

    private Game gameType;
    //private int numOfPlayers;

    public Table(Game gameName) {
        this.gameType = gameName;
        //this.numOfPlayers = numOfPlayers;
    }

    public void playGame() throws IllegalArgumentException {
        //gameType newGame = gameType();
        boolean playAgain = true;

        while (playAgain == true) {
            gameType.play();

            double result = gameType.earned(); // money won or lost from the game
            System.out.println("\n   ROUND SUMMARY      ");
            if (result > 0) {
                System.out.printf("You won $%.2f this round!%n", result);
            } else if (result < 0) {
                System.out.printf("You lost $%.2f this round.%n", Math.abs(result));
            } else {
                System.out.println("It was a push/tie. No money gained or lost.");
            }
            System.out.println();
            System.out.println("Would you like to play again? (Type the option number)");
            System.out.println("1. Yes");
            System.out.println("2. No");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            try {
                if (choice != 1 && choice != 2) {
                    throw new IllegalArgumentException("Choice must be either 1 or 2");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (choice == 1) {
                continue;
            } 
            else {
                playAgain = false;
            }
        }

        System.out.println("\nThanks for playing " + gameType.getName() + "!");
        System.out.printf("Your total earnings from this session: $%.2f%n", gameType.earned());
    }

    public void changeGame() {

    }

    public String getName() {
        return gameType.getName();
    }

    public String toString() {
        return gameType.toString();
    }
}
