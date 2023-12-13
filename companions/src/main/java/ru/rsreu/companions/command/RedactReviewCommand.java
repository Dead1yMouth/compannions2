package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.ReviewDAO;

public class RedactReviewCommand implements ActionCommand {

    private ReviewDAO reviewDAO;
    private DAOFactory daoFactory;

    public RedactReviewCommand(DAOFactory daoFactory) {
        this.reviewDAO = daoFactory.getReviewDAO();
        this.daoFactory = daoFactory;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        int reviewID = Integer.parseInt(request.getParameter("reviewID"));
        String reviewText = request.getParameter("reviewText");
        int rating = Integer.parseInt(request.getParameter("rating"));

        System.out.println(reviewID + reviewText + rating);

        reviewDAO.redactReview(reviewID, reviewText, rating);

        ModerReviewsCommand command = new ModerReviewsCommand(daoFactory);
        page = command.execute(request);

        return page;
    }
}
