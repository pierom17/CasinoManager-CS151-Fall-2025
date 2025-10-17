import java.util.Random;

public class Blackjack extends Game {
    // Simplified logic of Blackjack game
    private boolean roundOver = false;
    private boolean playerStands = false;
    private boolean dealerStands = false;
    private boolean playerHasHit = false;
    private double playerHandValue = 0.0;
    private double dealerHandValue = 0.0;
    private double currentPlayerBet = 0.0;
    private double lastAmount = 0.0;
    private final Random rand = new Random();

    public Blackjack() {
        super("Blackjack", 10.0);
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
        playerHasHit = false;
        lastAmount = 0.0;
        dealerHandValue = drawCard() + drawCard();
        playerHandValue = drawCard() + drawCard();

        System.out.printf("Player Hand Value: %f\n", playerHandValue);
        checkBlackjackOnDeal();
    }
    private void hit(){
        if (roundOver || playerStands || dealerStands) return;
        playerHandValue += drawCard();
        playerHasHit = true;
        checkPlayerOver21();
    }
    private void stand(){
        if (roundOver) return;
        playerStands = true;
        System.out.printf("Player Stands at sum of: %.2f\n", playerHandValue);
        
        System.out.println("Dealer's turn:");
        while (dealerHandValue < 17) {
            dealerHandValue += drawCard();
            System.out.printf("Dealer draws a card. Dealer hand value: %.2f\n", dealerHandValue);
            
            if (dealerHandValue > 21) {
                System.out.println("Dealer busted!");
                break;
            }
        }
        
        if (dealerHandValue <= 21) {
            System.out.printf("Dealer stands at: %.2f\n", dealerHandValue);
        }
        dealerStands = true;
        roundOver = true;
        settle(currentPlayerBet);
    }
    /**
     * Double the bet, take one more card, and end turn
     * @return currentPlayerBet double variable times 2
     */
    private double doubleDown(){
        if(roundOver || playerHasHit) {
            if (playerHasHit) {
                System.out.println("You can only double down on your original two cards!");
            }
            return currentPlayerBet;
        }
        
        currentPlayerBet = currentPlayerBet * 2;
        System.out.printf("Doubled Down Bet. New bet value is: %.1f\n", currentPlayerBet);
        
        playerHandValue += drawCard();
        playerHasHit = true;
        System.out.printf("You drew a card. Your new hand value is: %.2f\n", playerHandValue);
        
        if (playerHandValue > 21) {
            System.out.println("You busted! The cards add over 21!");
            betLoss(currentPlayerBet);
            return currentPlayerBet;
        }
        
        System.out.println("After doubling down, you must stand.");
        stand();
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
        if (dealerHandValue > 21){
            System.out.println("You won! The dealer busted!");
            betWin(currentPlayerBet);
        } else if (dealerHandValue > playerHandValue){
            System.out.println("You lost! The dealer's cards are closer to 21 than yours!");
            betLoss(currentPlayerBet);
        } else if (dealerHandValue < playerHandValue) {
            System.out.println("You won! Your cards are closer to 21!");
            betWin(currentPlayerBet);
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
        lastAmount += amount;
        System.out.printf("After your win, your current money is: $%.2f\n", lastAmount);
    }
    private void betLoss(double amount){
        roundOver = true;
        lastAmount -= amount;
        System.out.printf("After your loss, your current money is: $%.2f\n", lastAmount);
    }
    private void betPush(){
        roundOver = true;
        currentPlayerBet = lastAmount;
        System.out.println("After the Blackjack, your current money is: $" + lastAmount);
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
            System.out.printf("Your total hand value right now is: %.2f\n", playerHandValue);
            String action = UserInput.readNextLine("What do you want to do? Enter 'H' to Hit, 'D' to Double Down, or 'S' to Stand: ").toUpperCase().trim();
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
                default:
                    System.out.println("You can only enter one of those letters (H, D, S) or EXIT to exit the game.");
            }
        }
        if (roundOver) {
            System.out.println("Round over.");
            return;
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
