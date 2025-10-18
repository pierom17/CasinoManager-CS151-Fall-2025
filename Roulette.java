import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Roulette extends Game {
    private double betValues = 5.0; // Bet amount for each number, color, or odd/even
    private static int winningNumber;
    private static int choice;
    private static String numberOptions[]  = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "00"};
    private static String colorOptions[]   = {"g", "r", "b", "r", "b", "r", "b", "r", "b", "r", "b",  "b",  "r",  "b",  "r",  "b",  "r",  "b",  "r",  "b",  "b",  "r",  "b",  "r",  "b",  "r",  "b",  "r",  "r",  "b",  "r",  "b",  "r",  "b",  "r",  "b",  "r",  "g"};
    private static String betType;
    private static ArrayList<Character> bets = new ArrayList<>();
    private static ArrayList<Integer> betsNum = new ArrayList<Integer>();
    
    public Roulette() {
        super("Roulette", 5.0);
    }

    @Override
    public String getName() {
        return "Roulette";
    }
    
    @Override
    public void rules() { // Displays the rules of roulette
        System.out.println("These are the rules of Roulette: ");
        System.out.println("1. Players can place bets one or more numbers, the colors red or black, or odd vs even.");
        System.out.println("2. Once bets are complete, the dealer spins the wheel and the ball will land on a number.");
        System.out.println("3. If the ball lands on a number/color/type that a player bet on, they win.");
        System.out.println("4. Payouts are as follows: ");
    }

    @Override
    public void play() { // Runs a round of roulette
        setGameStarted(true);
        getBets();
        spin();
        earned();
    }

    @Override // Returns how much you can bet
    public double getRequiredBet() {
        return betValues;
    }

    private static void spin() { // Logic for spinning the wheel and determining the winning number
        System.out.println("Spinning the wheel...");
        
        winningNumber = (int) (Math.random() * 38); // Gets the winning number between 0-37 (37 represents 00)
        System.out.println("The winning number is: " + numberOptions[winningNumber] + "!");

        System.out.print("This means "); // Tells the player if odds/evens or red/black won
        if ( winningNumber == 0 || winningNumber == 37 ) {
            System.out.println("neither odds nor evens win, but green wins!");
        }
        else {
            if ( winningNumber % 2 == 0 ) {
                System.out.print("evens ");
            }
            else {
                System.out.print("odds ");
            }

            if ( colorOptions[winningNumber].equals("r")) {
                System.out.println("and red wins!");
            }
            else {
                System.out.println("and black wins!");
            }
        }

    }

    private static void getBets() { // Logic for getting bets from the player
        System.out.println("What would you like to bet on? (Type the option number)"); // Player can choose one option to bet on, not multiple
        System.out.println("1. Numbers");
        System.out.println("2. Colors");
        System.out.println("3. Odd/Even");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline to prevent error after nextInt() call
        
        if ( choice == 1) { // Player chooses to bet on numbers
            String removedBets = "";
            betType = "Number";
            System.out.println("What numbers would you like to bet on?");
            System.out.println("Type out your choices separated by spaces, ending with -1 when done. ");
            System.out.println("Valid choices are 0-36 and 00.");
            System.out.println("Example: 1 2 3 4 5 -1");
            String input = scanner.nextLine();
            System.out.println(" ");
            String[] inputs = input.split(" "); // Gets the input string of bets for numbers

            for ( int i = 0; i < inputs.length; i++) {// Converts the string array to an integer arraylist of which numbers were bet on (37 represents 00)
                try {
                    if ( inputs[i].equals("00")) {
                        betsNum.add(37); // Using 37 to represent 00
                    }
                    else {
                        betsNum.add( Integer.parseInt(inputs[i]) );
                    }
                } catch(NumberFormatException nfe) {
                    removedBets += "\"" + inputs[i] + "\"" +  ", ";
                } catch (Exception e) {
                    removedBets += inputs[i] + ", ";
                }
            }

            Iterator<Integer> iterator = betsNum.iterator();
            while (iterator.hasNext()) {
                int bet = iterator.next();
                if (bet == -1) {
                    break;
                }
                
                try {
                    if (bet < 0 || bet > 37) {
                        removedBets += bet + ", ";
                        iterator.remove();
                    }
                } catch (IllegalArgumentException ile) {
                    removedBets += bet + ", ";
                    betsNum.remove(betsNum.indexOf(bet));
                } catch (Exception e) {
                    removedBets += bet + ", ";
                    betsNum.remove(betsNum.indexOf(bet));
                }
            }

            if ( removedBets.length() > 1) { // Informs the player of any invalid bets they made
                System.out.println("The following bets were not valid and have been excluded: " + removedBets.substring(0, removedBets.length() - 2));
            }

            System.out.print("You have placed bets on the following numbers: "); // Displays the valid bets the player made
            for ( int betVal : betsNum) {
                if ( betVal == 37) {
                    System.out.print("00 ");
                }
                else {
                    System.out.print(betVal + ", ");
                }
            }
                System.out.println(" \n");
        }
        else if ( choice == 2) { // Player chooses to bet on a color
            betType = "Color";
            boolean validInput = false;

            while ( !validInput) {
                System.out.println("What color would you like to bet on? (Type Red or Black)");
                String color = scanner.nextLine();
                if ( color.toLowerCase().contains("red") ) {
                    bets.add('r');
                    validInput = true;
                }
                else if ( color.toLowerCase().contains("black")) {
                    bets.add('b');
                    validInput = true;
                }
                else {
                    System.out.println(color + " is not a valid choice.");
                }
            }
            System.out.print("You have placed a bet on the color: "); // Displays the valid bets the player made
            if ( bets.get(0).equals('r')) {
                System.out.println("Red \n");
            }
            else {
                System.out.println("Black \n");
            }
        }
        else if ( choice == 3) { // Player chooses to bet on odds or evens
            betType = "oddEven";
            System.out.println("Would you like to bet on Odd or Even? (Type Odd or Even)");
            String oddEven = scanner.nextLine() + "    "; // Adding spaces to prevent error if user inputs less than 4 characters
            if ( oddEven.substring(0, 4).toLowerCase().contains("odd")) {
                bets.add('o');
            }
            else if ( oddEven.substring(0, 4).toLowerCase().contains("even")) {
                bets.add('e');
            }
            else {
                System.out.println(oddEven + " is not a valid choice.");
            }

            System.out.print("You have placed a bet on the number type: "); // Displays the valid bets the player made
            if ( bets.get(0).equals('o')) {
                System.out.println("Odds \n");
            }
            else {
                System.out.println("Evens \n");
            }
        }
        else {
            System.out.println(choice + " is not a valid choice.");
        }
    }

    @Override
    public double earned() { // Calculates how much the player won based on their bets, if any at all
        double winnings = 0.00;

        if ( choice == 1) {
            if ( betsNum.contains(winningNumber) ) {
                winnings = ( 36.00 * betValues ) - ( betValues * bets.size() );
            }
        }
        else if ( choice == 2 ) {
            if ( bets.get(0) == colorOptions[winningNumber].charAt(0) ) {
                winnings = 2.00 * bets.size() * betValues;
            }
        }
        else {
            if ( winningNumber != 0 && winningNumber != 37 ) {
                if ( ( winningNumber % 2 == 0 && bets.get(0).equals(('e')) ) || ( winningNumber % 2 != 0 && bets.get(0).equals('o') ) ) {
                    System.out.println("winningNumber: " + winningNumber);
                    System.out.println("winningNumber % 2: " + (winningNumber % 2));
                    winnings = 2.00 * betValues;
                }
            }
        }
        
        if ( winnings > 0 ) {
            System.out.printf("You won $%.2f!%n\n", winnings);
        }
        else {
            System.out.println("You didn't win anything this round.\n");
        }

        return (winnings -= ( betValues * bets.size() )); // Subtracts the amount the player bet from their winnings to get their net earnings
    }
}
    