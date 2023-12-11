public class Pet {

    String name;
    int age;
    String petType;
    String description;

    public Pet(String name, int age, String petType, String description) {
        this.name = name;
        this.age = age;
        this.petType = petType;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPetType() {
        return petType;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){

        return "String";
    }
}
