import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private ArrayList<User> users;
    User currentUser;

    DbIO io = new DbIO();

    public void setUp() {
        saveAndLoadUserData();
        displayMenuOptions();
    }

    public void displayMenuOptions() {
        TextUI.displayMessage("Welcome To My Pet");
        TextUI.displayMessage("1. Log In");
        TextUI.displayMessage("2. Sign Up");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                logIn();
                break;
            case 2:
                signUp();
                break;
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    public void saveAndLoadUserData() {
        // test user
        ArrayList<String> user = io.readUserData("ListUser.data");
        users = new ArrayList<>();
// ---------------------------------------------------------------------------------------------------------------------
        loadUser();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveUserToFile();
            TextUI.displayMessage("Program Is Exiting, User Data Saved.");
        }));
// ---------------------------------------------------------------------------------------------------------------------
    }

    //User Login and Sign Up
    public void saveUserToFile() {
        io.saveUserData(users);
        TextUI.displayMessage("User Is Saved In File");
    }

    public void loadUser() {
        users = new ArrayList<>();
        ArrayList<String> userData = io.readUserData("ListUser.data");
        makeUser(userData);
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void signUp() {
        TextUI.displayMessage("Sign Up");
        TextUI.displayMessage("Enter your username: ");

        String username = TextUI.getUserInput();
        //check if username already exists
        if (isUsernameTaken(username)) {
            TextUI.displayMessage("Username already exists, please try again: ");
            signUp();
            return;
        }
        TextUI.displayMessage("Please choose profile type(owner or walker):");
        String usertype= TextUI.getUserInput().toLowerCase();

        //create password
        String password;
        while (true) {
            TextUI.displayMessage("Password must contain at least one uppercase letter, two numbers and it can't be longer than 25 characters. Create password: ");
            password = TextUI.getUserInput();

            if (isValidPassword(password)) {
                break;
            } else {
                TextUI.displayMessage("Invalid password. Please try again.");
            }
        }

        TextUI.displayMessage("Enter your Number: ");
        String number = TextUI.getUserInput();
        //check if number contains 8 letters
        if (!isValidNumber(number)) {
            TextUI.displayMessage("Invalid number. Please enter a valid 8-digit number.");
            return;

        }
        //check if number already exists
        if (isNumberTaken(number)) {
            TextUI.displayMessage("Number already exists, please try again: ");
            return;
        }

        TextUI.displayMessage("Enter your mail: ");
        String mail = TextUI.getUserInput();
        //check if mail already exists
        if (isMailTaken(mail)) {
            TextUI.displayMessage("Mail already exists, please try again: ");
            return;
        }
        //check if mail contains @
        if (!mail.contains("@")) {
            System.out.println("Invalid, the mail does not include @ ");
            return;
        }

        //check if mail contains .com or .dk
        if (!mail.contains(".com") || !mail.contains(".dk"))
        {
            System.out.println("Invalid, the mail does not include .com or .dk ");
            return;
        }

        // create a new user and add it to the list
        currentUser = new User(username, password, number, mail, usertype); // nyt
        // newUser blev aldrig gemt i "users" arraylist
        users.add(currentUser);
        TextUI.displayMessage("Sign up completed, you can now log in.");
        logIn();
    }

    public boolean isValidNumber(String number) {
        // Tjek om nummeret kun indeholder tal og har præcis 8 cifre
        return number.matches("\\d{8}");
    }

    private boolean isValidPassword(String password) {
        //pattern for at least 1 uppercase and 2 numbers and max 25 char long
        String uppercaseRegex = ".*[A-Z].*";
        String numbersRegex = ".*\\d.*";
        String lengthRegex= ".{1,25}";

        Pattern uppercasePattern = Pattern.compile(uppercaseRegex);
        Pattern numbersPattern = Pattern.compile(numbersRegex);
        Pattern lengthPattern= Pattern.compile(lengthRegex);
        //using pattern create matches for password
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        Matcher numbersMatcher = numbersPattern.matcher(password);
        Matcher lengthMatcher= lengthPattern.matcher(password);

        return uppercaseMatcher.matches() && numbersMatcher.matches() && lengthMatcher.matches();
    }

    public void logIn() {
        TextUI.displayMessage("Log In");
        TextUI.displayMessage("Enter Your Username: ");
        String username = TextUI.getUserInput();
        TextUI.displayMessage("Enter Your Password: ");
        String password = TextUI.getUserInput();
        if (authenticateUser(username, password)) {
            TextUI.displayMessage("Welcome " + username + "!");
        } else {
            TextUI.displayMessage("Invalid Username Or Password, Please Try Again!");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Find user
        currentUser = findUser(username, password);
        // Check if user exists and password is correct
        return currentUser != null && currentUser.getPassWord().equals(password) && currentUser.getUserName().equals(username);
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberTaken(String number) {
        for (User user : users) {
            if (user.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMailTaken(String mail) {
        for (User user : users) {
            if (user.getMail().equals(mail)) {
                return true;
            }
        }
        return false;
    }


    private User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void makeUser(ArrayList<String> userList) {
        if (userList.size() >= 5) {
            for (String s : userList) {
                String[] row = s.split(",");
                String userName = row[0];
                String passWord = row[1];
                String number = row[2];
                String mail = row[3];
                String usertype= row[4];

                User u = new User(userName, passWord, number, mail, usertype);
                users.add(u);
            }
        }
    }
}
