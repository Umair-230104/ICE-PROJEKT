import java.util.Scanner;

public class TextUI {
    private String userName;
    private String passWord;

    public String getInput(String msg) {
        displayMessage(msg);
        String input = getUserInput();
        return input;
    }

    public int getNumericInput(String msg) {
        System.out.println(msg);
        String input = getUserInput();
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Not a number. " + e.getMessage());
            num = getNumericInput(msg);
        }
        return num;
    }


    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
