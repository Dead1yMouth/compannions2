package ru.rsreu.companions.DataBase.OracleDB;

import java.sql.Connection;
// import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.DataBase.Data.User;

public class OracleUserDAO implements UsersDAO {
    private Connection connection;

    public OracleUserDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<User> getUsers() {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT u.userNickname, r.roleID, r.roleName, "
            + "u.firstName, u.lastName, u.userPassword, u.rating, u.userStatus "
            + "FROM users u JOIN role r ON u.userRole = r.roleID";
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            List<User> list = new ArrayList<User>();
            while (result.next()) {
                String userNickname = result.getString(1);
                int roleID = result.getInt(2);
                String roleName = result.getString(3);
                String firstName = result.getString(4);
                String lastName = result.getString(5);
                String userPassword = result.getString(6);
                float rating = result.getFloat(7);
                String status = result.getString(8);
                User user = new User(userNickname, roleID, roleName, firstName, lastName, userPassword, rating, status);
                list.add(user);
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUserByNickName(String nickName) {
        PreparedStatement statement = null;
        ResultSet result = null; 
        try {
            String query = "SELECT u.userNickname, r.roleID, r.roleName, "
                + "u.firstName, u.lastName, u.userPassword, u.rating, u.userStatus "
                + "FROM users u JOIN role r ON u.userRole = r.roleID "
                + "WHERE u.userNickname = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nickName);
            result = statement.executeQuery();
            List<User> list = new ArrayList<User>();
            while (result.next()) {
                String userNickname = result.getString(1);
                int roleID = result.getInt(2);
                String roleName = result.getString(3);
                String firstName = result.getString(4);
                String lastName = result.getString(5);
                String userPassword = result.getString(6);
                float rating = result.getFloat(7);
                String status = result.getString(8);
                User user = new User(userNickname, roleID, roleName, firstName, lastName, userPassword, rating, status);
                list.add(user);
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void changeUserStatus(String nickName, String status) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE USERS SET USERSTATUS = ? WHERE USERNICKNAME = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, status);  
            statement.setString(2, nickName);  
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addUser(String nickName, String roleName, String firstName, String lastName, String userPassword) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO users  VALUES (?, ?, ?, ?, ?, 0, 'Offline')";
            statement = connection.prepareStatement(query);
            statement.setString(1, nickName);
            switch (roleName) {
                case "Moderator":
                    statement.setInt(2, 2);
                    break;
                case "Admin":
                    statement.setInt(2, 1);    
                    break;
                case "Driver":
                    statement.setInt(2, 3);
                    break;
                default:
                    statement.setInt(2, 4);
                    break;
            }
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, userPassword);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String nickName) {
        PreparedStatement statement = null;
        try{
            String query = "DELETE FROM USERS WHERE USERNICKNAME = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nickName);
            statement.executeUpdate();
            System.out.println(nickName);
            statement.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void redactUser(String nickName, String roleName, String firstName, String lastName, String userPassword) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE USERS SET USERNICKNAME = ?, USERROLE = ?, FIRSTNAME = ?, LASTNAME = ?, USERPASSWORD = ? WHERE USERNICKNAME = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nickName);
            switch (roleName) {
                case "Moderator":
                    statement.setInt(2, 2);
                    break;
                case "Admin":
                    statement.setInt(2, 1);    
                    break;
                case "Driver":
                    statement.setInt(2, 3);
                    break;
                default:
                    statement.setInt(2, 4);
                    break;
            }
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, userPassword);
            statement.setString(6, nickName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }     
    }

    
}
