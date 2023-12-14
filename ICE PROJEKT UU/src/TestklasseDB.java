import java.util.ArrayList;
public class TestklasseDB {
    public ArrayList<Pet> dogs;
    public ArrayList<User> dogOwners;
    public ArrayList<Job> jobs;
    public ArrayList<PetWalker> petWalkers;

    public TestklasseDB(){
        this.dogs = new ArrayList<>();
        this.dogOwners = new ArrayList<>();
        this.jobs = new ArrayList<>();
        this.petWalkers = new ArrayList<>();
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
