import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Database{

    private static final String JDBC_URL = "jdbc:mysql://localhost/petwalkerapp";
    private static final String USER = "root";
    private static final String PASSWORD = "b4U}]ADKqGcD86";
    private static Connection connection;
    TextUI ui = new TextUI();


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
                testklasseDB.addHund(pet);
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
                stmt.setString(1, ui.getInput("Hundens navn"));
                stmt.setInt(2, ui.getNumericInput("Hundens alder"));
                stmt.setInt(3, 2);
                stmt.setString(4, ui.getInput("Hundens race"));
                stmt.setString(5, ui.getInput("Beskrivelse af hund"));

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

            String sql = "SELECT name, password, number, mail, userid FROM user";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String password = rs.getString("password");
                String number = rs.getString("number");
                String mail = rs.getString("mail");
                int userID = rs.getInt("userID");

                User user = new User(name, password, number, mail, userID);
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
            String sql = "INSERT INTO petwalkerapp.user (name, password, number, mail, usertype) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            try {
                stmt.setString(1, ui.getInput("Name"));
                stmt.setString(2, ui.getInput("Password"));
                stmt.setInt(3, ui.getNumericInput("Phone number"));
                stmt.setString(4, ui.getInput("Email"));
                stmt.setString(5, ui.getInput("User type"));

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
            if(confirmation.equals("yes")){
                deleteDogDataDB(deleteDogID);
            } else{
                System.out.println("Didn't delete any dog");
            }
    }
}

