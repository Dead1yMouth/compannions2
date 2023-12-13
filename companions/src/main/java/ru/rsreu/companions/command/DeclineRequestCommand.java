package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class DeclineRequestCommand implements ActionCommand {
    
    private TripsDAO tripsDAO; 
    private DAOFactory daoFactory;

    public DeclineRequestCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
        this.daoFactory = daoFactory;
        
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        // String login = (String) session.getAttribute("login");

        if(!role.equals("Driver")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }


        int tripID = Integer.parseInt(request.getParameter("tripID"));
        tripsDAO.declineTripRequest(tripID);

        DriverTripsCommand command = new DriverTripsCommand(daoFactory);
        page = command.execute(request);
        
        return page;
    }

}
