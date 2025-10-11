import java.util.ArrayList;

public class Table{
    private Game gameType;
    private int numOfPlayers;

    public Table (String gameName, int numOfPlayers){
        this.gameType = new gameName();
        this.numOfPlayers = numOfPlayers;
    }

    public void playGame() throws NumberOutOfRangeException{
        //gameType newGame = gameType();
        boolean playAgain = true;
        gameType.play();
        while(playAgain == true){
            System.out.println("Would you like to play again? (Type the option number)"); // Player can choose one option to bet on
            System.out.println("1. Yes");
            System.out.println("2. No");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if(choice != 1 && choice != 2){
                throw new NumberOutOfRangeException("Choice must be either 1 or 2");
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