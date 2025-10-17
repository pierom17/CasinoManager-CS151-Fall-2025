
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private String playerName;
    private int playerID;
    private double playerBalance;
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private double totalMoneyWon = 0.0;
    private double totalMoneyLost = 0.0;

    private static final List<Integer> usedPlayerIDs = new ArrayList<>();
    private static int counter = 0;

    public Player(String playerName, double playerBalance) {
        this.playerName = playerName;
        this.playerBalance = playerBalance;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public double getTotalMoneyWon() {
        return totalMoneyWon;
    }

    public double getTotalMoneyLost() {
        return totalMoneyLost;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID() {
        Random rand = new Random();
        int ID = 0;
        boolean uniqueGameID = false;

        while (!uniqueGameID) {
            ID = 10000 + rand.nextInt(90000);
            uniqueGameID = true;
            for (int i = 0; i < counter; i++) {
                if (usedPlayerIDs.get(i).equals(ID)) {
                    uniqueGameID = false;
                    break;
                }
            }
        }
        usedPlayerIDs.add(ID);
        counter++;
        this.playerID = ID;
    }

    public void displayPlayerBalance() {
        System.out.printf("%s's available funds: $.2f", playerName, playerBalance);
        System.out.println();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            playerBalance += amount;
            System.out.printf("$%.2f deposited successfully into %s's account.", amount, playerName);
            System.out.println();
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
        } else if (amount > playerBalance) {
            System.out.printf("%s's account has insufficient funds.", playerName);
            System.out.println();
        } else {
            playerBalance -= amount;
            System.out.printf("$%.2f withdrawn successfully from %s's account.", amount, playerBalance);
        }
    }

    //  Player Statistics Tracking 
    public void recordGame(boolean won, double amountChange) {
        gamesPlayed++;
        if (won) {
            gamesWon++;
            totalMoneyWon += amountChange;
        } else {
            totalMoneyLost += Math.abs(amountChange);
        }
    }

    public void displayStats() {
        System.out.println("\nPlayer Statistics for " + playerName + ":");
        System.out.printf("Games Played: %d%n", gamesPlayed);
        System.out.printf("Games Won: %d%n", gamesWon);
        System.out.printf("Total Money Won: $%.2f%n", totalMoneyWon);
        System.out.printf("Total Money Lost: $%.2f%n", totalMoneyLost);
        double net = totalMoneyWon - totalMoneyLost;
        System.out.printf("Net Earnings: $%.2f%n", net);
        
        double winRate = 0.0;
        if (gamesPlayed > 0) {
            double ratio = (double) gamesWon / gamesPlayed;
            winRate = ratio * 100;
        }
        System.out.printf("Win Rate: %.1f%%%n", winRate);
    }

    public void resetStats() {
        gamesPlayed = 0;
        gamesWon = 0;
        totalMoneyWon = 0.0;
        totalMoneyLost = 0.0;
        System.out.println("All stats reset.");
    }

    @Override
    public String toString() {
        return "    Your assigned ID is: " + playerID;
    }
}
