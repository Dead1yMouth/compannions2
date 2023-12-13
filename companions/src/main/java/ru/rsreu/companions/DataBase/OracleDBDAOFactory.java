package ru.rsreu.companions.DataBase;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ru.rsreu.companions.DataBase.OracleDB.OracleReviewDAO;
import ru.rsreu.companions.DataBase.OracleDB.OracleTripsDAO;
import ru.rsreu.companions.DataBase.OracleDB.OracleUserDAO;

public class OracleDBDAOFactory extends DAOFactory {
	private static volatile OracleDBDAOFactory instance;
	private Connection connection;

	private OracleDBDAOFactory() {
	}

	public static OracleDBDAOFactory getInstance()
			throws ClassNotFoundException, SQLException, NamingException {
		OracleDBDAOFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDBDAOFactory.class) {
				instance = factory = new OracleDBDAOFactory();
				factory.connected();
			}
		}
		return factory;
	}

	private void connected() throws ClassNotFoundException, SQLException, NamingException {
		Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
		DataSource ds = (DataSource) envCtx.lookup("jdbc/database");
		connection = ds.getConnection();
		System.out.println("Connected to oracle DB!");
	}

	@Override
	public TripsDAO getTripsDAO() {
		return new OracleTripsDAO(connection);
	}

	@Override
	public UsersDAO getUsersDAO() {
		return new OracleUserDAO(connection);
	}

	@Override
	public ReviewDAO getReviewDAO() {
		return new OracleReviewDAO(connection);
	}

	

}
