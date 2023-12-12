public class Pet {

    String name;
    int age;
    int hundeID;
    String race;
    String description;
    int ownerID;

    public Pet(String name, int ownerID, int hundeID, int age, String race, String description) {
        this.name = name;
        this.ownerID = ownerID;
        this.hundeID = hundeID;
        this.age = age;
        this.race = race;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getOwnerID(){
        return ownerID;
    }
    public int getID() {
        return hundeID;
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
    public String toString(){
        return "{Name: "+getName()+", OwnerID: "+ownerID+", Age: "+getAge()+", Race: "+ getRace()+", ID: "+getID()+", Evt om hunden: "+getDescription()+"}";
    }
}