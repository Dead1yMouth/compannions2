package ru.rsreu.companions.DataBase;

import java.util.List;

import ru.rsreu.companions.DataBase.Data.Review;

public interface ReviewDAO {
    public List<Review> getReviews();
    public List<Review> getReviews(String nickName);
    public void redactReview(int reviewID, String reviewText, int rating);
    public void deleteReview(int reviewID);
    public void makeReview(String reviewerID, String targetID, String reviewText, int tripID, int Rating);
}
