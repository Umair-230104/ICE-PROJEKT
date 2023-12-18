import java.util.ArrayList;

public class PetOwner {
    private User currentUser;
    private ArrayList<Job> jobs = new ArrayList<>();
    private TestklasseDB testklasseDB;
    private Database database;

    public PetOwner(TestklasseDB testklasseDB, Database database){
        this.testklasseDB = testklasseDB;
        this.database = database;
    }

    public void runMethodOwner() {
        displayOptionsOwner();

    }

    //Display
    public void displayOptionsOwner() {
        while (true) {
            TextUI.displayMessage("\nYou now have the following options: ");
            TextUI.displayMessage("1. Pet Menu");
            TextUI.displayMessage("2. Pet activity");
            TextUI.displayMessage("3. Show profile information.");
            TextUI.displayMessage("4. Exit Program");
            TextUI.displayMessage("Enter Your Choice: ");
            try {
                int choice = Integer.parseInt(TextUI.getUserInput());
                switch (choice) {
                    case 1:
                        petMenu();
                        break;
                    case 2:
                        showJobsActivity();
                        break;
                    case 3:
                        showProfileInformation();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        TextUI.displayMessage("Invalid choice, Please Try Again");
                }
            } catch (NumberFormatException e) {
                TextUI.displayMessage("Invalid input, please enter a number.");
            }
        }
    }

    public void showProfileInformation() {
        if (currentUser != null) {
            TextUI.displayMessage("Profile Information:");
            TextUI.displayMessage("UserID: " + currentUser.getUserid());
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

    // Pet Menu
    public void petMenu() {
        while (true) {
            TextUI.displayMessage("\nPet Menu Options: ");
            TextUI.displayMessage("1. Create pet");
            TextUI.displayMessage("2. List of pet");
            TextUI.displayMessage("3. Delete pets");
            TextUI.displayMessage("4. Go back to main menu");
            TextUI.displayMessage("5. Exit Program");
            TextUI.displayMessage("Enter Your Choice: ");
            try {
                int choice = Integer.parseInt(TextUI.getUserInput());
                switch (choice) {
                    case 1:
                        makePetDB();
                        break;
                    case 2:
                        showPetList();
                        break;
                    case 3:
                        showPetList();
                        deletePetDB();
                        break;
                    case 4:
                        return; // Go back to the main menu
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        TextUI.displayMessage("Invalid choice, Please Try Again");
                }
            } catch (NumberFormatException e) {
                TextUI.displayMessage("Invalid input, please enter a number.");
            }
        }
    }

    public void makePetDB() {
        database.writeDogDataDB();
        TextUI.displayMessage("You have successfully created a pet.");
    }

    public void showPetList() {
        TextUI.displayMessage("Pet list: ");
        database.showDogs();
    }

    public void deletePetDB() {
        database.deleteDogDataDBCheck();
        TextUI.displayMessage("You have successfully deleted a pet");
    }

    // Show Jobs Activity
    public void showJobsActivity() {
        while (true) {
            TextUI.displayMessage("\nJob Activity Options: ");
            TextUI.displayMessage("1. Create job.");
            TextUI.displayMessage("2. See my created jobs");
            TextUI.displayMessage("3. Go back to main menu.");
            TextUI.displayMessage("Enter Your Choice: ");
            try {
                int choice = Integer.parseInt(TextUI.getUserInput());
                switch (choice) {
                    case 1:
                        makeJobsDB();
                        break;
                    case 2:
                        showJobsDB();
                        break;
                    case 3:
                        return; // Go back to the main menu
                    default:
                        TextUI.displayMessage("Invalid choice, Please Try Again");
                }
            } catch (NumberFormatException e) {
                TextUI.displayMessage("Invalid input, please enter a number.");
            }
        }
    }

    public void makeJobsDB() {
        database.writeJobDataDB();
        TextUI.displayMessage("You have successfully created a job.");
    }

    public void showJobsDB() {
        TextUI.displayMessage("Jobs: ");
        database.readJobDataDB(testklasseDB);
        testklasseDB.showJobs();
    }
}