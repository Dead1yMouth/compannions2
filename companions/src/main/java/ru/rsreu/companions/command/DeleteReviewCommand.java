package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.ReviewDAO;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class DeleteReviewCommand implements ActionCommand {

    private ReviewDAO reviewDAO;
    private DAOFactory daoFactory;
    
    public DeleteReviewCommand(DAOFactory daoFactory) {
        this.reviewDAO = daoFactory.getReviewDAO();
        this.daoFactory = daoFactory;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        // String login = (String) session.getAttribute("login");

        if(!role.equals("Moderator")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }

        int tripID = Integer.parseInt(request.getParameter("reviewID-delete"));
        
        reviewDAO.deleteReview(tripID);

        ModerReviewsCommand command = new ModerReviewsCommand(daoFactory);
        page = command.execute(request);

        return page;
    }
}
