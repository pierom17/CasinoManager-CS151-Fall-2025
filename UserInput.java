import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);
    private static int count;

    private UserInput() {}

    public static void survey() {
        if (count > 0) {
            System.out.println("Thank you for filling out our player survey!");
        } else {
            System.out.println("Thanks for taking our player survey! Your feedback is highly appreciated!");
            System.out.println("Is this your first time here?");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exciting game. Thank you for playing!");
                scanner.close();
                System.exit(0);
            } else if (userInput.toLowerCase().equalsIgnoreCase("no")); {
                System.out.println("We appreciate your patronage!");
            }
            count += 1;
        }
    }

    public static int readAge(String userPrompt) {
        while (true) {
            System.out.println(userPrompt);
            String userInput = scanner.nextLine();
            int age = Integer.parseInt(userInput);
            int difference = 21 - age;

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exciting game. Thank you for playing!");
                scanner.close();
                System.exit(0);
            } if (age < 21) {
                System.out.println("Sorry, you must be 21 years of age to play. Please come back in " + difference + " years!");
            }
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a valid number or type \"exit\" to quit. ");
            }
        }
    }

    public static String readNextLine(String userPrompt) {
        System.out.print(userPrompt);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the game...");
            scanner.close();
            System.exit(0);
        }

        return userInput;
    }
    
    public static int readNextInt(String userPrompt) {
        while (true) {
            System.out.print(userPrompt);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game. Thank you for playing!");
                scanner.close();
                System.exit(0);
            }
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a valid number or type \"exit\" to quit.");
            }
        }
    }
}
