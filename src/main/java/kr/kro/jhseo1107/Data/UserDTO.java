package kr.kro.jhseo1107.Data;

public class UserDTO {
    private int userNo;
    private String userName;
    private String userEmail;
    private String userPassword;

    public int getUserNo() {
        return this.userNo;
    }

    public void setUserNo(int value) {
        this.userNo = value;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String value) {
        this.userName = value;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String value) {
        this.userEmail = value;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String value) {
        this.userPassword = value;
    }
}
