package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class RedactTripCommand implements ActionCommand {

    TripsDAO tripsDAO;
    DAOFactory daoFactory;

    public RedactTripCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
        this.daoFactory = daoFactory;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(!role.equals("Moderator")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }

        int tripID = Integer.parseInt(request.getParameter("tripID"));
        String startLocation = request.getParameter("startLocation");
        String endLocation = request.getParameter("endLocation");
        String tripDate = request.getParameter("tripDate");
        float tripPrice = Float.parseFloat(request.getParameter("tripPrice"));
        int avaliableSeats = Integer.parseInt(request.getParameter("avaliableSeats"));
        
        tripsDAO.redactTrip(tripID, startLocation, endLocation, tripDate, tripPrice, avaliableSeats);
        
        ModerTripsCommand command = new ModerTripsCommand(daoFactory);
        page = command.execute(request);
        return page;
    }
    
}
