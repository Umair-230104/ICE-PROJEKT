// Skal user måske være en abstract klasse?

public class User {
    String userName;
    String passWord;
    String number;
    String mail;
    String userid;
    String usertype;


    public User(String userName, String passWord, String number, String mail, String userid, String usertype) {
        this.userName = userName;
        this.passWord = passWord;
        this.number = number;
        this.mail = mail;
        this.userid = userid;
        this.usertype = usertype;

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

    public String getUsertype() {
        return usertype;
    }

    public String getUserid() {
        return userid;
    }

    public String toString() {
        return "Username: " + userName +
                "\nPassword: " + passWord +
                "\nNumber: " + number +
                "\nMail: " + mail +
                "\nUser Type: " + usertype;
    }
}

