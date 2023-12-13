package ru.rsreu.companions.DataBase;

import java.util.List;

import ru.rsreu.companions.DataBase.Data.TripRequest;
import ru.rsreu.companions.DataBase.Data.Trip;
import ru.rsreu.companions.DataBase.Data.TripInformation;

public interface TripsDAO {
    public List<Trip> getTrips(String login);
    public List<Trip> getTrips();
    public List<TripInformation> getTripsInformation(String login);
    public List<Trip> getAllTrips();
    public List<Trip> getDriversTrips(String login);
    public List<TripRequest> getPassangersTrips(String login);
    public List<TripRequest> getDriversRequests(String login);
    public List<Trip> getTripHistory(String login);
    public void refuseTripRequest(int requestID);
    public void makeTripRequest(int tripID, String passengerID);
    public void makeTrip(String driverID, String departure, String arrival, String date, int price, int seats);
    public void removeTrip(int tripID);
    public void acceptTripRequest(int tripID);
    public void declineTripRequest(int tripID);
    public void redactTrip(int tripID, String startLocation, String endLocation, String tripDate, float tripPrice, int avaliableSeats);
}
