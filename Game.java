public abstract class Game implements Playable{
    private static int nextGameID = 1;
    private String gameName;
    private int gameID;
    private double requiredBet;
    private boolean gameStarted;

    public Game (String gameName, double requiredBet) {
        this.gameName = gameName;
        this.gameID = nextGameID++;
        this.requiredBet = requiredBet;
        this.gameStarted = false;
    }

    public abstract String getName();

    public abstract double getRequiredBet();

    //new - shu saw
    //Error: when playing the game, it asks you to enter an amount to bet with, but this amount isn’t from the player’s balance 
    public abstract boolean deductRequiredBet(Player player);

    public int getGameID() {
        return gameID;
    }

    public boolean gameStarted() {
        return gameStarted;
    }
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    @Override
    public String toString() {
        return "Game Name: " + gameName + "\nGame ID: " + gameID + "\nRequired Bet: $" + requiredBet;
    }
}
