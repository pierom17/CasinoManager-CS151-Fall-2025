public class Blackjack extends Game implements Playable {
    
    public Blackjack() {
        super("Blackjack", 10.0);
    }
    /**
     * Deals the cards to the players and the dealer
     */
    public void deal(){
        
    }
    public void hit(){

    }
    private void stand(){ 
    }
    // Double the bet, take one more card, and end turn
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
        return 0;
    }
    @Override
    public String toString() {
        return "Game: " + getName() + ", Required Bet: $" + getRequiredBet();
    }

}
