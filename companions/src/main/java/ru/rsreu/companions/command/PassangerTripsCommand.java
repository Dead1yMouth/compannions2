package ru.rsreu.companions.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.DataBase.Data.TripRequest;
import ru.rsreu.companions.DataBase.Data.Trip;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class PassangerTripsCommand implements ActionCommand{
        //DEPRECATED


    private TripsDAO tripsDAO; 

    public PassangerTripsCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        String login = (String) session.getAttribute("login");

        if(!role.equals("Passenger")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }

        
        List<Trip> trips = tripsDAO.getTrips(login);
        request.setAttribute("trips", trips);

        List<TripRequest> myTrips = tripsDAO.getPassangersTrips(login);
        request.setAttribute("myTrips", myTrips);

        page = ConfigurationManager.getProperty("path.page.passanger.trips"); 
        return page;
        //DEPRECATED
    }
}
