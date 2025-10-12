import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    private UserInput() {}

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
                System.out.println("Invalid number. Please enter a valid number or type 'exit' to quit.");
            }
        }
    }
}
