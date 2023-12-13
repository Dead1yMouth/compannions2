package ru.rsreu.companions.DataBase;

import java.util.List;

import ru.rsreu.companions.DataBase.Data.User;

public interface UsersDAO {
    public List<User> getUsers();
    
    public List<User> getUserByNickName(String nickName);

    public void changeUserStatus(String nickName, String status);

    public void addUser(String nickName, String roleName, String firstName, String lastName, String userPassword);

    public void deleteUser(String nickName);

    public void redactUser(String nickName, String roleName, String firstName, String lastName, String userPassword);

}
