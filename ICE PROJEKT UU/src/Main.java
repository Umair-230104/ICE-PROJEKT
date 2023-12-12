public class Main {
    public static void main(String[] args) {

       /*
        MainMenu mainMenu = new MainMenu();
        mainMenu.setUp();
        */

        PetOwner petOwner = new PetOwner();
        petOwner.runMethod();
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