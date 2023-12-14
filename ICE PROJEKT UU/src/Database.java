import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Database {

    private static final String JDBC_URL = "jdbc:mysql://localhost/petwalkerapp";
    private static final String USER = "root";
    private static final String PASSWORD = "egf89ahw";
    private static Connection connection;
    private TextUI ui = new TextUI();
    private User currentUser;


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


    public User getCurrentUser() {
        return currentUser;
    }

    public void showUserProfile() {
        if (currentUser != null) {
            TextUI.displayMessage(currentUser.toString());
        } else{
            TextUI.displayMessage("No logged in user");
    }
}

public boolean loginDB() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    boolean loggedIn = false;

    try {
        conn = connect();

        // Prompt the user for username and password
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();


        String sql = "SELECT userid, usertype, number, mail FROM user WHERE name = ? AND password = ?";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);
        stmt.setString(2, password);

        rs = stmt.executeQuery();

        if (rs.next()) {
            // If a row is found, the username and password are correct
            loggedIn = true;
            // Set the current user based on the retrieved data
            int userID = rs.getInt("userid");
            int number = rs.getInt("number");
            String mail = rs.getString("mail");
            String userType = rs.getString("usertype");
            currentUser = new User(userID, username, password, number, mail, userType);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
            se2.printStackTrace();
        }
        disconnect();
    }

    return loggedIn;
}


public void readDogOwnerDataDB(TestklasseDB testklasseDB) {
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
            int number = rs.getInt("number");
            String mail = rs.getString("mail");
            int userID = rs.getInt("userid");
            String usertype = rs.getString("usertype");

            User user = new User(userID, name, password, number, mail, usertype);
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

        try {
            stmt.setString(1, ui.getInput("Navn"));
            stmt.setString(2, ui.getInput("Password"));
            stmt.setInt(3, ui.getNumericInput("Phone number"));
            stmt.setString(4, ui.getInput("Email"));
            stmt.setString(5, ui.getInput("User type Owner or Walker"));


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


public void deleteDogDataDB(int dogID) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = connect();
        String sql = "DELETE FROM petwalkerapp.dog WHERE dogid = ?";
        stmt = conn.prepareStatement(sql);

        try {
            stmt.setInt(1, dogID);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Checking in any row(s) is updated: " + rowsAffected + " row(s)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteDogDataDBCheck() {
    Scanner scan = new Scanner(System.in);
    int deleteDogID = ui.getNumericInput("\nEnter dog ID to remove it from list");
    System.out.println("Are you sure you want to delete dog with ID: " + deleteDogID);
    String confirmation = scan.next().toLowerCase();
    if (confirmation.equals("yes")) {
        deleteDogDataDB(deleteDogID);
    } else {
        System.out.println("Didn't delete any dog");
    }
}


public void readJobDataDB(TestklasseDB testklasseDB) {
    Connection conn = null;
    PreparedStatement stmt = null;


    try {
        conn = connect();
        String sql = "SELECT descriptionofjob, dayandtime, price, area, jobid  FROM job";
        stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String descriptionofjob = rs.getString("descriptionofjob");
            String dayandtime = rs.getString("dayandtime");
            String price = rs.getString("price");
            String area = rs.getString("area");
            int jobid = rs.getInt("jobid");

            Job job = new Job(descriptionofjob, dayandtime, price, area, jobid);
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