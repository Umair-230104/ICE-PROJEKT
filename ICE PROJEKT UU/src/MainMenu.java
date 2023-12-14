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
    }

    public void setUp() {
        displayMenuOptions();
    }
    public void displayMenuOptions() {
        TextUI.displayMessage("Welcome To My Pet");
        TextUI.displayMessage("1. Log In");
        TextUI.displayMessage("2. Sign Up");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch (choice) {
            case 1:
                boolean loggedIn = userLoginDB();
                if (loggedIn) {
                    // Show a menu to user based on the user type
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
                    // Show af menu to user based on the user type
                    String userType = currentUser.getUsertype();
                    if ("owner".equalsIgnoreCase(userType)) {
                        petOwner.runMethodOwner();
                    } else if ("walker".equalsIgnoreCase(userType)) {
                        petWalker.runMethodWalker();
                    } else {
                        TextUI.displayMessage("Invalid user type.");
                    }
                } else {
                    TextUI.displayMessage("Login failed.");
                }
                break;
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    public void makeUserDB() {
        // ændre metoden til petuser
        database.writePetOwnerDataDB();
    }

    public boolean userLoginDB() {
        currentUser = database.loginDB();
        System.out.println(currentUser.getUserid());
        return (currentUser != null);
    }

    public void readUserDB() {
        // ændre metoden til petuser
        database.readDogOwnerDataDB(testklasseDB);
    }

}