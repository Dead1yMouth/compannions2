package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.DataBase.Data.Trip;
import ru.rsreu.companions.DataBase.Data.TripRequest;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

import java.util.List;

public class DriverTripsCommand implements ActionCommand {

    private TripsDAO tripsDAO; 

    public DriverTripsCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        String login = (String) session.getAttribute("login");
        if(!role.equals("Driver")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }


        List<Trip> driverTrips = tripsDAO.getDriversTrips(login);
        request.setAttribute("myTrips", driverTrips);
        List<TripRequest> driverTripRequests = tripsDAO.getDriversRequests(login);
        request.setAttribute("tripRequest", driverTripRequests);

        page = ConfigurationManager.getProperty("path.page.driver.trips");
        return page;
    }
    
}
