import java.util.Scanner;

public class Table{
    private Game gameType;
    //new - shu saw
    private Player currentPlayer;
    //private int numOfPlayers;

    public Table (Game gameName){
        this.gameType = gameName;
        //this.numOfPlayers = numOfPlayers;
    }

    public Table (Game gameName, Player player){
        this.gameType = gameName;
        this.currentPlayer = player;
    }

    public void playGame() throws IllegalArgumentException{
        //gameType newGame = gameType();
        boolean playAgain = true;
        
        while(playAgain == true){
            //new - shu
            if (!gameType.deductRequiredBet(currentPlayer)) {
            System.out.println("Player could not afford the minimum bet. Returning to main menu.");
            break; 
            }

            gameType.play();
            //new - shu saw
            //updating player's total balance after each game
            double earnings = gameType.earned();
            currentPlayer.updateBalance(earnings);
            //System.out.printf("Current Player's Wallet Amount: %s | Player's Wallet Balance: %d",currentPlayer.getPlayerName(), earnings);
            System.out.printf("Net round change: $%.2f%n", earnings);
            System.out.printf("%s's New Updated Account Balance: $%.2f%n", currentPlayer.getPlayerName(),currentPlayer.getPlayerBalance());

            //new - shu saw
            //checks if player's balance is >=0, if its not then end the simulation because they can no longer gamble
            if (!currentPlayer.canAffordToPlay()) {
                playAgain = false;
                return;
            }

            System.out.println("");
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

    public void changeGame(){

    }

    public String getName(){
        return gameType.getName();
    }

    public String toString() {
        return gameType.toString();
    }
}
