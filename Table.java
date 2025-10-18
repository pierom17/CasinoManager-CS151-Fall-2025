import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Table{
    private Game gameType;
    //private int numOfPlayers;

    public Table (Game gameName){
        this.gameType = gameName;
        //this.numOfPlayers = numOfPlayers;
    }

    private static final List<String> history = new ArrayList<>();

    private static void roundHistory(String gameName, double betAmt, double net) {
        String date = LocalDateTime.now().toString();
        String nullName;
        if (gameName == null || gameName.isBlank()) {
            nullName = "(Unstated)";
        } else {
            nullName = gameName.trim();
        }
        String data = String.format("%s | %s | Bet Amount: %.2f | Net Gain: %+.2f", date, nullName, betAmt, net);
        history.add(data);
    }

    public static void printHistory() {
        if (history.isEmpty()) {
            System.out.println("This session has no rounds.");
            return;
        }
        System.out.println("\n=== Session Round History ===");
        for (String data : history) {
            System.out.println(data);
        }
        System.out.println("==============================\n");
    }

    public void playGame() throws IllegalArgumentException{
        //gameType newGame = gameType();
        boolean playAgain = true;
        
        while(playAgain == true){
            gameType.play();
            double netGain = gameType.earned();
            roundHistory(gameType.getName(), gameType.getRequiredBet(), netGain);
            System.out.printf("Round summary: %s | Bet Amount: %.2f | Net Gain: %+.2f\n", gameType.getName(), gameType.getRequiredBet(), netGain);
            System.out.println("Would you like to play again? (Type the option number)"); // Player can choose one option to bet on
            System.out.println("1. Yes");
            System.out.println("2. No");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            try {
                if(choice != 1 && choice != 2){
                    throw new IllegalArgumentException("Choice must be either 1 or 2");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            if(choice == 1){
                continue;
            }
            else{
                playAgain = false;
            }
        }
    }

    public void changeGame() throws IllegalArgumentException {
        System.out.println("\nChoose your next game: ");
        System.out.println("1. Roulette");
        System.out.println("3. Craps");
        int choice = UserInput.readNextInt("Enter choice (1-3): ");

        switch (choice) {
            case 1:
                this.gameType = new Roulette();
                System.out.println("Changed to Roulette.");
                break;
            case 2:
                this.gameType = new Blackjack();
                System.out.println("Changed to Blackjack.");
                break;
            case 3:
                this.gameType = new Craps();
                System.out.println("Changed to Craps.");
                break;
            default:
                throw new IllegalArgumentException("Invalid choice, must choose between 1 and 3.");
        }
    }

    public String getName(){
        return gameType.getName();
    }

    public String toString() {
        return gameType.toString();
    }
}
