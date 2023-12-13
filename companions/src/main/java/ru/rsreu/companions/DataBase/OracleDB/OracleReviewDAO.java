package ru.rsreu.companions.DataBase.OracleDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.companions.DataBase.ReviewDAO;
import ru.rsreu.companions.DataBase.Data.Review;

public class OracleReviewDAO implements ReviewDAO {

    private Connection connection;

    public OracleReviewDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Review> getReviews() {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT * FROM REVIEW";
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            List<Review> list = new ArrayList<Review>();
            while (result.next()) {
                int reviewID = result.getInt(1);
                String reviewerID = result.getString(2);
                String targetID = result.getString(3);
                int tripID = result.getInt(4);
                String reviewText = result.getString(5);
                int rating = result.getInt(6);
                list.add(new Review(reviewID, reviewerID, targetID, tripID, reviewText, rating));
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
    public List<Review> getReviews(String nickName) {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT * FROM REVIEW WHERE TARGETID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nickName);
            result = statement.executeQuery();
            List<Review> list = new ArrayList<Review>();
            while (result.next()) {
                int reviewID = result.getInt(1);
                String reviewerID = result.getString(2);
                String targetID = result.getString(3);
                int tripID = result.getInt(4);
                String reviewText = result.getString(5);
                int rating = result.getInt(6);
                list.add(new Review(reviewID, reviewerID, targetID, tripID, reviewText, rating));
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
    public void redactReview(int reviewID, String reviewText, int rating) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE REVIEW SET REVIEWTEXT=?, rating=? WHERE REVIEWID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, reviewText);
            statement.setInt(2, rating);
            statement.setInt(3, reviewID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void deleteReview(int reviewID) {
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM REVIEW WHERE REVIEWID=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, reviewID);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeReview(String reviewerID, String targetID, String reviewText, int tripID, int rating) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO review (REVIEWERID, TARGETID, TRIPID, REVIEWTEXT, RATING) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, reviewerID);
            statement.setString(2, targetID);
            statement.setInt(3, tripID);
            statement.setString(4, reviewText);
            statement.setInt(5, rating);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }


    
    
}
