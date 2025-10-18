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
            menuChoice = menu();
        }
        while(menuChoice != 0);
    }

    public static int menu() {
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
            Table.printHistory();
        }
        else {
            System.out.print("You chose ");
            switch (choice) {
                case 1:
                    System.out.print("Roulette\n");
                    Roulette r = new Roulette();
                    Table t1 = new Table(r);
                    t1.playGame();
                    break;
                case 2:
                    System.out.print("Blackjack\n");
                    Blackjack bj = new Blackjack();
                    Table t2 = new Table(bj);
                    t2.playGame();
                    break;
                case 3:
                    System.out.print("Craps\n");
                    Craps c = new Craps();
                    Table t3 = new Table(c);
                    t3.playGame();
                    break;
            }
            System.out.println("! Good luck!");
        }
        return choice;
    }
}
