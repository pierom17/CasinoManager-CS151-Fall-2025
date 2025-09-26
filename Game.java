public abstract class Game implements Playable{
    private String gameName;
    private int gameID;
    private double requiredBet;
    private boolean gameStarted;

    public Game (String gameName, double requiredBet) {
        this.gameName = gameName;
        this.requiredBet = requiredBet;
        this.gameStarted = false;
    }

    public abstract String getName();

    public abstract double getRequiredBet();

    public int getGameID() {
        return gameID;
    }
    public void setGameID() {

    }

    public boolean gameStarted() {
        return gameStarted;
    }
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String toString() {
        return "";
    }
}
