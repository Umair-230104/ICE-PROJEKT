public class Pet {

    String name;
    String age;
    String dogID;
    String race;
    String description;
    String ownerID;

    public Pet(String name, String ownerID, String dogID, String age, String race, String description) {
        this.name = name;
        this.ownerID = ownerID;
        this.dogID = dogID;
        this.age = age;
        this.race = race;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getOwnerID(){
        return ownerID;
    }
    public String getID() {
        return dogID;
    }


    public String getAge() {
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
        return "{Name: "+getName()+", OwnerID: "+ownerID+", Age: "+getAge()+", Race: "+ getRace()+", ID: "+getID()+", Descript your dog: "+getDescription()+"}";
    }
}