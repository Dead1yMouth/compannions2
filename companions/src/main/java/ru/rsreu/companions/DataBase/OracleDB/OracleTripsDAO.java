package ru.rsreu.companions.DataBase.OracleDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.DataBase.Data.TripRequest;
import ru.rsreu.companions.DataBase.Data.Trip;
import ru.rsreu.companions.DataBase.Data.TripInformation;

public class OracleTripsDAO implements TripsDAO {
    private Connection connection;

    public OracleTripsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Trip> getTrips(String login) {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT * FROM TRIP WHERE TRIPID NOT IN (SELECT TRIPID FROM TRIPREQUEST WHERE PASSENGERID = ?) AND TRUNC(SYSDATE) < TRIPDATE";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            result = statement.executeQuery();
            List<Trip> list = new ArrayList<Trip>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String avaliableSeats = result.getString(7);
                list.add(new Trip(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, avaliableSeats));
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
    public List<Trip> getTrips() {
        //DEPRECATED
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            String query = "SELECT * FROM TRIP WHERE TRIPID = 2";
            statement = connection.prepareStatement(query);
            result = statement.executeQuery(query);
            List<Trip> list = new ArrayList<Trip>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String avaliableSeats = result.getString(7);
                list.add(new Trip(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, avaliableSeats));
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e){
            e.printStackTrace();
        }
        //DEPRECATED
        return null;
    }


