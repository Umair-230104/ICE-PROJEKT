import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
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
            writer.write("username, password, number, mail, usertype" + " \n");
            for (User u : users) {
                String textTosave = u.getUserName() + "," + u.getPassWord() + "," + u.getNumber() + "," + u.getMail() + "," + u.getUserType(); // nyt
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("something went wrong during writing to file");
        }
    }

    public ArrayList<String> readPetData(String path) {
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

    public void savePetData(ArrayList<Pet> pets) {
        try {
            FileWriter writer = new FileWriter("ListPet.data");
            writer.write("name, age, pettype, description " + " \n");
            for (Pet p : pets) {
                String textTosave = p.getName()+ "," + p.getAge() + "," + p.getRace() + "," + p.getDescription(); // nyt
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayMessage("something went wrong during writing to file");
        }
    }



}
