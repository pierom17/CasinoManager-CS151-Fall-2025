import java.util.Scanner;

public class Main {
    private static int menuChoice;

    public static void main(String args[]) {
        System.out.println("       Welcome to our Casino!       ");
        

        do {
            menuChoice = menu();
        }
        while(menuChoice != 0);
    }
    
    public static int menu() {
        int choice;

        Scanner scanner = new Scanner(System.in);
        System.out.println("  What game would you like to play? ");
        System.out.println("            1. Roulette             ");
        System.out.println("            2. Blackjack            ");
        System.out.println("            3. Craps                ");
        System.out.println("            0. Exit                 ");        
        choice = scanner.nextInt();
        scanner.close();

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
                    System.out.print("Roulette");
                case 2:
                    System.out.print("Blackjack");
                case 3:
                    System.out.print("Craps");
            }
            System.out.println("! Good luck!");
        }
        return choice;
    }
}
