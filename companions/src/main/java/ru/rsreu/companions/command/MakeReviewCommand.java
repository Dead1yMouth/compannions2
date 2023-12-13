package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.ReviewDAO;

public class MakeReviewCommand implements ActionCommand {

    ReviewDAO reviewDAO;
    DAOFactory daoFactory;
    
    
    
    public MakeReviewCommand(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.reviewDAO = daoFactory.getReviewDAO();
    }



    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        String reviewerID = login;
        String targetID = request.getParameter("targetID");
        String reviewText = request.getParameter("reviewText");
        int rating = Integer.parseInt(request.getParameter("rating"));
        int tripID = Integer.parseInt(request.getParameter("tripID"));

        reviewDAO.makeReview(reviewerID, targetID, reviewText, tripID, rating);

        TripHistoryCommand command = new TripHistoryCommand(daoFactory);
        page = command.execute(request);

        return page;
    }

    
}
