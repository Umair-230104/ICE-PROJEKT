import java.util.ArrayList;

public class PetWalker {

    TestklasseDB testklasseDB = new TestklasseDB();
    Database db = new Database(testklasseDB);
    private User currentUser;

    public void runMethodWalker() {
        displayOptionsWalker();
    }

    //Display
    public void displayOptionsWalker() {
        TextUI.displayMessage("\n");
        TextUI.displayMessage("You now have following options: ");
        TextUI.displayMessage("1. Show Jobs");
        TextUI.displayMessage("2. Show profile");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                showJobsDB();
                displayOptionsWalker();
                break;
            case 2:
                showProfileInformation();
                displayOptionsWalker();
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    public void showJobsDB() {
        TextUI.displayMessage("Jobs: ");
        db.readJobDataDB(testklasseDB);
        testklasseDB.showJobs();
    }

    public void showProfileInformation() {
        if (currentUser != null) {
            TextUI.displayMessage("Profile Information:");
            TextUI.displayMessage("UserID: " + currentUser.getUserId());
            TextUI.displayMessage("Username: " + currentUser.getUserName());
            TextUI.displayMessage("User password: " + currentUser.getPassWord());
            TextUI.displayMessage("Email: " + currentUser.getMail());
            TextUI.displayMessage("Phone: " + currentUser.getNumber());
            TextUI.displayMessage("Usertype: " + currentUser.getUsertype());
            // Tilføj eventuel anden brugerinformation, der skal vises
        } else {
            TextUI.displayMessage("No user is currently logged in.");
        }
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
