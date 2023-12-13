package ru.rsreu.companions.DataBase.Data;

public class User {
    private String userNickname;
    private int roleID;
    private String roleName;
    private String firstName;
    private String lastName;
    private String userPassword;
    private float rating;
    private String status;
    
    

    public User(String userNickname, int roleID, String roleName, String firstName, String lastName,
            String userPassword, float rating, String status) {
        this.userNickname = userNickname;
        this.roleID = roleID;
        this.roleName = roleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.rating = rating;
        this.status = status;
    }

    public String getUserNickname() {
        return userNickname;
    }
    public int getRoleID() {
        return roleID;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public float getRating() {
        return rating;
    }
    public String getStatus() {
        return status;
    }

    
}
