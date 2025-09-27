public class Craps extends Game implements Playable{
    
    public Craps(){
        super("Craps", 1);
    }

    public int rollDice(){

    }

    public bet(){

    }

    @Override
    public void play(){
        setGameStarted(true);
        rollDice();
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
        return "Craps";
    }
    @Override
    public double getRequiredBet() {
        return 1;
    }
    @Override
    public String toString() {
        return "Game: " + getName() + ", Required Bet: $" + getRequiredBet();
    }

}