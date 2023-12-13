package ru.rsreu.companions.DataBase;


public abstract class DAOFactory {
    public static DAOFactory getInstance(DBType dbType) {
        DAOFactory result = dbType.getDAOFactory();
        return result;
    }

    public abstract TripsDAO getTripsDAO();
    public abstract UsersDAO getUsersDAO();
    public abstract ReviewDAO getReviewDAO();
    
}
