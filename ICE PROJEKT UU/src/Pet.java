public class Pet {

    String name;
    int age;
    int dogId;
    String race;
    String description;
    int ownerID;

    public Pet(String name, int ownerID, int dogId, int age, String race, String description) {
        this.name = name;
        this.ownerID = ownerID;
        this.dogId = dogId;
        this.age = age;
        this.race = race;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getID() {
        return dogId;
    }

    public int getAge() {
        return age;
    }

    public String getRace() {
        return race;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "\n" +
                "\nName: " + getName() +
                "\nOwnerID: " + ownerID +
                "\nAge: " + getAge() +
                "\nRace: " + getRace() +
                "\nDogID: " + getID() +
                "\nEvt om hunden: " + getDescription();
    }
}