import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Game implements Playable {

    private static int nextGameID = 1;
    private final String gameName;
    private final int gameID;
    private double requiredBet;
    private boolean gameStarted;
    private LocalDateTime startTime;

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
        this.startTime = null;
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
        if (gameStarted) {
            this.startTime = LocalDateTime.now();
        }
    }

    public void updateRequiredBet(double newBet) {
        if (newBet < 0) {
            System.out.println("Warning: Bet cannot be negative. Value unchanged.");
            return;
        }
        this.requiredBet = newBet;
    }

    public void resetGame() {
        this.gameStarted = false;
        this.startTime = null;
        System.out.println("Game reset successfully for: " + gameName);
    }

    public void printSummary() {
        String timeStr = (startTime == null) ? "N/A" :
            startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("==== Game Summary ====");
        System.out.println("Name: " + gameName);
        System.out.println("ID: " + gameID);
        System.out.println("Required Bet: $" + String.format("%.2f", requiredBet));
        System.out.println("Started: " + (gameStarted ? "Yes" : "No"));
        System.out.println("Start Time: " + timeStr);
        System.out.println("======================");
    }

    @Override
    public String toString() {
        return String.format(
            "Game Name: %s%nGame ID: %d%nRequired Bet: $%.2f%nStatus: %s",
            gameName, gameID, requiredBet, gameStarted ? "In Progress" : "Not Started"
        );
    }
}
