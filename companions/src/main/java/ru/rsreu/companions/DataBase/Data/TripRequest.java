package ru.rsreu.companions.DataBase.Data;

import java.sql.Date;

public class TripRequest {
    private int tripID;
    private String driverID;
    private Date tripDate;
    private float tripPrice;
    private String startLocation;
    private String endLocation;
    private String status;
    private int requestID;
    private String passengerID;
    
    public TripRequest(int tripID, String driverID, Date tripDate, float tripPrice, String startLocation, String endLocation,
            String status, int requestID, String passengerID) {
        this.tripID = tripID;
        this.driverID = driverID;
        this.tripDate = tripDate;
        this.tripPrice = tripPrice;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = status;
        this.requestID = requestID;
        this.passengerID = passengerID;
    }

    
    
    public int getTripID() {
        return tripID;
    }
    
    public String getDriverID() {
        return driverID;
    }
    
    public Date getTripDate() {
        return tripDate;
    }
    
    public float getTripPrice() {
        return tripPrice;
    }
    
    public String getStartLocation() {
        return startLocation;
    }
    
    public String getEndLocation() {
        return endLocation;
    }
    
    public String getStatus() {
        return status;
    }

    public int getRequestID() {
        return requestID;
    }



    public String getPassengerID() {
        return passengerID;
    }
}
