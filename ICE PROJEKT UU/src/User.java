// Skal user måske være en abstract klasse?

public class User {
    String userName;
    String passWord;
    String number;
    String mail;
    String userType;

    public User(String userName, String passWord, String number, String mail, String userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.number = number;
        this.mail = mail;
        this.userType= userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getNumber() {
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
}


