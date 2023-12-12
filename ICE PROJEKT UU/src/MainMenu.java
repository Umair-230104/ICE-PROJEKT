import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private ArrayList<User> users;
    User currentUser;

    FileIO io = new FileIO();

    public void setUp() {
        saveAndLoadUserData();
        displayMenuOptions();
        PetOwner petOwner = new PetOwner();
        petOwner.displayPetOptionsTest();
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

        TextUI.displayMessage("Enter your user id: ");
        int userId = Integer.parseInt(TextUI.getUserInput());
        //check if userName already exists
        if (isUserIdTaken(userId)) {
            TextUI.displayMessage("Username already exists, please try again: ");
            signUp();
            return;
        }

        TextUI.displayMessage("Enter your username: ");
        String userName = TextUI.getUserInput();
        //check if userName already exists
        if (isUserNameTaken(userName)) {
            TextUI.displayMessage("Username already exists, please try again: ");
            signUp();
            return;
        }

        TextUI.displayMessage("Please choose profile type(owner or walker):");
        //String usertype= TextUI.getUserInput().toLowerCase();
        String profileType = TextUI.getUserInput();

        // Check if the entered profile type is valid
        if (!isValidProfileType(profileType)) {
            TextUI.displayMessage("Invalid profile type, please enter either 'owner' or 'walker': ");
            signUp();
            return;
        }

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

        // Enter and validate number
        String number;
        while (true) {
            TextUI.displayMessage("Enter your Number: ");
            number = TextUI.getUserInput();
            if (isValidNumber(number) && !isNumberTaken(number)) {
                break;
            } else {
                TextUI.displayMessage("Invalid or taken number. Please enter a valid, unique 8-digit number.");
            }
        }


        //check if number already exists
        if (isNumberTaken(number)) {
            TextUI.displayMessage("Number already exists, please try again: ");
            return;
        }

        String mail;
        while (true) {
            TextUI.displayMessage("Enter your mail: ");
            mail = TextUI.getUserInput();
            if (mail.contains("@") && !isMailTaken(mail)) {
                // Removed the check for .com or .dk as emails can have various domains
                break;
            } else {
                TextUI.displayMessage("Invalid or taken mail. Please enter a valid, unique email.");
            }
        }


        //check if mail contains @
        if (!mail.contains("@")) {
            System.out.println("Invalid, the mail does not include @ ");
            return;
        }


        // create a new user and add it to the list
        currentUser = new User(userId, userName, password, number, mail, profileType); // nyt
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
    private boolean isValidProfileType(String profileType) {
        return profileType.equals("owner") || profileType.equals("walker");
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


    private boolean isUserIdTaken(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }


    private boolean isUserNameTaken(String username) {
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
                int userId = Integer.parseInt(row [0]);
                String userName = row[0];
                String passWord = row[1];
                String number = row[2];
                String mail = row[3];
                String usertype= row[4];

                User u = new User(userId, userName, passWord, number, mail, usertype);
                users.add(u);
            }
        }
    }
}
