import java.util.ArrayList;

public class Table{
    private String gameType;
    private int numOfPlayers;

    public Table (String gameType, int numOfPlayers){
        this.gameType = gameType;
        this.numOfPlayers = numOfPlayers;
    }

    public void playGame(){
        gameType newGame = gameType();
        newGame.play();
    }

    public void changeGame(){
        
    }
}