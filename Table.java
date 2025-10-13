import java.util.Scanner;

public class Table{
    private Game gameType;
    //private int numOfPlayers;

    public Table (Game gameName){
        this.gameType = gameName;
        //this.numOfPlayers = numOfPlayers;
    }

    public void playGame() throws IllegalArgumentException{
        //gameType newGame = gameType();
        boolean playAgain = true;
        
        while(playAgain == true){
            gameType.play();
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