    @Override
    public List<TripRequest> getPassangersTrips(String login) {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT TRIP.TRIPID, TRIP.DRIVERID, TRIP.TRIPDATE, TRIP.TRIPPRICE, TRIP.STARTLOCATION, TRIP.ENDLOCATION, TRIPREQUEST.REQUESTSTATUS, TRIPREQUEST.REQUESTID, TRIPREQUEST.PASSENGERID\n" + //
                    "FROM TRIP\n" + //
                    "JOIN TRIPREQUEST ON TRIP.TRIPID = TRIPREQUEST.TRIPID\n" + //
                    "WHERE TRIPREQUEST.PASSENGERID = ? AND TRIPREQUEST.REQUESTSTATUS <> 'Declined' AND TRUNC(SYSDATE) < TRIP.TRIPDATE";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            result = statement.executeQuery();
            List<TripRequest> list = new ArrayList<TripRequest>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String status = result.getString(7);
                int requestID = result.getInt(8);
                String passengerID = result.getString(9);
                list.add(new TripRequest(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, status, requestID, passengerID));
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TripRequest> getDriversRequests(String login) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            String query = "SELECT TRIP.TRIPID, TRIP.DRIVERID, TRIP.TRIPDATE, TRIP.TRIPPRICE, TRIP.STARTLOCATION, TRIP.ENDLOCATION, TRIPREQUEST.REQUESTSTATUS, TRIPREQUEST.REQUESTID, TRIPREQUEST.PASSENGERID\n" + //
                    "FROM TRIP\n" + //
                    "JOIN TRIPREQUEST ON TRIP.TRIPID = TRIPREQUEST.TRIPID\n" + //
                    "WHERE TRIP.DRIVERID = ? AND TRIPREQUEST.REQUESTSTATUS <> 'Declined' AND TRIP.TRIPDATE >= TRUNC(SYSDATE)";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            result = statement.executeQuery();
            List<TripRequest> list = new ArrayList<TripRequest>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String status = result.getString(7);
                int requestID = result.getInt(8);
                String passengerID = result.getString(9);
                list.add(new TripRequest(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, status, requestID, passengerID));
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Trip> getDriversTrips(String login) {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            
            String query = "SELECT * FROM TRIP WHERE DRIVERID = ? AND TRUNC(SYSDATE) <= TRIPDATE";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            result = statement.executeQuery();
            List<Trip> list = new ArrayList<Trip>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String avaliableSeats = result.getString(7);
                list.add(new Trip(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, avaliableSeats));
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void refuseTripRequest(int requestID) {
        PreparedStatement statement = null;
        
        try {
            String query = "UPDATE TRIP SET AVALIABLESEATS = AVALIABLESEATS + 1 WHERE TRIPID IN (SELECT TRIPID FROM TRIPREQUEST WHERE REQUESTID = ? AND REQUESTSTATUS='Accepted')";
            statement = connection.prepareStatement(query);
            statement.setInt(1, requestID);
            statement.executeUpdate();

            query = "DELETE FROM TRIPREQUEST WHERE REQUESTID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, requestID);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTrip(int tripID) {
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM TRIP WHERE tripID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeTrip(String driverID, String departure, String arrival, String date, int price, int seats) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO trip (DRIVERID, TRIPDATE, TRIPPRICE, STARTLOCATION, ENDLOCATION, AVALIABLESEATS) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, driverID);
            statement.setString(2, date);
            statement.setInt(3, price);
            statement.setString(4, departure);
            statement.setString(5, arrival);
            statement.setInt(6, seats);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeTripRequest(int tripID, String passengerID) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO tripRequest (passengerID, tripid, requestStatus) VALUES (?, ?, 'Pending')";
            statement = connection.prepareStatement(query);
            statement.setString(1, passengerID);
            statement.setInt(2, tripID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    @Override
    public void acceptTripRequest(int tripID) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE TRIPREQUEST SET REQUESTSTATUS = 'Accepted' WHERE TRIPID = ? AND (SELECT AVALIABLESEATS FROM TRIP WHERE TRIPID = ?) >= 1";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripID);
            statement.setInt(2, tripID);
            int count = statement.executeUpdate();
            if (count > 0){
                query = "UPDATE TRIP SET AVALIABLESEATS = AVALIABLESEATS - 1 WHERE TRIPID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, tripID);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void declineTripRequest(int tripID) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE TRIPREQUEST SET REQUESTSTATUS = 'Declined' WHERE TRIPID = ? AND REQUESTSTATUS = 'Accepted'";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripID);
            int count = statement.executeUpdate(); 
            if (count > 0) {
                query = "UPDATE TRIP SET AVALIABLESEATES = AVALIABLESEATS + 1 WHERE TRIPID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, tripID);
                statement.executeUpdate();
            }

            query = "UPDATE TRIPREQUEST SET REQUESTSTATUS = 'Declined' WHERE TRIPID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripID);
            statement.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> getAllTrips() {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT * FROM TRIP";
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            List<Trip> list = new ArrayList<Trip>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String avaliableSeats = result.getString(7);
                list.add(new Trip(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, avaliableSeats));
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
    public void redactTrip(int tripID, String startLocation, String endLocation, String tripDate, float tripPrice,
            int avaliableSeats) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE TRIP SET STARTLOCATION = ?, ENDLOCATION = ?, TRIPDATE=TO_DATE(?, 'YYYY-MM-DD'), TRIPPRICE = ?, AVALIABLESEATS = ? WHERE TRIPID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, startLocation);
            statement.setString(2, endLocation);
            statement.setString(3, tripDate);
            statement.setFloat(4, tripPrice);
            statement.setInt(5, avaliableSeats);
            statement.setInt(6, tripID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> getTripHistory(String login) {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT * FROM TRIP WHERE TRIPID IN (SELECT TRIPID FROM TRIPREQUEST WHERE PASSENGERID = ? AND REQUESTSTATUS='Accepted') AND TRIPDATE < TRUNC(SYSDATE)";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            result = statement.executeQuery();
            List<Trip> list = new ArrayList<Trip>();
            while (result.next()) {
                int tripID = result.getInt(1);
                String driverID = result.getString(2);
                Date tripDate = result.getDate(3);
                float tripPrice = result.getFloat(4);
                String startLocation = result.getString(5);
                String endLocation = result.getString(6);
                String avaliableSeats = result.getString(7);
                list.add(new Trip(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, avaliableSeats));
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TripInformation> getTripsInformation(String login) {
        PreparedStatement tripStatement = null;
        PreparedStatement usersStatement = null;
        ResultSet tripResultSet = null;
        ResultSet userResultSet = null;
        try {
            String tripQuery = "SELECT * FROM TRIP WHERE DRIVERID = ? AND TRIPDATE < TRUNC(SYSDATE)";
            String usersQuery = "SELECT USERNICKNAME FROM USERS WHERE USERNICKNAME in (SELECT PASSENGERID FROM TRIPREQUEST WHERE TRIPID = ? AND REQUESTSTATUS = 'Accepted')";
            tripStatement= connection.prepareStatement(tripQuery);
            tripStatement.setString(1, login);
            tripResultSet = tripStatement.executeQuery();
            List<TripInformation> list = new ArrayList<TripInformation>();
            while (tripResultSet.next()) {
                int tripID = tripResultSet.getInt(1);
                String driverID = tripResultSet.getString(2);
                Date tripDate = tripResultSet.getDate(3);
                float tripPrice = tripResultSet.getFloat(4);
                String startLocation = tripResultSet.getString(5);
                String endLocation = tripResultSet.getString(6);
                String avaliableSeats = tripResultSet.getString(7);
                usersStatement = connection.prepareStatement(usersQuery);
                usersStatement.setInt(1, tripID);
                userResultSet = usersStatement.executeQuery();
                List<String> usersID = new ArrayList<String>();
                while (userResultSet.next()) {
                    String userID = userResultSet.getString(1);
                    usersID.add(userID);
                }
                usersStatement.close();
                userResultSet.close();
                Trip trip = new Trip(tripID, driverID, tripDate, tripPrice, startLocation, endLocation, avaliableSeats);
                list.add(new TripInformation(trip, usersID));
            }
            tripStatement.close();
            tripResultSet.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
