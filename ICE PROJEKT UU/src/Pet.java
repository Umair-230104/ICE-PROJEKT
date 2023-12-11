public class Pet {

    String name;
    int age;
    int hundeID;
    String race;
    String description;

    public Pet(String name, int hundeID, int age, String race, String description) {
        this.name = name;
        this.hundeID = hundeID;
        this.age = age;
        this.race = race;
        this.description = description;
    }

    public String getName() {
        return name;
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

    public String toString(){
        return "{Name: "+getName()+", Age: "+getAge()+", Race: "+ getRace()+", ID: "+getID()+", Evt om hunden: "+getDescription()+"}";
    }
}