import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private String playerName;
    private int playerID;
    private double playerBalance;

    private static final List<Integer> usedPlayerIDs = new ArrayList<>();
    private static int counter = 0;

    public Player(String playerName, double playerBalance) {
        this.playerName = playerName;
        this.playerBalance = playerBalance;
    }

    //new - shu saw
    /**
     * currently the game only checks how much you gain and lost from each game
     * but it doesn't add or subtract from the player's original amount
     * so creating two methods which gets the player's balance and updating the balance is a must
     * @return
     */
    public double getPlayerBalance(){
        return playerBalance;
    }

    public double updateBalance(double change){
        return playerBalance = playerBalance + change;
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
        System.out.printf("%s's available funds: $%.2f", playerName, playerBalance);
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

    //new - shu saw
    //needed to implement the user not being able to play anymore because their total balance is 0 or negative
    public boolean canAffordToPlay() {
        return playerBalance >= 0.0;
    }

    public boolean deductBet(double amount) {
        if (amount <= 0) {
            System.out.println("Bet amount must be positive");
            return false;
        }
        if (amount > playerBalance) {
            System.out.printf("%s has insufficient funds for a $%.2f bet", playerName, amount);
            System.out.printf("Balance: $%.2f.%n", playerBalance);
            return false;
        }
        
        playerBalance = playerBalance - amount;
        return true;
    }

    @Override
    public String toString() {
        return "    Your assigned ID is: " + playerID;
    }
}
