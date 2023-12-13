package ru.rsreu.companions.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.ReviewDAO;
import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.DataBase.Data.Review;
import ru.rsreu.companions.DataBase.Data.User;
import ru.rsreu.companions.resource.ConfigurationManager;

public class ProfileCommand implements ActionCommand {

    private UsersDAO usersDAO; 
    private ReviewDAO reviewDAO;

    public ProfileCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
        this.reviewDAO = daoFactory.getReviewDAO();
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        String login = (String) session.getAttribute("login");
        System.out.println(role);

        List<User> user = usersDAO.getUserByNickName(login);
        request.setAttribute("user", user.getFirst());

        List<Review> reviews = reviewDAO.getReviews(login);
        request.setAttribute("reviews", reviews);

        if (role.equals("Passenger")) {
            page = ConfigurationManager.getProperty("path.page.passanger.profile");
        } else {
            page = ConfigurationManager.getProperty("path.page.driver.profile");
        }
        
        return page;
    }
    
}
