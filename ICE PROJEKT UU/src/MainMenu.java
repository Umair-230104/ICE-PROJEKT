import java.util.ArrayList;

public class MainMenu {
    private ArrayList<User> users;
    private User currentUser;
    private FileIO io = new FileIO();
    private PetOwner petOwner;
    private PetWalker petWalker;
    private TestklasseDB testklasseDB;
    private Database database;

    public MainMenu(TestklasseDB testklasseDB, Database database, PetOwner petOwner, PetWalker petWalker){
        this.testklasseDB = testklasseDB;
        this.database = database;
        this.petOwner = petOwner;
        this.petWalker = petWalker;
    }

    public void setUp() {
        displayMenuOptions();
    }

    public void displayMenuOptions() {
        while (true) {
            TextUI.displayMessage("Welcome To Paw Pal");
            TextUI.displayMessage("1. Log In");
            TextUI.displayMessage("2. Sign Up");
            TextUI.displayMessage("3. Exit program");
            TextUI.displayMessage("Enter Your Choice: ");
            String userInput = TextUI.getUserInput();
            try {
                int choice = Integer.parseInt(userInput);
                switch (choice) {
                    case 1:
                        boolean loggedIn = userLoginDB();
                        if (loggedIn) {
                            String userType = currentUser.getUsertype();
                            if ("owner".equalsIgnoreCase(userType) || "Owner".equalsIgnoreCase(userType)) {
                                petOwner.setCurrentUser(currentUser);
                                petOwner.runMethodOwner();
                            } else if ("walker".equalsIgnoreCase(userType) || ("walker".equalsIgnoreCase(userType))) {
                                petWalker.setCurrentUser(currentUser);
                                petWalker.runMethodWalker();
                            } else {
                                TextUI.displayMessage("Invalid user type.");
                            }
                        } else {
                            TextUI.displayMessage("Login failed.");
                        }
                        break;
                    case 2:
                        readUserDB();
                        makeUserDB();
                        boolean loggedInAfterSignUp = userLoginDB();
                        if (loggedInAfterSignUp) {
                            String userType = currentUser.getUsertype();
                            if ("owner".equalsIgnoreCase(userType) || "Owner".equalsIgnoreCase(userType)) {
                                petOwner.setCurrentUser(currentUser);
                                petOwner.runMethodOwner();
                            } else if ("walker".equalsIgnoreCase(userType) || ("walker".equalsIgnoreCase(userType))) {
                                petWalker.setCurrentUser(currentUser);
                                petWalker.runMethodWalker();
                            } else {
                                TextUI.displayMessage("Invalid user type.");
                            }
                        } else {
                            TextUI.displayMessage("Login failed.");
                        }
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        TextUI.displayMessage("Invalid choice, Please Try Again");
                }
            } catch (NumberFormatException e) {
                TextUI.displayMessage("Invalid input, please enter a number");
            }
        }
    }

    public void makeUserDB() {
        // ændre metoden til petuser
        database.writeUserDataDB();
    }

    public boolean userLoginDB() {
        currentUser = database.loginDB();
        System.out.println(currentUser.getUserid());
        return (currentUser != null);
    }

    public void readUserDB() {
        // ændre metoden til petuser
        database.readUserDataDB(testklasseDB);
    }
}