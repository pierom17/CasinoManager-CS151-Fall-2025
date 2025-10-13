public class Blackjack extends Game implements Playable {
    
    private boolean roundOver = false;
    private boolean playerStands = false;
    private boolean dealerStands = false;
    private double playerHandValue = 0.0;
    private double dealerHandValue = 0.0;
    private double currentPlayerBet = 0.0;

    public Blackjack() {
        super("Blackjack", 10.0);
    }
    public double raiseBet(double amount){
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Bet increase must be positive");
            }
            currentPlayerBet += amount;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return currentPlayerBet;
    }
    /**
     * Deals the cards to the players and the dealer
     */
    private void deal(){
        
    }
    private void hit(){

    }
    private void stand(){ 
    }
    /**
     * Double the bet, take one more card, and end turn
     * TODO: implement method once Player class is created
     * @return bet doubled
     */
    private double doubleDown(){
        // get bet from the Player class
        //return bet *= 2.0;
        return 0.0;
    }


    @Override
    public void play(){
        setGameStarted(true);
        deal();
    }
    @Override
    public void rules(){

    }
    @Override
    public double earned(){
        return 0.0;
    }
    @Override
    public String getName() {
        //for now:
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
