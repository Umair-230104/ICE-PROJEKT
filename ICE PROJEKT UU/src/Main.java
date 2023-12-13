import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

       /*
        MainMenu mainMenu = new MainMenu();
        mainMenu.setUp();
        */


        TestklasseDB testklasseDB = new TestklasseDB();
        Database database = new Database();
        database.readDogDataDB(testklasseDB);
        database.readDogOwnerDataDB(testklasseDB);
        database.readJobDataDB(testklasseDB);

        //database.writeDogDataDB();

        System.out.println("Dog database size: "+testklasseDB.getDogs().size());
        System.out.println("Owner database size: "+testklasseDB.getDogOwners().size());

            for(int i = 0; i < testklasseDB.getDogs().size(); i++){
                System.out.println(testklasseDB.getDogs().get(i).toString());
            }

            for(int i = 0; i < testklasseDB.getDogOwners().size(); i++){
                System.out.println(testklasseDB.getDogOwners().get(i).toString());
            }
        System.out.println("Første job: "+testklasseDB.getJobs().get(0).toString());

  /*    database.writePetOwnerDataDB();
        database.writeDogDataDB();
        database.writeJobDataDB();
        database.readJobDataDB(testklasseDB);*/


    }
}
