public abstract class Game implements Playable {

    private static int nextGameID = 1;
    private final String gameName;
    private final int gameID;
    private double requiredBet;
    private boolean gameStarted;

    public Game(String gameName, double requiredBet) {
        if (gameName == null || gameName.isBlank()) {
            throw new IllegalArgumentException("Game name cannot be empty.");
        }
        if (requiredBet < 0) {
            throw new IllegalArgumentException("Required bet cannot be negative.");
        }
        this.gameName = gameName.trim();
        this.gameID = nextGameID++;
        this.requiredBet = requiredBet;
        this.gameStarted = false;
    }

    public abstract String getName();
    public abstract double getRequiredBet();

    public int getGameID() {
        return gameID;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void updateRequiredBet(double newBet) {
        if (newBet < 0) {
            System.out.println("Warning: Bet cannot be negative. Value unchanged.");
            return;
        }
        this.requiredBet = newBet;
    }

    @Override
    public String toString() {
        return String.format(
            "Game Name: %s%nGame ID: %d%nRequired Bet: $%.2f%nStatus: %s",
            gameName, gameID, requiredBet, gameStarted ? "In Progress" : "Not Started"
        );
    }
}
