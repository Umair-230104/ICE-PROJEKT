import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DbIO {
    //User
    public ArrayList<String> readUserData(String path) {
        ArrayList<String> users = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                users.add(s);
            }
        } catch (FileNotFoundException e) {
            TextUI.displayMessage("File not found");
        }
        return users;
    }

    public void saveUserData(ArrayList<User> users) {
        try {
            FileWriter writer = new FileWriter("ListUser.data");
            writer.write("Username, password, number, mail" + " \n");
            for (User u : users) {
                String textTosave = u.getUserName() + "," + u.getPassWord() + "," + u.getNumber() + "," + u.getMail(); // nyt
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("something went wrong during writing to file");
        }
    }
}
