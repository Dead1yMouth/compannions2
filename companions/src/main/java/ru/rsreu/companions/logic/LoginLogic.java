package ru.rsreu.companions.logic;
import java.util.List;

import ru.rsreu.companions.DataBase.Data.User;

public class LoginLogic {
    

    public static boolean checkLogin(String enterLogin, String enterPass, List<User> userList) {
        if (userList.size() == 0){
            return false;
        }

        if (userList.get(0).getUserPassword().equals(enterPass)) {
            return true;
        }
        return false;
    }
}
