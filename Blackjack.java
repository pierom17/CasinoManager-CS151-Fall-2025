import java.util.Random;

public class Blackjack extends Game implements Playable {
    // Simplified logic of Blackjack game
    private boolean roundOver = false;
    private boolean playerStands = false;
    private boolean dealerStands = false;
    private double playerHandValue = 0.0;
    private double dealerHandValue = 0.0;
    private double currentPlayerBet = 0.0;
    private double lastAmount = 0.0;
    private final Random rand = new Random();
    private final Player player;


    public Blackjack(Player player) {
        super("Blackjack", 10.0);
        this.player = player;
    }


    public double raiseBet(double amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Bet increase must be positive");
        }
        currentPlayerBet += amount;
        return currentPlayerBet;
    }
    /**
     * Deals the cards to the players and the dealer
     */
    private void deal(){
        roundOver = false;
        playerStands = false;
        dealerStands = false;
        lastAmount = 0.0;
        dealerHandValue = drawCard() + drawCard();
        playerHandValue = drawCard() + drawCard();


        System.out.printf("Player Hand Value: %f\n", playerHandValue);
        checkBlackjackOnDeal();
    }
    private void hit(){
        if (roundOver || playerStands || dealerStands) return;
        playerHandValue += drawCard();
        dealerHandValue += drawCard();
        checkPlayerOver21();
    }
    //for simplicity, if the player stands, the dealer will stand too
    private void stand(){
        if (roundOver) return;
        playerStands = true;
        dealerStands = true;
        System.out.printf("Player Stands at sum of: %f\n", playerHandValue);
        System.out.printf("Dealer Stands at sum of: %f\n", dealerHandValue);

        settle(currentPlayerBet);
    }
    /**
     * Double the bet, take one more card, and end turn
     * @return currentPlayerBet double variable times 2
     */
    private double doubleDown(){
        // get bet from the Player class
        //return bet *= 2.0;
        if(roundOver) return currentPlayerBet;
        currentPlayerBet *= 2.0;
        System.out.printf("Doubled Down Bet. New bet value is: %.1f\n", currentPlayerBet);

        playerHandValue += drawCard();
        System.out.printf("Your total after double-down: %.0f%n", playerHandValue);

        // Means game still is going on
        if (!checkPlayerOver21()) {
            stand();
        }
        return currentPlayerBet;
    }

    private boolean checkPlayerOver21() {
        if (playerHandValue > 21) {
            System.out.println("You lost! The cards add over 21!");
            betLoss(currentPlayerBet);
            return true;
        }
        return false;
    }
    private void settle(double amount){
        if (roundOver) return;

        if (dealerHandValue > 21){
            System.out.println("You lost! The cards add over 21!");
            betWin(amount);
        } else if (dealerHandValue > playerHandValue){
            System.out.println("You lost! The dealer's cards are closer to 21 than yours!");
            betLoss(amount);
        } else if (dealerHandValue < playerHandValue) {
            System.out.println("You won! Your cards are closer to 21!");
            betWin(amount);
        } else {
            System.out.println("Push");
            betPush();
        }
    }

    private int drawCard(){
        return rand.nextInt(10) + 2;
    }

    // Check Blackjack method. To see if either the player or the dealer got blackjack
    private void checkBlackjackOnDeal(){
        //In case the player won
        if (playerHandValue == 21 && dealerHandValue != 21){
            System.out.println("You won!");
            betWin(currentPlayerBet);
        } else if (dealerHandValue == 21 && playerHandValue != 21){
            System.out.println("You lost!");
            betLoss(currentPlayerBet);
        } else if (playerHandValue == 21 && dealerHandValue == 21){
            System.out.println("Both player and dealer got Blackjack!");
            betPush();
        }
    }

    private void betWin(double amount){
        roundOver = true;
        player.addBalance(amount);
        lastAmount += amount;
        System.out.printf("After your win, your current money is: $%.2f%n", player.getBalance());
    }
    private void betLoss(double amount){
        roundOver = true;
        player.addBalance(-amount);
        lastAmount -= amount;
        System.out.printf("After your loss, your current money is: $%.2f%n", player.getBalance());
    }
    private void betPush(){
        roundOver = true;
        currentPlayerBet = lastAmount;
        System.out.printf("After the Blackjack, your current money is: $%.2f%n", player.getBalance());
    }

    @Override
    public void play(){
        setGameStarted(true);
        currentPlayerBet = getRequiredBet();
        System.out.printf("Hello %s. Table Minimum is: $%.2f ===%n", getName(), getRequiredBet());
        // Raise bet at the beginning of the game if they want to:
        String raise = UserInput.readNextLine("Do you want to raise your bet? Type the amount to add (or press Enter to skip / type 'EXIT' to quit): ");
        if (!raise.isBlank()) {
            try {
                double raisedAmount = Double.parseDouble(raise.trim());
                if (raisedAmount > 0) raiseBet(raisedAmount);
                else System.out.println("Raise amount have to be positive. We will use the current bet.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid raise amount. We will use the current bet.");
            }
        }

        //Start game
        deal();
        //If it ended, then end the game

        while (!roundOver && !playerStands && !dealerStands) {
            System.out.printf("Your total hand value right now is: %.0f%n", playerHandValue);
            String action = UserInput.readNextLine("What do you want to do: Enter 'H' to Hit, 'D' to Double Down, or 'S' to Stand: ").toUpperCase().trim();
            switch (action) {
                case "H":
                    hit();
                    break;
                case "S":
                    stand();
                    break;
                case "D":
                    doubleDown();
                    break;
                case "EXIT":
                    roundOver = true;
                    System.out.println("Exiting round...");
                    break;
                default:
                    System.out.println("Enter H (Hit), D (Double), S (Stand), or EXIT.");;
            }
        }


    }
    @Override
    public void rules(){
        System.out.println("Blackjack Rules");
        System.out.println("""
                1. Play against the dealer and do NOT go over 21.
                2. You can hit, meaning take an extra card.
                3. You can stand, meaning you keep the cards and not get any more.
                4. You can Double Down, meaning you can double your bet and take an extra card.
                5, The goal is for any of the players to get your cards to add up to 21.
                """);
    }
    @Override
    public double earned(){
        return lastAmount;
    }
    @Override
    public String getName() {
        return "Blackjack";
    }
    @Override
    public double getRequiredBet() {
        return 10.0;
    }
    @Override
    public String toString() {
        return "Game: " + getName() + ", Required Bet: $" + getRequiredBet();
    }

}
