import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private String playerName;
    private int playerID;
    private double playerBalance;
    private boolean isAssigned = false;

    private static final List<Integer> usedPlayerIDs = new ArrayList<>();
    private static int counter = 0;

    public Player(String playerName, double playerBalance) {
        this.playerName = playerName;
        this.playerBalance = playerBalance;
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

        if (isAssigned) {
            throw new UnsupportedOperationException("Game ID is set. No further changes allowed");
        }
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

    @Override
    public String toString() {
        return "    Your assigned ID is: " + playerID;
    }
}
