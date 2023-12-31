import java.util.ArrayList;
public class TestklasseDB {
    private ArrayList<Pet> dogs;
    private ArrayList<User> dogOwners;
    private ArrayList<Job> jobs;
    private ArrayList<PetWalker> petWalkers;

    public TestklasseDB(){
        this.dogs = new ArrayList<>();
        this.dogOwners = new ArrayList<>();
        this.jobs = new ArrayList<>();
        this.petWalkers = new ArrayList<>();
    }


    public void showPets (){
        TextUI.displayMessage(String.valueOf(dogs));
    }

    public void showJobs (){
        TextUI.displayMessage(String.valueOf(jobs));
    }

    public ArrayList<Pet> getDogs() {
        return dogs;
    }

    public ArrayList<User> getDogOwners() {
        return dogOwners;
    }

    public void addDog(Pet pet){
        dogs.add(pet);
    }

    public void addOwner(User user){
        dogOwners.add(user);
    }

    public ArrayList<Job> getJobs() { return jobs; }

    public void addJob(Job job){jobs.add(job); }
}