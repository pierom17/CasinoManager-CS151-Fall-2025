import java.util.Random;

public class Craps extends Game implements Playable{
    
    public Craps(){
        super("Craps", 1);
    }

    public int rollDice(){
        int dice1;
        int dice2;

        Random r = new Random();

        dice1 = r.nextInt(6) + 1;
        dice2 = r.nextInt(6) + 1;
        int totalDice = dice1 + dice2;

        return totalDice;
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