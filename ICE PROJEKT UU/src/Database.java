import java.sql.*;
import java.util.regex.*;
import java.util.Scanner;


public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost/petwalkerapp";
    private static final String USER = "root";
    private static final String PASSWORD = "egf89ahw";
    private static Connection connection;
    TextUI ui = new TextUI();
    private TestklasseDB testklasseDB;
    private User currentUser;
    public Database(TestklasseDB testklasseDB) {
        this.testklasseDB = testklasseDB;
    }

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

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
        } else {
            TextUI.displayMessage("No logged in user");
        }
    }

    public User loginDB() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
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
                String userid = rs.getString("userid");
                String number = rs.getString("number");
                String mail = rs.getString("mail");
                String usertype = rs.getString("usertype");
                currentUser = new User(username, password, number, mail, userid, usertype);
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
        return currentUser;
    }

    public void readUserDataDB(TestklasseDB testklasseDB) {
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

    public void writeUserDataDB() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connect();
            String sql = "INSERT INTO petwalkerapp.user (name, password, number, mail, usertype) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            String name;
            boolean usernameExists;
            do {
                name = ui.getInput("Username");
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                usernameExists = false;
                for (User user : testklasseDB.getDogOwners()) {
                    if (user.getUserName().equalsIgnoreCase(name.trim())) {
                        usernameExists = true;
                        break;
                    }
                }

                if (usernameExists) {
                    TextUI.displayMessage("Username already exists. Please choose a different username.");
                }
            } while (usernameExists);

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
            boolean isPhoneNumberValid;
            do {
                phonenumber = ui.getNumericInput("Phone number");
                boolean isEightDigitsLong = phonenumber >= 10000000 && phonenumber <= 99999999;
                boolean isPhoneNumberUnique = true;
                for (User user : testklasseDB.getDogOwners()) {
                    if (user.getNumber().equals(String.valueOf(phonenumber))) {
                        isPhoneNumberUnique = false;
                        break;
                    }
                }

                isPhoneNumberValid = isEightDigitsLong && isPhoneNumberUnique;
                if (!isPhoneNumberValid) {
                    if (!isEightDigitsLong) {
                        TextUI.displayMessage("Phone number must contain 8 digits.");
                    }
                    if (!isPhoneNumberUnique) {
                        TextUI.displayMessage("Phone number is already in use. Please choose a different number.");
                    }
                }
            } while (!isPhoneNumberValid);

            String email;
            boolean emailExists;
            boolean isEmailValid;
            do {
                email = ui.getInput("Email");
                emailExists = false;
                for (User user : testklasseDB.getDogOwners()) {
                    if (user.getMail().equalsIgnoreCase(email.trim())) {
                        emailExists = true;
                        break;
                    }
                }
                boolean containsAtSymbol = email.contains("@");
                isEmailValid = !emailExists && containsAtSymbol;
                if (!isEmailValid) {
                    if (emailExists) {
                        TextUI.displayMessage("Email already used. Please choose a different email.");
                    }
                    if (!containsAtSymbol) {
                        TextUI.displayMessage("Invalid email. Make sure it contains '@'.");
                    }
                }
            } while (!isEmailValid);

            int userTypeChoice;
            String usertype = "";
            boolean isUsertypeValid;
            do {
                userTypeChoice = ui.getNumericInput("Select Usertype:\n1. Owner\n2. Walker");
                switch (userTypeChoice) {
                    case 1:
                        usertype = "owner";
                        isUsertypeValid = true;
                        break;
                    case 2:
                        usertype = "walker";
                        isUsertypeValid = true;
                        break;
                    default:
                        isUsertypeValid = false;
                        TextUI.displayMessage("Invalid choice. Please enter '1' for 'owner' or '2' for 'walker'.");
                        break;
                }
            } while (!isUsertypeValid);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidPassword(String password) {
        //pattern for at least 1 uppercase and 2 numbers and max 25 char long
        String uppercaseRegex = ".*[A-Z].*";
        String numbersRegex = ".*\\d.*";
        String lengthRegex = ".{1,25}";
        Pattern uppercasePattern = Pattern.compile(uppercaseRegex);
        Pattern numbersPattern = Pattern.compile(numbersRegex);
        Pattern lengthPattern = Pattern.compile(lengthRegex);
        //using pattern create matches for password
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        Matcher numbersMatcher = numbersPattern.matcher(password);
        Matcher lengthMatcher = lengthPattern.matcher(password);
        return uppercaseMatcher.matches() && numbersMatcher.matches() && lengthMatcher.matches();
    }

    public boolean loginDBCheck() {
        boolean loggedIn = false;
        if (currentUser != null) {
            loggedIn = true;
        }
        return loggedIn;
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
                String age = rs.getString("dogage");
                String race = rs.getString("dograce");
                String id = rs.getString("dogid");
                String description = rs.getString("dogdescription");
                String ownerID = rs.getString("ownerid");
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
        System.out.println("writedog: userid" + currentUser.getUserid());
        if (currentUser != null) {
            try {
                conn = connect();
                String sql = "INSERT INTO petwalkerapp.dog (dogname, dogage, ownerid, dograce, dogdescription) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                try {
                    stmt.setString(1, ui.getInput("Name of the dog"));
                    stmt.setInt(2, ui.getNumericInput("Age of the dog"));
                    stmt.setInt(3, Integer.parseInt(currentUser.getUserid()));
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
        } else {
            System.out.println("No user logged in. Cannot write dog data.");
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
        int deleteDogID = ui.getNumericInput("Enter dog ID to remove it from list");
        TextUI.displayMessage("Are you sure you want to delete dog with ID: " + deleteDogID);
        String confirmation = ui.getInput("Write " + '"' + "yes" + '"' + " to delete your dog. Press any key and hit [Enter] if you don't want to delete it").toLowerCase();
        if (confirmation.equalsIgnoreCase("yes")) {
            deleteDogDataDB(deleteDogID);
        } else {
            System.out.println("Didn't delete any dog");
        }
    }

    public void showDogs() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connect();
            String sql = "SELECT * FROM petwalkerapp.dog WHERE ownerid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(currentUser.getUserid()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("dogname");
                String age = rs.getString("dogage");
                String dogID = rs.getString("dogid");
                String race = rs.getString("dograce");
                String description = rs.getString("dogdescription");
                String ownerID = rs.getString("ownerid");
                Pet pet = new Pet(name, ownerID, dogID, age, race, description);
                System.out.println(pet.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void showOwnJobs() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connect();
            String sql = "SELECT * FROM petwalkerapp.job WHERE petownerid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(currentUser.getUserid()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String descriptionofjob = rs.getString("descriptionofjob");
                String dayandtime = rs.getString("dayandtime");
                String price = rs.getString("price");
                String area = rs.getString("area");
                int jobid = rs.getInt("jobid");
                int jobtaken = rs.getInt("jobtaken");
                int petownerid = rs.getInt("petownerid");
                Job job = new Job(descriptionofjob, dayandtime, price, area, jobid, jobtaken, petownerid);
                System.out.println(job.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void readJobDataDB(TestklasseDB testklasseDB) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connect();
            String sql = "SELECT descriptionofjob, dayandtime, price, area, jobid, jobtaken, petownerid  FROM job";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String descriptionofjob = rs.getString("descriptionofjob");
                String dayandtime = rs.getString("dayandtime");
                String price = rs.getString("price");
                String area = rs.getString("area");
                int jobid = rs.getInt("jobid");
                int jobtaken = rs.getInt("jobtaken");
                int petownerid = rs.getInt("petownerid");
                Job job = new Job(descriptionofjob, dayandtime, price, area, jobid, jobtaken, petownerid);
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
            String sql = "INSERT INTO petwalkerapp.job (descriptionofjob, dayandtime, price, area, jobtaken, petownerid) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            try {
                stmt.setString(1, ui.getInput("Description of the job"));
                stmt.setString(2, ui.getInput("Day and time for the job"));
                stmt.setString(3, ui.getInput("Price"));
                stmt.setString(4, ui.getInput("Area"));
                stmt.setInt(5, 0);
                stmt.setString(6, currentUser.getUserid());
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
