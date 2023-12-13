import java.sql.*;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.Scanner;


public class Database{

    private static final String JDBC_URL = "jdbc:mysql://localhost/petwalkerapp";
    private static final String USER = "root";
    private static final String PASSWORD = "b4U}]ADKqGcD86";
    private static Connection connection;
    TextUI ui = new TextUI();
    TestklasseDB testklasseDB = new TestklasseDB();
    private ArrayList<Pet> dogs;



    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            System.out.println("Connected to the database.");
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readDogDataDB(TestklasseDB testklasseDB) {
        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connect();
            String sql = "SELECT dogname, dogid, ownerid, dogage, dograce, dogdescription  FROM dog";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("dogname");
                int age = rs.getInt("dogage");
                String race = rs.getString("dograce");
                int id = rs.getInt("dogid");
                String description = rs.getString("dogdescription");
                int ownerID = rs.getInt("ownerid");

                Pet pet = new Pet(name, ownerID, id, age, race, description);
                testklasseDB.addDog(pet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }

    public void writeDogDataDB() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connect();
            String sql = "INSERT INTO petwalkerapp.dog (dogname, dogage, ownerid, dograce, dogdescription) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            try {
                stmt.setString(1, ui.getInput("Name of the dog"));
                stmt.setInt(2, ui.getNumericInput("Age of the dog"));
                stmt.setInt(3, ui.getNumericInput("ownerid"));
                stmt.setString(4, ui.getInput("Race of the dog"));
                stmt.setString(5, ui.getInput("Description of the dog"));

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void readDogOwnerDataDB(TestklasseDB testklasseDB){
        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connect();

            String sql = "SELECT name, password, number, mail, userid, usertype FROM user";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String number = rs.getString("number");
                String mail = rs.getString("mail");
                String userID = rs.getString("userid");
                String usertype = rs.getString("usertype");

                User user = new User(name, password, number, mail, userID, usertype);
                testklasseDB.addOwner(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }

    public void writePetOwnerDataDB() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connect();
            String sql = "INSERT INTO petwalkerapp.user (name, password, number, mail, usertype) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            String name = ui.getInput("Username");

            //skal ændres til at løbe i gennem arraylisten fra TestklasseDB over user og se om der allerede findes en med det samme username
           /* for (Serie serie : series) {
                if (serie.getName().toLowerCase().contains(searchName.toLowerCase())) {
                    searchResults.add(serie);
                }
            }*/

            String password;
            while (true) {
                TextUI.displayMessage("Password must contain at least one uppercase letter, two numbers and it can't be longer than 25 characters. Create password: ");
                password = TextUI.getUserInput();

                if (isValidPassword(password)) {
                    break;
                } else {
                    TextUI.displayMessage("Invalid password. Please try again.");
                }
            }

            int phonenumber;
            while (true) {
                phonenumber = ui.getNumericInput("Phone number");
                if (phonenumber > 11111111 && phonenumber < 99999999) {
                    break;
                } else {
                    TextUI.displayMessage("Phone number must contain 8 digits");
                }
            }

            //På samme måde som med Username skal der også tjekkes om emailen findes plus tjekke efter @
            String email = ui.getInput("Email");

            //Omdannes til en switch med valgene "petowner" eller "petwalker"
            String usertype = ui.getInput("Usertype");
                try {
                    stmt.setString(1, name);
                    stmt.setString(2, password);
                    stmt.setInt(3, phonenumber);
                    stmt.setString(4, email);
                    stmt.setString(5, usertype);

                    int rowsAffected = stmt.executeUpdate();
                    System.out.println(rowsAffected + " row(s) affected");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    disconnect();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
    }

    private boolean isValidPassword(String password) {
        //pattern for at least 1 uppercase and 2 numbers and max 25 char long
        String uppercaseRegex = ".*[A-Z].*";
        String numbersRegex = ".*\\d.*";
        String lengthRegex= ".{1,25}";

        Pattern uppercasePattern = Pattern.compile(uppercaseRegex);
        Pattern numbersPattern = Pattern.compile(numbersRegex);
        Pattern lengthPattern= Pattern.compile(lengthRegex);
        //using pattern create matches for password
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        Matcher numbersMatcher = numbersPattern.matcher(password);
        Matcher lengthMatcher= lengthPattern.matcher(password);

        return uppercaseMatcher.matches() && numbersMatcher.matches() && lengthMatcher.matches();
    }

    /*private boolean isEmailTaken(String mail) {
      for (User user : users) {
          if (user.getMail().equals(mail)) {
              return true;
          }
      }
        return false;
    }*/

    public void deleteDogDataDB(int dogID){
            Connection conn = null;
            PreparedStatement stmt = null;

            try{
                conn = connect();
                String sql = "DELETE FROM petwalkerapp.dog WHERE dogid = ?";
                stmt = conn.prepareStatement(sql);

                try{
                    stmt.setInt(1, dogID);
                    int rowsAffected = stmt.executeUpdate();
                    System.out.println("Checking in any row(s) is updated: "+rowsAffected+" row(s)");
                } catch(SQLException e){
                    e.printStackTrace();
                } finally {
                    disconnect();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
    }

    public void deleteDogDataDBCheck(){
            Scanner scan = new Scanner(System.in);
            int deleteDogID = ui.getNumericInput("Enter dog ID to remove it from list");
            System.out.println("Are you sure you want to delete dog with ID: "+deleteDogID);
            String confirmation = scan.next().toLowerCase();
            if(confirmation.equalsIgnoreCase("yes")){
                deleteDogDataDB(deleteDogID);
            } else{
                System.out.println("Didn't delete any dog");
            }
    }

        //Skal nok ikke bruges?
 /*   public void readWalkerDataDB(TestklasseDB testklasseDB) {
        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connect();
            String sql = "SELECT username, password, number, mail, userid, usertype  FROM walker";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int number = rs.getInt("number");
                String mail = rs.getString("mail");
                int userid = rs.getInt("userid");
                String usertype = rs.getString("usertype");



                PetWalker petwalker = new PetWalker(username, password, number, mail, userid, usertype);
                testklasseDB.addPetWalker(petwalker);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }*/
    public void readJobDataDB(TestklasseDB testklasseDB) {
        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connect();
            String sql = "SELECT descriptionofjob, dayandtime, price, area, jobid, jobtaken  FROM job";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String descriptionofjob = rs.getString("descriptionofjob");
                String dayandtime = rs.getString("dayandtime");
                String price = rs.getString("price");
                String area = rs.getString("area");
                int jobid = rs.getInt("jobid");
                int jobtaken = rs.getInt("jobtaken");

                Job job = new Job(descriptionofjob, dayandtime, price, area, jobid, jobtaken);
                testklasseDB.addJob(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }

    public void writeJobDataDB() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connect();
            String sql = "INSERT INTO petwalkerapp.job (descriptionofjob, dayandtime, price, area) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            try {
                stmt.setString(1, ui.getInput("Description of the job"));
                stmt.setString(2, ui.getInput("Day and time for the job"));
                stmt.setString(3, ui.getInput("Price"));
                stmt.setString(4, ui.getInput("Area"));

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

