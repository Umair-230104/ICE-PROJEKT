import java.util.ArrayList;


public class PetOwner {
    private ArrayList<Pet> pets;
    private User currentUser;


    public PetOwner(ArrayList<Pet> pets, User currentUser) {
        this.pets = pets;
        this.currentUser= currentUser;
    }

    public void makePet() {
        TextUI.displayMessage("Please enter pet details.");
        TextUI.displayMessage("Enter pet name: ");
        String name= TextUI.getUserInput();

        TextUI.displayMessage("Enter pet age: ");
        int age= Integer.parseInt(TextUI.getUserInput());

        TextUI.displayMessage("Enter pet type: ");
        String petType= TextUI.getUserInput();

        TextUI.displayMessage("Enter pet description: ");
        String description= TextUI.getUserInput();

//create instance
        Pet newPet= new Pet(name, age, petType, description);

//add pet
        pets.add(newPet);

        TextUI.displayMessage("Pet is now added to your list.");
    }

    public void loadPet() {
// like load user
    }

    public void savePet() {
//same like user
    }

    // showuser eller showuser profile?
    public void showUser(User user) {
        if(user!=null){
            //print user info with toString
            TextUI.displayMessage("User information: ");
            TextUI.displayMessage(user.toString());
        } else {
            TextUI.displayMessage("Something went wrong, please try again.");
        }

    }


    public void displayOptions() {
        TextUI.displayMessage("You now have following options: ");
        TextUI.displayMessage("1. Pet information");
        TextUI.displayMessage("2. Pet activity");
        TextUI.displayMessage("3.Show profile information.");
        TextUI.displayMessage("Enter Your Choice: ");
        int choice = Integer.parseInt(TextUI.getUserInput());
        switch ((choice)) {
            case 1:
                myPetInfo();
                break;
            case 2:
                myPetActivity();
                break;
            case 3:
                showUser(currentUser);
            default:
                TextUI.displayMessage("Invalid, Please Try Again");
        }
    }

    //private void showUser() {
    //  showUser(currentUser);
    //}


    //maybe change name
    public void myPetInfo() {
        TextUI.displayMessage("You have following options: ");
        TextUI.displayMessage("1. See pets.");
        TextUI.displayMessage("2. Create a new pet.");
        TextUI.displayMessage("3.Delete an existing pet.");
        TextUI.displayMessage("Enter Your Choice: ");
        int petInfo=Integer.parseInt(TextUI.getUserInput());;
        switch ((petInfo)){
            case 1:
                seeMyPet();
                break;

            case 2:
                createPet();
                break;

            case 3:
                deletePet();

        }
    }

    public void seeMyPet() {
//call toString from pet
        if(pets.isEmpty()){
            TextUI.displayMessage("You dont have any pets yet.");
        } else {
            TextUI.displayMessage("Your pets: ");
        }

        for(Pet pet:pets){
            TextUI.displayMessage(pet.toString());
        }
    }

    public void createPet() {
//makeUser like in mainmenu
        //
    }

    public void deletePet() {
//delete from pet database
        if(pets.isEmpty()){
            TextUI.displayMessage("You don't have any pets.");
            return;
        }

        TextUI.displayMessage("Your pets: ");
        for(int i=0; i<pets.size();i++){
            TextUI.displayMessage((i+1) + ". " + pets.get(i).toString());
        }

        TextUI.displayMessage("Enter the number of pets you want to delete, press '0' to cancel: ");
        int choice= Integer.parseInt(TextUI.getUserInput());

        if(choice > 0 && choice <= pets.size()){
            //remove pets from list
            pets.remove(choice -1);
            TextUI.displayMessage("Pet deleted");
        } else if (choice==0){
            TextUI.displayMessage("Deletion canceled");
        } else {
            TextUI.displayMessage("Invalid, no pet has been deleted");
        }
    }

    public void myPetActivity() {
        TextUI.displayMessage("You have following options: ");
        TextUI.displayMessage("1. Create new activity.");
        TextUI.displayMessage("2. Activity history.");
        TextUI.displayMessage("Enter Your Choice: ");
        int activity=Integer.parseInt(TextUI.getUserInput());;
        switch ((activity)){
            case 1:
                createActivity();
                break;

            case 2:
                activityHistory();
                break;

        }
    }

    public void createActivity() {

    }

    public void activityHistory() {

    }

}