public class Main {
    public static void main(String[] args) {

       /*
        MainMenu mainMenu = new MainMenu();
        mainMenu.setUp();
        */
/*
        PetOwner petOwner = new PetOwner();
        petOwner.runMethod();

 */

        TestklasseDB testklasseDB = new TestklasseDB();
        Database database = new Database();

        database.writePetOwnerDataDB();
        database.readDogOwnerDataDB(testklasseDB);
        System.out.println("hej" + testklasseDB.getHundeejer().get(0).toString());

    }
}


/*
Hvad der virker:
Vi kan lave user som bliver gemt.
Vi kan lave et pet som bliver gemt.
Vi har nogen krav til kode og mail.

Hvad der ikke virker:
User bliver ikke loadet når vi starter programmet igen.
Det samme med pets.

 */