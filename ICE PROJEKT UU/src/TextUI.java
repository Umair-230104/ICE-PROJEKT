import java.util.Scanner;

public class TextUI {
    private String userName;
    private String passWord;

    public static String getInput(String msg) {
        displayMessage(msg);
        String input = getUserInput();
        return input;
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static String promptForInput(String s) {
        return s;
    }
}
