// Skal user måske være en abstract klasse?

public class User {
    private int userId;
    private String userName;
    private String passWord;
    private int number;
    private String mail;
    private String userType;

    public User(int userId, String userName, String passWord, int number, String mail, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.number = number;
        this.mail = mail;
        this.userType= userType;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getNumber() {
        return number;
    }

    public String getMail() {
        return mail;
    }

    public String getUserType() {
        return userType;
    }

    public String toString(){
        return "Username: " + userName +
                "\nPassword: " + passWord +
                "\nNumber: " + number +
                "\nMail: " + mail +
                "\nUser Type: " + userType;
    }

    public String getUsertype() {
        return userType;
    }
}


