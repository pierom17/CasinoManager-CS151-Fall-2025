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
