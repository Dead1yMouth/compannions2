package ru.rsreu.companions.logic;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.DBType;

public class DAOLogic {
    private static DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE);

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
