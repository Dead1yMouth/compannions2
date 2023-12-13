package ru.rsreu.companions.DataBase.Data;

import java.util.List;

public class TripInformation {
    private Trip trip;
    private List<String> usersID;

    
    
    public TripInformation(Trip trip, List<String> usersID) {
        this.trip = trip;
        this.usersID = usersID;
    }

    public Trip getTrip() {
        return trip;
    }
    
    public List<String> getUsersID() {
        return usersID;
    }
}
