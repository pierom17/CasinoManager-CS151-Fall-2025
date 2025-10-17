public class Main {
    private static int menuChoice;

    public static void main(String args[]) {
        String name = UserInput.readNextLine("Please enter your name: ");
        double balance = UserInput.readNextInt("Enter starting balance: ");
        System.out.println();
        System.out.printf("    Welcome to our Casino %s!       ", name);
        System.out.println();
        System.out.printf("  Your starting balance is: %.2f       ", balance);
        System.out.println();
        Player player = new Player(name, balance);
        player.setPlayerID();
        System.out.println(player);


        do {
            menuChoice = menu(player);

            //new - shu saw
            //checks if player's balance is >=0, if its not then end the simulation because they can no longer gamble
            if (menuChoice != 0 && !player.canAffordToPlay()) {
                System.out.println("\nYour current balance is $0.00 or less. The house requires you to leave!");
                menuChoice = 0; // Force exit
            }
        }
        while(menuChoice != 0);
    }

    public static int menu(Player player) {
        //new - shu saw , checks within start of menu loop
        if (!player.canAffordToPlay()) {
            return 0; 
        }
        System.out.println();
        System.out.println("            1. Roulette             ");
        System.out.println("            2. Blackjack            ");
        System.out.println("            3. Craps                ");
        System.out.println("            0. Exit                 ");

        int choice = UserInput.readNextInt("  What game would you like to play?\n");

        if ( choice < 0 || choice > 3 ) {
            throw new IllegalArgumentException("Invalid menu choice");
        }
        else if (choice == 0) {
            System.out.println("Thanks for coming. See you next time!");
        }
        else {
            System.out.print("You chose ");
            switch (choice) {
                case 1:
                    System.out.print("Roulette\n");
                    Roulette r = new Roulette();
                    Table t1 = new Table(r, player);
                    t1.playGame();
                    break;
                case 2:
                    System.out.print("Blackjack\n");
                    Blackjack bj = new Blackjack();
                    Table t2 = new Table(bj, player);
                    t2.playGame();
                    break;
                case 3:
                    System.out.print("Craps\n");
                    Craps c = new Craps();
                    Table t3 = new Table(c, player);
                    t3.playGame();
                    break;
            }
            System.out.println("! Good luck!");
        }
        return choice;
    }
}
