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

public class UserProfileCommand implements ActionCommand {

    private UsersDAO usersDAO; 
    private ReviewDAO reviewDAO;

    public UserProfileCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
        this.reviewDAO = daoFactory.getReviewDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        System.out.println(role);

        String nickName = request.getParameter("nickName");

        List<User> user = usersDAO.getUserByNickName(nickName);
        request.setAttribute("user", user.getFirst());

        List<Review> reviews = reviewDAO.getReviews(nickName);
        request.setAttribute("reviews", reviews);

        if (role.equals("Passenger")) {
            page = ConfigurationManager.getProperty("path.page.passanger.user.profile");
        } else {
            page = ConfigurationManager.getProperty("path.page.driver.user.profile");
        }

        return page;
    }
}
