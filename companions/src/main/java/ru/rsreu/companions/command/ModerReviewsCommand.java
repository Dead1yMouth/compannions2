package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.ReviewDAO;
import ru.rsreu.companions.DataBase.Data.Review;
import ru.rsreu.companions.resource.ConfigurationManager;

public class ModerReviewsCommand implements ActionCommand {
    private ReviewDAO reviewDAO;

    public ModerReviewsCommand(DAOFactory daoFactory) {
        this.reviewDAO = daoFactory.getReviewDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        List<Review> reviews = reviewDAO.getReviews();
        request.setAttribute("reviews", reviews);

        page = ConfigurationManager.getProperty("path.page.moder.reviews");
        return page;
    }
}
