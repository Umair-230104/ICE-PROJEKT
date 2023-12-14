import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {




        TestklasseDB testklasseDB = new TestklasseDB();
        Database database = new Database(testklasseDB);

        database.readDogDataDB(testklasseDB);
        database.readDogOwnerDataDB(testklasseDB);
        database.readJobDataDB(testklasseDB);

        PetOwner petOwner = new PetOwner(testklasseDB, database);
        PetWalker petWalker = new PetWalker(testklasseDB, database);

        MainMenu mainMenu = new MainMenu(testklasseDB, database, petOwner, petWalker);
        mainMenu.setUp();
        //database.loginDB();
        /*

        System.out.println("Dog database size: "+testklasseDB.getDogs().size());
        System.out.println("Owner database size: "+testklasseDB.getDogOwners().size());
        System.out.println("Job database size: "+testklasseDB.getJobs().size());
        for (User user : testklasseDB.getDogOwners()) {
            System.out.println("Loaded username: " + user.getUserName());
        }
*/
          /*  for(int i = 0; i < testklasseDB.getDogs().size(); i++){
                System.out.println(testklasseDB.getDogs().get(i).toString());
            }

            for(int i = 0; i < testklasseDB.getDogOwners().size(); i++){
                S*/
        //database.writeJobDataDB();
        //System.out.println("Første job: "+testklasseDB.getJobs().get(0).toString());
        //database.writeDogDataDB();
        /*database.writePetOwnerDataDB();
        database.writeDogDataDB();
        database.writeJobDataDB();
        database.readJobDataDB(testklasseDB);
        database.writePetOwnerDataDB();
        database.writeDogDataDB();
        database.writeDogDataDB();
        database.writeDogDataDB();
        database.writeDogDataDB();*/

    }
}
