package ru.rsreu.companions.DataBase.Data;

public class Review {
    private int reviewID;
    private String reviewerID;
    private String targetID;
    private int tripID;
    private String reviewText;
    private int rating;
    
    public Review(int reviewID, String reviewerID, String targetID, int tripID, String reviewText, int rating) {
        this.reviewID = reviewID;
        this.reviewerID = reviewerID;
        this.targetID = targetID;
        this.tripID = tripID;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public int getReviewID() {
        return reviewID;
    }

    public String getReviewerID() {
        return reviewerID;
    }

    public String getTargetID() {
        return targetID;
    }

    public int getTripID() {
        return tripID;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    
}
