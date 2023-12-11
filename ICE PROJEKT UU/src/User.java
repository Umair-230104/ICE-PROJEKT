// Skal user måske være en abstract klasse?

public class User {
    String userName;
    String passWord;
    String number;
    String mail;


    public User(String userName, String passWord, String number, String mail) {
        this.userName = userName;
        this.passWord = passWord;
        this.number = number;
        this.mail = mail;

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


    public String toString(){

        return "{Username: "+getUserName()+", Password: "+getPassWord()+", Number: "+ getNumber()+", Mail: "+getMail()+"}";
    }
}

