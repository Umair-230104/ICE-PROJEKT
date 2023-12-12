import java.util.ArrayList;
public class TestklasseDB {
    private ArrayList<Pet> hunde;
    private ArrayList<User> hundeejer;

    public TestklasseDB(){
        this.hunde = new ArrayList<>();
        this.hundeejer = new ArrayList<>();
    }

    public ArrayList<Pet> getHunde() {
        return hunde;
    }

    public ArrayList<User> getHundeejer() {
        return hundeejer;
    }

    public void addHund(Pet pet){
        hunde.add(pet);
    }

    public void addOwner(User user){
        hundeejer.add(user);
    }
}