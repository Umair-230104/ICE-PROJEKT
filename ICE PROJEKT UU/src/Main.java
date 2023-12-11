import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        /*MainMenu mainMenu = new MainMenu();
        mainMenu.setUp();*/
        TestklasseDB testklasseDB = new TestklasseDB();
        Database database = new Database();
        database.readDogDataDB(testklasseDB);
        database.readDogOwnerDataDB(testklasseDB);

        System.out.println("Dog database size: "+testklasseDB.getHunde().size());
        System.out.println("Owner database size: "+testklasseDB.getHundeejer().size());

            for(int i = 0; i < testklasseDB.getHunde().size(); i++){
                System.out.println(testklasseDB.getHunde().get(i).toString());
            }

            for(int i = 0; i < testklasseDB.getHundeejer().size(); i++){
                System.out.println(testklasseDB.getHundeejer().get(i).toString());
            }


    }
}
