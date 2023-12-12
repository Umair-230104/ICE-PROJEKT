import java.util.ArrayList;

public class Job {

    private ArrayList<String> activities;

    FileIO io = new FileIO();


    public void myPetJob() {
        TextUI.displayMessage("You have following options: ");
        TextUI.displayMessage("1. Create new activity.");
        TextUI.displayMessage("2. Activity history.");
        TextUI.displayMessage("Enter Your Choice: ");
        int activity = Integer.parseInt(TextUI.getUserInput());
        ;
        switch ((activity)) {
            case 1:
                createJob();
                break;

            case 2:
                //jobHistory(jobs);
                break;

        }
    }

    public  void createJob() {
        activities = new ArrayList<>();

        TextUI.displayMessage("Enter day and time: ");
        String dayAndTime= TextUI.getUserInput();

        TextUI.displayMessage("Enter activity area: ");
        String area= TextUI.getUserInput();

        TextUI.displayMessage("Enter the price: ");
        String price= TextUI.getUserInput();

        TextUI.displayMessage("Enter your contact information: ");
        String information= TextUI.getUserInput();

        TextUI.displayMessage("Describe your pet and what you specifically want: ");
        String description= TextUI.getUserInput();

        String activitiesss =  dayAndTime+ ", " +price+ ", " + area+ ", " + information + ", "+ description+ ".";
        activities.add(activitiesss);

        TextUI.displayMessage("You have successfully created a new activity.");
        TextUI.displayMessage("If you would like to go back to menu options, press 1.");
        int goBack=Integer.parseInt(TextUI.getUserInput());;
        switch (goBack){
            case 1:
                // displayOptions();  skal ændres
                break;

            default:
                TextUI.displayMessage("The program is closing.");
        }
    }

    public ArrayList<String> getActivities() {
        return activities;

    }

}
