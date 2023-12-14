import java.util.ArrayList;

public class PetOwner {
    private ArrayList<Pet> pets;
    private User currentUser;
    private FileIO io = new FileIO();
    private ArrayList<Job> jobs = new ArrayList<>();
    private ArrayList<String> jobsList;
    private Database db = new Database();
    private TestklasseDB testklasseDB = new TestklasseDB();

    public void runMethodOwner() {
        displayOptionsOwner();

    }

    //Display
    public void displayOptionsOwner() {
        TextUI.displayMessage("\n");
        TextUI.displayMessage("You now have following options: ");
        TextUI.displayMessage("1. Pet Menu");
        TextUI.displayMessage("2. Pet activity");
        TextUI.displayMessage("3. Show profile information.");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                petMenu();
                break;
            case 2:
                showJobsActivity();
                break;
            case 3:
                showProfileInformation();
                displayOptionsOwner();
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
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

    public void petMenu() {
        TextUI.displayMessage("\n");
        TextUI.displayMessage("You now have following options: ");
        TextUI.displayMessage("1. Create pet");
        TextUI.displayMessage("2. List of pet");
        TextUI.displayMessage("3. Delete pets");
        TextUI.displayMessage("4. Go back to main menu");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                makePetDB();
                displayOptionsOwner();
                break;
            case 2:
                showPetList();
                displayOptionsOwner();
                break;
            case 3:
                showPetList();
                deletePetDB();
                displayOptionsOwner();
                break;
            case 4:
                displayOptionsOwner();
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    public void makePetDB() {
        db.writeDogDataDB();
        TextUI.displayMessage("You have successfully created a pet.");
    }

    public void showPetList() {
        TextUI.displayMessage("Pet list: ");
        db.readDogDataDB(testklasseDB);
        testklasseDB.showPets();
    }

    public void deletePetDB() {
        db.deleteDogDataDBCheck();
        TextUI.displayMessage("You have successfully deleted a pet");
    }

    public void showJobsActivity() {
        TextUI.displayMessage("\n");
        TextUI.displayMessage("You now have following options: ");
        TextUI.displayMessage("1. Create job.");
        TextUI.displayMessage("2. See my created jobs");
        TextUI.displayMessage("3. Go back to main menu.");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                makeJobsDB();
                displayOptionsOwner();
                break;
            case 2:
                showJobsDB();
                displayOptionsOwner();
                break;
            case 3:
                displayOptionsOwner();
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    public void makeJobsDB() {
        db.writeJobDataDB();
        TextUI.displayMessage("You have successfully created a job.");
    }

    public void showJobsDB() {
        TextUI.displayMessage("Jobs: ");
        db.readJobDataDB(testklasseDB);
        testklasseDB.showJobs();
    }
}