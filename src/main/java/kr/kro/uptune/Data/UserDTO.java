package kr.kro.uptune.Data;

public class UserDTO {
    private int userNo;
    private String userName;
    private String userEmail;
    private String userPassword;
    private boolean userIsAdmin = false;

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int value) {
        this.userNo = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String value) {
        this.userName = value;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String value) {
        this.userEmail = value;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String value) {
        this.userPassword = value;
    }

    public boolean getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(boolean value)
    {
        this.userIsAdmin = value;
    }
}
