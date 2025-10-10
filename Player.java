import java.util.Random;

public class Player {
    private String playerName;
    private int playerID;
    private double playerBalance;

    public Player(String playerName, double playerBalance) {
        this.playerName = playerName;
        this.playerID = generateID();
        this.playerBalance = playerBalance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerID() {
        return playerID;
    }
    private int generateID() {
        Random rand = new Random();
        return 10000 + rand.nextInt(90000);
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
}
