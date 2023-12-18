public class Main {
    public static void main(String[] args) {
        TestklasseDB testklasseDB = new TestklasseDB();
        Database database = new Database(testklasseDB);

        database.readDogDataDB(testklasseDB);
        database.readUserDataDB(testklasseDB);
        database.readJobDataDB(testklasseDB);

        PetOwner petOwner = new PetOwner(testklasseDB, database);
        PetWalker petWalker = new PetWalker(testklasseDB, database);

        MainMenu mainMenu = new MainMenu(testklasseDB, database, petOwner, petWalker);
        mainMenu.setUp();
    }
}

/*
Når en bruger for vist en liste af pets eller jobs så er det alle der bliver vist
men der skal kun vises de jobs fx en pet owner har lavet og det samme med pets
men en petwalker skal kunne se alle de jobs der er fra alle petowner


og når bruger taster noget der ikke skal tastet så der hvor programmet
lukker ned så skal der lave sådan at man enten skal prøve igen eller noget
 */

/*
Jeg har lagt database, pet, user, mainmenu, owner og walker klasse ind

det med at en owner kun kan se de pet han har lavet så skal det samme være med jobs
 */

/*
Vi skal lave hvis man er logget ind og man er inde på den menu man er på
så skal man enten kunne slutte programmet (som vi allerede har) eller så
skal man gå til login og sign up side
 */