public class Roulette extends Game implements Playable {
    private boolean takingBets;
    private double betValues = 0.0;
    private boolean winLose;
    
    public Roulette() {
        super("Roulette", 5.0);
    }

    @Override
    public String getName() {
        return "Roulette";
    }
    
    @Override
    public void rules() {
        System.out.println("These are the rules of Roulette: ");
    }

    @Override
    public void play() {
        setGameStarted(true);
        getRequiredBet();
        spin();
    }

    @Override
    public double getRequiredBet() {
        return 5.0;
    }

    private static void spin() {
        int winningNumber = chooseNumber();
        
    }

    private static int chooseNumber() {
        return 0;
    }

    @Override
    public double earned() {
        return 0.0;
    }
    
}
