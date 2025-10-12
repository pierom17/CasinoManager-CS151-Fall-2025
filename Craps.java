import java.util.Random;
import java.util.Scanner;

public class Craps extends Game implements Playable{
    private int moneyEarned = 0;
    
    public Craps(){
        super("Craps", 1.0);
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

    public void bet() throws IllegalArgumentException {
        System.out.println("What would you like to bet on? (Type the option number)"); // Player can choose one option to bet on
        System.out.println("1. Pass Line");
        System.out.println("2. Don't Pass Line");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice != 1 && choice != 2){
            throw new IllegalArgumentException("Choice must be either 1 or 2");
        }

        if(choice == 1){
            System.out.println("How much would you like to bet?");
            int amount = scanner.nextInt();

            boolean done = false;
            while(done != true){
                System.out.println("Rolling dice...");

                int diceRoll = rollDice();

                if(diceRoll == 7 || diceRoll == 11){
                    System.out.println("Dice rolled a: " + diceRoll);
                    System.out.println("You win!");
                    addEarnedMoney(amount);
                    done = true;
                }
                else if(diceRoll == 2 || diceRoll == 3 || diceRoll == 12){
                    System.out.println("Dice rolled a: " + diceRoll);
                    System.out.println("You lose :(");
                    done = true;
                }
                else{
                    System.out.println("Dice rolled a: " + diceRoll);
                    System.out.println("That means a push, rerolling dice");
                }
            }
        }

        else if(choice == 2){
            System.out.println("How much would you like to bet?");
            int amount = scanner.nextInt();

            boolean done = false;
            while(done != true){
                System.out.println("Rolling dice...");

                int diceRoll = rollDice();

                if(diceRoll == 2 || diceRoll == 3){
                    System.out.println("Dice rolled a: " + diceRoll);
                    System.out.println("You win!");
                    addEarnedMoney(amount);
                    done = true;
                }
                else if(diceRoll == 7 || diceRoll == 11){
                    System.out.println("Dice rolled a: " + diceRoll);
                    System.out.println("You lose :(");
                    done = true;
                }
                else{
                    System.out.println("Dice rolled a: " + diceRoll);
                    System.out.println("That means a push, rerolling dice");
                }
            }
        }
    }

    private void addEarnedMoney(int m){
        this.moneyEarned += m;
    }

    @Override //from Playable.java
    public void play(){
        setGameStarted(true);
        bet();
    }
    @Override //from Playable.java
    public void rules(){
        System.out.println("Simplified Craps Rule:");
        System.out.println("Choose to bet either the pass or don't pass line");
        System.out.println("Roll two dice and the sum if your dice score");
        System.out.println("If you bet the pass line and roll a score of 7 or 11 you win. If you roll a 2, 3 or 12 you lose");
        System.out.println("If you bet the don't pass line and roll a score of 2 or 3 you win. If you roll a 7 or 11 you lose");
        System.out.println("All other numbers are a push(tie)");
    }
    @Override //from Playable.java
    public double earned(){
        return moneyEarned;
    }
    @Override //from Game.java
    public String getName() {
        //for now:
        return "Craps";
    }
    @Override //from Game.java
    public double getRequiredBet() {
        return 1.0;
    }
}
